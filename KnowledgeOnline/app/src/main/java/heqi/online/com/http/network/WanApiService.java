package heqi.online.com.http.network;


import java.util.List;

import heqi.online.com.base.WanBaseBean;
import heqi.online.com.main.bean.WanBannerBean;
import heqi.online.com.main.bean.WxArticlesBelongBean;
import heqi.online.com.main.bean.WxArticlesListBean;
import io.reactivex.Flowable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author : by
 * date: 2019/4/19 0019  上午 10:31.
 * describe  wanAndroid 的请求调用
 */

public interface WanApiService {

    //获取微信文章列表
    @GET("wxarticle/chapters/json")
    Flowable<Result<WanBaseBean<List<WxArticlesBelongBean>>>> getWxArticles();

    //获取公众号下具体文章列表
    @GET("wxarticle/list/{chapterId}/{curPage}/json")
    Flowable<Result<WanBaseBean<WxArticlesListBean>>> getWxArticlesList(@Path("chapterId") int chapterId, @Path("curPage") int curPage);

    //获取banner 图片
    @GET("banner/json")
    Flowable<Result<WanBaseBean<List<WanBannerBean>>>> getBannerList();
}
