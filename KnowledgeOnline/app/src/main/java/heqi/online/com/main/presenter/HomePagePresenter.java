package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import java.util.List;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.base.WanBaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.http.network.BaseWanApiServiceHelper;
import heqi.online.com.main.bean.ArticleTypeBean;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.WanBannerBean;
import heqi.online.com.main.inter.IHomePageArticle;
import heqi.online.com.utils.UIUtils;

/**
 * Created by Administrator on 2019/4/25.
 */

public class HomePagePresenter extends BaseAbstractPresenter<IHomePageArticle> {

    public HomePagePresenter(IHomePageArticle mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getTypes() {

        compositeDisposable.add(BaseApiServiceHelper.getArticleTypes().subscribe(new BaseConsumer<BaseBean<List<ArticleTypeBean>>>() {
            @Override
            protected void onSuccessData(BaseBean<List<ArticleTypeBean>> result) {
                if (result.isRequestSuccess()) { //请求成功
                    List<ArticleTypeBean> data = result.getData();
                    mView.getArticleTypes(data);
                }
            }
        }));
    }

    //获取首页文章列表
    public void getHomePageArticles(String title, String typeCodes, int currentPage, int pageSize) {
        compositeDisposable.add(BaseApiServiceHelper.getArticles(title, typeCodes, currentPage, pageSize).subscribe(new BaseConsumer<BaseBean<HomePageBean>>() {
            @Override
            protected void onSuccessData(BaseBean<HomePageBean> result) {
                if (result.isRequestSuccess()) { //如果请求成功以后
                    HomePageBean data = result.getData();
                    if (data != null) {
                        mView.getHomePageBean(data);
                    }
                }
            }
        }));
    }

    public void getHomePageArticlesNoType(int currentPage, int pageSize){
        compositeDisposable.add(BaseApiServiceHelper.getHomeArticle(currentPage,pageSize).subscribe(new BaseConsumer<BaseBean<HomePageBean>>() {
            @Override
            protected void onSuccessData(BaseBean<HomePageBean> result) {
                if (result.isRequestSuccess()) { //如果请求成功以后
                    HomePageBean data = result.getData();
                    if (data != null) {
                        mView.getHomePageBean(data);
                    }
                }
            }
        }));
    }

    public void getPublishArticles(String loginAccount, int currentPage, int pageSize) {
        compositeDisposable.add(BaseApiServiceHelper.getPublishArticles(loginAccount, currentPage, pageSize).subscribe(new BaseConsumer<BaseBean<HomePageBean>>() {
            @Override
            protected void onSuccessData(BaseBean<HomePageBean> result) {
                if (result.isRequestSuccess()) { //如果请求成功以后
                    HomePageBean data = result.getData();
                    if (data != null) {
                        mView.getHomePageBean(data);
                    }
                }
            }
        }));
    }

    //获取我的收藏列表
    public void getMyCollection(int currentPage, int pageSize) {
        compositeDisposable.add(BaseApiServiceHelper.getCollection(UIUtils.getUid(), currentPage, pageSize).subscribe(new BaseConsumer<BaseBean<HomePageBean>>() {
            @Override
            protected void onSuccessData(BaseBean<HomePageBean> result) {
                if (result.isRequestSuccess()) {
                    HomePageBean data = result.getData();
                    if (data != null) {
                        mView.getHomePageBean(data);
                    }
                }
            }
        }));
    }

    //获取banner List
    public void getBannerList() {
        compositeDisposable.add(BaseWanApiServiceHelper.getBannerList().subscribe(new BaseConsumer<WanBaseBean<List<WanBannerBean>>>() {
            @Override
            protected void onSuccessData(WanBaseBean<List<WanBannerBean>> result) {
                if (result.getData() != null) {
                    mView.getBannerList(result.getData());
                }
            }
        }));
    }
}
