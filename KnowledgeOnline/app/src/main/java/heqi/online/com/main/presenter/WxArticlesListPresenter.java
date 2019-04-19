package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.WanBaseBean;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.http.network.BaseWanApiServiceHelper;
import heqi.online.com.main.bean.WxArticlesListBean;
import heqi.online.com.main.inter.IWxArticlesList;

/**
 * author : by
 * date: 2019/4/19 0019  下午 2:28.
 * describe  微信文章列表
 */

public class WxArticlesListPresenter extends BaseAbstractPresenter<IWxArticlesList> {

    public WxArticlesListPresenter(IWxArticlesList mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getWxArticleList(int chapterId, int curPage) {
        compositeDisposable.add(BaseWanApiServiceHelper.getWxArticlesList(chapterId, curPage).subscribe(new BaseConsumer<WanBaseBean<WxArticlesListBean>>() {
            @Override
            protected void onSuccessData(WanBaseBean<WxArticlesListBean> result) {
                //获取微信文章列表
                mView.getWxArticlesList(result.getData());
            }
        }));
    }
}
