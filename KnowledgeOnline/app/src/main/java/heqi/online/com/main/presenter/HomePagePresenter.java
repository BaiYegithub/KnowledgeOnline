package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.inter.IHomePageArticle;

/**
 * Created by Administrator on 2019/4/25.
 */

public class HomePagePresenter extends BaseAbstractPresenter<IHomePageArticle> {

    public HomePagePresenter(IHomePageArticle mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getHomePageArticles(int currentPage, int pageSize) {
        compositeDisposable.add(BaseApiServiceHelper.getHomeArticle(currentPage, pageSize).subscribe(new BaseConsumer<BaseBean<HomePageBean>>() {
            @Override
            protected void onSuccessData(BaseBean<HomePageBean> result) {
                if (result.isRequestSuccess()) { //如果请求成功以后
                    HomePageBean data = result.getData();
                    mView.getHomePageBean(data);
                }
            }
        }));
    }
}
