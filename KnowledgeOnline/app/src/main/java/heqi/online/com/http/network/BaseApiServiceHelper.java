package heqi.online.com.http.network;

import java.util.ArrayList;
import java.util.List;

import heqi.online.com.base.BaseBean;
import heqi.online.com.login.bean.LoginBean;
import heqi.online.com.main.bean.CourseBean;
import heqi.online.com.main.bean.HomePageBean;
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
        return getFlowable(apiService.login(userName, passWord));
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
     * @param articleId
     * @return 根据文章编号查询文章内容
     */
    public static Flowable<Result<BaseBean<HomePageBean.DataBean>>> getArticleDetail(String articleId) {
        return getFlowable(apiService.getArticleDetail(articleId,UIUtils.getUid()));
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
        return getFlowable(apiService.getCollection(loginAccount,currentPage,pageSize));
    }

    //分页查询课程列表
    public static Flowable<Result<BaseBean<CourseBean>>> getCourseBean(int currentPage,int pageSize){
        return getFlowable(apiService.getCourseBean(currentPage,pageSize));
    }

}
