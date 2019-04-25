package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.text.TextUtils;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.inter.IArticleDetail;

/**
 * Created by Administrator on 2019/4/25.
 */

public class ArticleDetailPresenter extends BaseAbstractPresenter<IArticleDetail> {

    public ArticleDetailPresenter(IArticleDetail mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getArticleDetail(String articleId) {
        mView.showLoading();
        compositeDisposable.add(BaseApiServiceHelper.getArticleDetail(articleId).subscribe(new BaseConsumer<BaseBean<HomePageBean.DataBean>>(mView) {
            @Override
            protected void onSuccessData(BaseBean<HomePageBean.DataBean> result) {
                if (result.isRequestSuccess()) {//请求成功
                    HomePageBean.DataBean data = result.getData();
                    if (data != null) {
                        mView.getArticleDetail(data);
                    }
                }
            }
        }));

    }

    /**
     * @param collect 是否收藏
     * @param uid     用户id
     * @param aid     文章id
     */
    public void collectOrNotArticle(final boolean isCollect, String uid, String aid) {

        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(aid)) {
            mView.showToast("暂时不能操作");
            return;
        }

        mView.showLoading();
        compositeDisposable.add(BaseApiServiceHelper.collectOrNotArticle(isCollect, uid, aid).subscribe(new BaseConsumer<BaseBean>(mView) {
            @Override
            protected void onSuccessData(BaseBean result) {
                if(result.isRequestSuccess()){
                    mView.ClickSuccess(isCollect);
                }
            }
        }));
    }

}
