package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.text.TextUtils;

import java.util.List;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.bean.ArticleTypeBean;
import heqi.online.com.main.inter.IPublishArticle;

/**
 * Created by Administrator on 2019/4/21.
 */

public class PublishArticlePresenter extends BaseAbstractPresenter<IPublishArticle> {

    public PublishArticlePresenter(IPublishArticle mView, LifecycleOwner lifecycleOwner) {
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

    /**
     *
     * @param title 文章标题
     * @param articleContent 文章内容
     * @param loginAccount 登陆名
     */
    public void publishArticle(String title, String articleContent, String loginAccount,String typeCode) {
        if(TextUtils.isEmpty(title)){
            mView.showToast("标题不能为空");
            return;
        }
        if(TextUtils.isEmpty(articleContent)){
            mView.showToast("内容不能为空");
            return;
        }
        mView.showLoading();
        compositeDisposable.add(BaseApiServiceHelper.publishArticle(title, articleContent, loginAccount,typeCode).subscribe(new BaseConsumer<BaseBean>() {
            @Override
            protected void onSuccessData(BaseBean result) {
                if(result.isRequestSuccess()){
                    mView.publishSuccess();
                }
            }
        }));
    }
}
