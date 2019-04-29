package heqi.online.com.http.network;

import java.util.List;

import heqi.online.com.base.BaseBean;
import heqi.online.com.login.bean.LoginBean;
import heqi.online.com.main.bean.CommentsBean;
import heqi.online.com.main.bean.CourseBean;
import heqi.online.com.main.bean.FocusBean;
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

    //根据登陆账号查询发布的所有文章
    @POST("article/showPublishArticlesByPageBean")
    @FormUrlEncoded
    Flowable<Result<BaseBean<HomePageBean>>> getPublishArticles(@Field("loginAccount") String loginAccount, @Field("currentPage") int currentPage, @Field("pageSize") int pageSize);

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

    //关注用户
    @POST("user/focusUser")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> focusUser(@Field("uid") String uid, @Field("fid") String fid);

    //取消关注
    @POST("user/cancelFocus")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> cancelFocus(@Field("uid") String uid, @Field("fid") String fid);

    //查询我关注的
    @POST("user/showFocusUsersByPageBean")
    @FormUrlEncoded
    Flowable<Result<BaseBean<FocusBean>>> getFocusUser(@Field("uid") String uid, @Field("currentPage") int currentPage, @Field("pageSize") int pageSize);

    //查询关注我的
    @POST("user/showUsersFocusOnByPageBean")
    @FormUrlEncoded
    Flowable<Result<BaseBean<FocusBean>>> getFocusOn(@Field("uid") String uid, @Field("currentPage") int currentPage, @Field("pageSize") int pageSize);

    //修改用户信息
    @POST("user/update")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> update(@Field("loginAccount") String loginAccount, @Field("nickName") String nickName, @Field("userSex") String userSex, @Field("userAge") int userAge);

    //添加评论
    @POST("comments/insert")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> insert(@Field("articleId") String articleId, @Field("fromUid") String fromUid, @Field("content") String content);

    //查询所有文章评论
    @POST("comments/selectAllComments")
    @FormUrlEncoded
    Flowable<Result<BaseBean<List<CommentsBean>>>> getCommentsList(@Field("articleId") String articleId);

    //添加回复
    @POST("reply/insert")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> replyInsert(@Field("commentId") String commentId, @Field("replyId") String replyId, @Field("replyType") String replyType, @Field("fromUid") String fromUid, @Field("toUid") String toUid, @Field("content") String content);

    //删除评论
    @POST("comments/delete")
    @FormUrlEncoded
    Flowable<Result<BaseBean>> deleteComments(@Field("articleId") String articleId, @Field("fromUid") String fromUid, @Field("id") int id);


}
