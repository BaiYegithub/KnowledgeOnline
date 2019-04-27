package heqi.online.com.http.network;

import heqi.online.com.base.BaseBean;
import heqi.online.com.login.bean.LoginBean;
import heqi.online.com.main.bean.CourseBean;
import heqi.online.com.main.bean.HomePageBean;
import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2019/4/6.
 */

public interface ApiService {

    //注册接口
    @POST("user/register")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> register(@Field("loginAccount") String loginAccount, @Field("password") String password);

    //登陆接口
    @POST("user/login")
    @FormUrlEncoded
    Flowable<Result<BaseBean<LoginBean>>> login(@Field("loginAccount") String loginAccount, @Field("password") String password);

    //发布文章
    @POST("article/publishArticle")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> publishArticle(@Field("title") String title, @Field("articleContent") String articleContent, @Field("loginAccount") String loginAccount);

    //获取首页文章列表
    @POST("article//showAllArticlesByPageBean")
    @FormUrlEncoded
    Flowable<Result<BaseBean<HomePageBean>>> getHomeArticle(@Field("currentPage") int currentPage, @Field("pageSize") int pageSize);

    //根据文章编号查询文章内容
    @POST("article/showArticleDetails")
    @FormUrlEncoded
    Flowable<Result<BaseBean<HomePageBean.DataBean>>> getArticleDetail(@Field("articleId") String articleId, @Field("loginAccount") String loginAccount);

    //用户收藏文章
    @POST("article/collectArticle")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> collectArticle(@Field("uid") String uid, @Field("aid") String aid);

    //用户取消收藏文章
    @POST("article/cancelCollection")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> cancleCollect(@Field("uid") String uid, @Field("aid") String aid);

    //用户头像上传
    @POST("user/upload/{loginAccount}")
    @Multipart
    Flowable<Result<BaseBean<String>>> upload(@Part MultipartBody.Part file, @Path("loginAccount") String loginAccount);

    //查询用户收藏的所有文章列表
    @POST("article/selectCollectionArticleByPageBean")
    @FormUrlEncoded
    Flowable<Result<BaseBean<HomePageBean>>> getCollection(@Field("loginAccount") String loginAccount, @Field("currentPage") int currentPage, @Field("pageSize") int pageSize);

    //分页查询课程列表
    @POST("course/showAllCourseByPageBean")
    @FormUrlEncoded
    Flowable<Result<BaseBean<CourseBean>>> getCourseBean(@Field("currentPage") int currentPage, @Field("pageSize") int pageSize);

}
