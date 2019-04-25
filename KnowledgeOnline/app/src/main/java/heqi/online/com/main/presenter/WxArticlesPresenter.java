package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import java.util.List;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.WanBaseBean;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.http.network.BaseWanApiServiceHelper;
import heqi.online.com.main.bean.WxArticlesBelongBean;
import heqi.online.com.main.inter.IWxArticles;

/**
 * author : by
 * date: 2019/4/19 0019  上午 10:59.
 * describe
 */

public class WxArticlesPresenter extends BaseAbstractPresenter<IWxArticles> {

    public WxArticlesPresenter(IWxArticles mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getWxArticles(){
        mView.showLoading();
        compositeDisposable.add(BaseWanApiServiceHelper.getWxArticles().subscribe(new BaseConsumer<WanBaseBean<List<WxArticlesBelongBean>>>(mView) {
            @Override
            protected void onSuccessData(WanBaseBean<List<WxArticlesBelongBean>> result) {
                if(result.getErrorCode()==0){//获取数据成功
                    mView.getWxArticlesList(result.getData());
                }
            }
        }));
    }
}
