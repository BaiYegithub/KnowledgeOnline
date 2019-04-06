package heqi.online.com.http.network;

import heqi.online.com.base.BaseBean;
import io.reactivex.Flowable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2019/4/6.
 */

public interface ApiService {

    //注册接口
    @POST("user/register")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> register(@Field("loginAccount") String loginAccount, @Field("password") String password);

}
