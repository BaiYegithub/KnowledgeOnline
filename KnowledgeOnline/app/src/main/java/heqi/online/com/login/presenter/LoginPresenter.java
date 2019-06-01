package heqi.online.com.login.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.login.bean.LoginBean;
import heqi.online.com.login.inter.ILogin;
import heqi.online.com.utils.ConstantUtil;
import heqi.online.com.utils.SharedPreferenceUtils;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2019/4/6.
 */

public class LoginPresenter extends BaseAbstractPresenter<ILogin> {

    public LoginPresenter(ILogin mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    /**
     * @param userName 用户名
     * @param password 密码
     */
    public void register(String userName, String password) {
        compositeDisposable.add(BaseApiServiceHelper.register(userName, password).subscribe(new BaseConsumer<BaseBean>() {
            @Override
            protected void onSuccessData(BaseBean result) {
                if (result.isRequestSuccess()) {
                    mView.registerSuccess();
                }
            }
        }));

    }

    /**
     * @param userName 用户名
     * @param password 密码
     */
    public void login(String userName, String password) {
        mView.showLoading();
        compositeDisposable.add(BaseApiServiceHelper.login(userName, password).subscribe(new BaseConsumer<BaseBean<LoginBean>>(mView) {
            @Override
            protected void onSuccessData(BaseBean<LoginBean> result) {
                if (result.isRequestSuccess()) {
                    LoginBean loginBean = result.getData();
                    //保存个人信息到sharedPreference
                    //保存用户名
                    SharedPreferenceUtils.put(ConstantUtil.LoginAccount, loginBean.getLoginAccount());
                    //保存用户昵称
                    SharedPreferenceUtils.put(ConstantUtil.NickName, loginBean.getNickName());
                    //保存用户年龄
                    SharedPreferenceUtils.put(ConstantUtil.Age, loginBean.getUserAge());
                    //保存用户头像
                    SharedPreferenceUtils.put(ConstantUtil.UserPicture, loginBean.getUserPic());
                    //保存用户性别
                    SharedPreferenceUtils.put(ConstantUtil.Sex, loginBean.getUserSex());

                    SharedPreferenceUtils.put(ConstantUtil.isLogin, true);

                    SharedPreferenceUtils.put(ConstantUtil.UrlSig,loginBean.getUrlSig());

                    TIMManager.getInstance().login(loginBean.getLoginAccount(), loginBean.getUrlSig(), new TIMCallBack() {
                        @Override
                        public void onError(int code, String desc) {
                            //错误码 code 和错误描述 desc，可用于定位请求失败原因
                            //错误码 code 列表请参见错误码表
                            Log.d("baiye", "login failed. code: " + code + " errmsg: " + desc);
                            mView.showToast("现在不能聊天哦~");
                            mView.loginSuccess();
                        }

                        @Override
                        public void onSuccess() {
                            Log.d("baiye", "login succ");
                            mView.loginSuccess();
                        }
                    });

                }
            }
        }));

    }


}
