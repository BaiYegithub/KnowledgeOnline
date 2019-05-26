package heqi.online.com.http.network;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import heqi.online.com.base.BaseBean;
import heqi.online.com.login.bean.LoginBean;
import heqi.online.com.main.bean.ArticleTypeBean;
import heqi.online.com.main.bean.CommentsBean;
import heqi.online.com.main.bean.CourseBean;
import heqi.online.com.main.bean.FocusBean;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.utils.TxyInit;
import heqi.online.com.utils.UIUtils;
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
    public static Flowable<Result<BaseBean>> register(String userName, String passWord) {
        return getFlowable(apiService.register(userName, passWord));
    }

    //登陆功能
    public static Flowable<Result<BaseBean<LoginBean>>> login(String userName, String passWord) {
        return getFlowable(apiService.login(userName, passWord, TxyInit.SDK_APPID));
    }

    //发布文章功能
    public static Flowable<Result<BaseBean>> publishArticle(String title, String articleContent, String loginAccount) {
        return getFlowable(apiService.publishArticle(title, articleContent, loginAccount));
    }

    /**
     * @param currentPage 当前页
     * @param pageSize    每页展示的条目数
     * @return 获取首页推荐文章列表
     */
    public static Flowable<Result<BaseBean<HomePageBean>>> getHomeArticle(int currentPage, int pageSize) {
        return getFlowable(apiService.getHomeArticle(currentPage, pageSize));
    }

    /**
     * 根据个人查询发布的文章
     *
     * @param loginAccount
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static Flowable<Result<BaseBean<HomePageBean>>> getPublishArticles(String loginAccount, int currentPage, int pageSize) {
        return getFlowable(apiService.getPublishArticles(loginAccount, currentPage, pageSize));
    }

    /**
     * @param articleId
     * @return 根据文章编号查询文章内容
     */
    public static Flowable<Result<BaseBean<HomePageBean.DataBean>>> getArticleDetail(String articleId) {
        return getFlowable(apiService.getArticleDetail(articleId, UIUtils.getUid()));
    }

    /**
     * 收藏或者取消收藏
     *
     * @return
     */
    public static Flowable<Result<BaseBean>> collectOrNotArticle(boolean collect, String uid, String aid) {
        if (collect) {
            return getFlowable(apiService.collectArticle(uid, aid));
        } else {
            return getFlowable(apiService.cancleCollect(uid, aid));
        }
    }

    /**
     * 用户头像上传
     *
     * @return
     */
    public static Flowable<Result<BaseBean<String>>> upload(String filePath, String loginAccount) {
        List<String> list = new ArrayList<>();
        list.add(filePath);
        // RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), loginAccount);
        return getFlowable(apiService.upload(UIUtils.filesToMultipartBody(list).part(0), loginAccount));
    }

    /**
     * @param loginAccount 用户id
     * @param currentPage  当前页
     * @param pageSize     每页数据
     * @return
     */
    public static Flowable<Result<BaseBean<HomePageBean>>> getCollection(String loginAccount, int currentPage, int pageSize) {
        return getFlowable(apiService.getCollection(loginAccount, currentPage, pageSize));
    }

    //分页查询课程列表
    public static Flowable<Result<BaseBean<CourseBean>>> getCourseBean(int currentPage, int pageSize) {
        return getFlowable(apiService.getCourseBean(currentPage, pageSize));
    }

    /**
     * 关注用户
     *
     * @param uid
     * @param fid 被关注的uid
     * @return
     */
    public static Flowable<Result<BaseBean>> focusOrNotUser(boolean focus, String uid, String fid) {
        if (focus) {
            return getFlowable(apiService.focusUser(uid, fid));
        } else {
            return getFlowable(apiService.cancelFocus(uid, fid));
        }
    }

    /**
     * 获取关注列表
     *
     * @param from
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static Flowable<Result<BaseBean<FocusBean>>> getFocusList(int from, int currentPage, int pageSize) {
        {
            if (from == 1) {
                return getFlowable(apiService.getFocusOn(UIUtils.getUid(), currentPage, pageSize));
            } else {
                return getFlowable(apiService.getFocusUser(UIUtils.getUid(), currentPage, pageSize));
            }
        }

    }

    /**
     * 修改个人信息
     *
     * @param loginAccount
     * @param nickName
     * @param userSex
     * @param userAge
     * @return
     */
    public static Flowable<Result<BaseBean>> update(String loginAccount, String nickName, String userSex, int userAge) {
        return getFlowable(apiService.update(loginAccount, nickName, userSex, userAge));
    }

    /**
     * @param articleId 文章id
     * @param content   文章内容
     * @return
     */
    public static Flowable<Result<BaseBean>> insertComments(String articleId, String content) {
        return getFlowable(apiService.insert(articleId, UIUtils.getUid(), content));
    }

    /**
     * 获取评论列表
     *
     * @param articleId
     * @return
     */
    public static Flowable<Result<BaseBean<List<CommentsBean>>>> getCommentsList(String articleId) {
        return getFlowable(apiService.getCommentsList(articleId));
    }

    /**
     * 回复评论
     *
     * @return
     */
    public static Flowable<Result<BaseBean>> replyInsert(String commentId, String replyId, String replyType, String toUid, String content) {
        return getFlowable(apiService.replyInsert(commentId, replyId, replyType, UIUtils.getUid(), toUid, content));
    }

    public static Flowable<Result<BaseBean>> deleteComments(String articleId, int id) {
        return getFlowable(apiService.deleteComments(articleId, UIUtils.getUid(), id));
    }

    /*
    * 获取所有的类型
    * */
    public static Flowable<Result<BaseBean<List<ArticleTypeBean>>>> getArticleTypes() {
        return getFlowable(apiService.getArticleTypes());
    }


    public static Flowable<Result<BaseBean<HomePageBean>>> getArticles(String title, String typeCodes, int currentPage, int pageSize) {
        if(TextUtils.isEmpty(title)&&!TextUtils.isEmpty(typeCodes)){
            return getFlowable(apiService.getArticle(typeCodes,currentPage,pageSize));
        }else if(!TextUtils.isEmpty(title)&&TextUtils.isEmpty(typeCodes)){
            return getFlowable(apiService.getArticleByTitle(title,currentPage,pageSize));
        }else if(TextUtils.isEmpty(title)&&TextUtils.isEmpty(typeCodes)){
            return getFlowable(apiService.getArticle(currentPage,pageSize));
        }else {
            return getFlowable(apiService.getArticle(title,typeCodes,currentPage,pageSize));
        }

    }


}
