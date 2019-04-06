package heqi.online.com.http.network;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import heqi.online.com.base.BaseBean;
import heqi.online.com.base.BaseView;
import heqi.online.com.utils.LogUtil;
import heqi.online.com.utils.SharedPreferenceUtils;
import io.reactivex.functions.Consumer;
import retrofit2.adapter.rxjava2.Result;


public abstract class BaseConsumer<T> implements Consumer<Result<T>> {
    private BaseView mView;
    private Class clazz;
    private final int CATCH_TIME = 10;//缓存时间（单位：秒）

    public BaseConsumer() {

    }

    public BaseConsumer(BaseView view) {
        this.mView = view;
    }

    public BaseConsumer(Class clazz) {
        this.clazz = clazz;
    }

    public BaseConsumer(BaseView view, Class clazz) {
        this.mView = view;
        this.clazz = clazz;
    }


    @Override
    public void accept(Result<T> result) throws Exception {
        try {
            if (mView != null) {
                mView.hideLoading();
            }
            if (result.isError()&&clazz!=null) {
                String jsonData = (String) SharedPreferenceUtils.get(clazz.getName(), "");
                LogUtil.i("clazz", "clazz:" + clazz);
                if (!"".equals(jsonData)) {
                    Gson gson = new Gson();
                    BaseBean baseBean = new BaseBean();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    baseBean.setCode(jsonObject.optString("code"));
                    baseBean.setMessage(jsonObject.optString("message"));
                    baseBean.setData( gson.fromJson(jsonObject.optString("data"), clazz));
                    onSuccessData((T) baseBean);
                    return;
                }
            }
            if (result.isError()) {
                onErrorData("网络错误未知");
                return;
            }
            if (result.response().isSuccessful()) {
                if (clazz!=null){
                    //读取上次缓存时间
                    long homeCacheTime = (long) SharedPreferenceUtils.get("homeCacheTime", 0L);
                    long currentTime = System.currentTimeMillis();
                    //判断当前距离上次缓存时间是否超过CATCH_TIME秒钟，如果超过了更新缓存
                    if ((currentTime-homeCacheTime)>CATCH_TIME*1000){
                        SharedPreferenceUtils.put(clazz.getName(), new Gson().toJson(result.response().body()));
                        SharedPreferenceUtils.put("homeCacheTime", currentTime);
                    }
                }
                if (mView != null) {
                    mView.hideNoNetWork();
                }
                onSuccessData(result.response().body());
            } else {
                int code = result.response().code();
                if (code == 500 || code == 404 || code == 505 || code == 400 || code == 502 || code == 504) {
                    onErrorData("网络错误码" + result.response().code());
                }
            }
        } catch (Exception e) {
            failureData(e);
        }
    }

    private void failureData(Throwable throwable) {
        try {
            if (throwable != null) {
                String errorString = "";
                if (throwable instanceof ConnectException) {
                    errorString = "网络错误 ConnectException";
                } else if (throwable instanceof SocketException) {
                    errorString = "网络错误 SocketException";
                } else if (throwable instanceof UnknownHostException) {
                    errorString = "请检查是否已经连接网络~";
                }
                onErrorData(errorString);
            }
        } catch (Exception we) {
            we.printStackTrace();
        }
    }

    protected abstract void onSuccessData(T result);

    protected void onErrorData(String errorString) {
        // UIUtils.showToast("错误信息:" + errorString);
        LogUtil.i1(errorString);
        if (mView != null) {
            mView.showNoNetWork();
        }

    }
}
