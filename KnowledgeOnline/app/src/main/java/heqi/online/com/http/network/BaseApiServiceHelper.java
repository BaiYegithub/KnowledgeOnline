package heqi.online.com.http.network;

import heqi.online.com.base.BaseBean;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Administrator on 2019/4/6.
 */

public class BaseApiServiceHelper {
    private static final ApiService apiService = RetrofitAPIManager.getInstance().getApiService();


    /**
     * @param flowable 请求flowable
     * @return Flowable 对象
     */

    private static <T> Flowable<T> getFlowable(Flowable<T> flowable) {
        return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    //注册功能
    public static Flowable<Result<BaseBean>> register(String userName,String passWord){

        return getFlowable(apiService.register(userName,passWord));
    }
}
