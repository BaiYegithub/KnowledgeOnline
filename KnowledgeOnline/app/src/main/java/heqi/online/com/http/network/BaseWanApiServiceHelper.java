package heqi.online.com.http.network;

import java.util.List;

import heqi.online.com.base.WanBaseBean;
import heqi.online.com.main.bean.WanBannerBean;
import heqi.online.com.main.bean.WxArticlesBelongBean;
import heqi.online.com.main.bean.WxArticlesListBean;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Administrator on 2019/4/6.
 */

public class BaseWanApiServiceHelper {
    private static final WanApiService wanApiService = RetrofitAPIManager.getInstance().getWanApiService();


    /**
     * @param flowable 请求flowable
     * @return Flowable 对象
     */

    private static <T> Flowable<T> getFlowable(Flowable<T> flowable) {
        return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @return 获取微信公众号列表
     */
    public static Flowable<Result<WanBaseBean<List<WxArticlesBelongBean>>>> getWxArticles() {
        return getFlowable(wanApiService.getWxArticles());
    }

    /**
     * @return 获取微信公众号文章列表
     */
    public static Flowable<Result<WanBaseBean<WxArticlesListBean>>> getWxArticlesList(int chapterId, int curPage) {
        return getFlowable(wanApiService.getWxArticlesList(chapterId, curPage));
    }

    /**
     * 获取首页Banner图
     *
     * @return
     */
    public static  Flowable<Result<WanBaseBean<List<WanBannerBean>>>> getBannerList() {
        return getFlowable(wanApiService.getBannerList());
    }

}
