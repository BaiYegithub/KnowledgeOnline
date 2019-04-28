package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.text.TextUtils;

import java.util.List;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.bean.CommentsBean;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.inter.IArticleDetail;
import heqi.online.com.utils.UIUtils;

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
     * @param isCollect 是否收藏
     * @param uid       用户id
     * @param aid       文章id
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
                if (result.isRequestSuccess()) {
                    mView.ClickSuccess(isCollect);
                }
            }
        }));
    }

    public void FocusUserOrNot(final boolean isFocus, String fid) {
        mView.showLoading();
        compositeDisposable.add(BaseApiServiceHelper.focusOrNotUser(!isFocus, UIUtils.getUid(), fid).subscribe(new BaseConsumer<BaseBean>(mView) {
            @Override
            protected void onSuccessData(BaseBean result) {
                if (result.isRequestSuccess()) {//是否请求成功
                    mView.showToast(result.getMessage());
                    mView.changeFocus(!isFocus);
                }
            }
        }));
    }

    public void insertComments(String articleId, String content) {

        if (TextUtils.isEmpty(content)) {
            mView.showToast("评论内容不能为空");
            return;
        }

        mView.showLoading();
        compositeDisposable.add(BaseApiServiceHelper.insertComments(articleId, content).subscribe(new BaseConsumer<BaseBean>(mView) {
            @Override
            protected void onSuccessData(BaseBean result) {
                if (result.isRequestSuccess()) {
                    mView.showToast("评论成功");
                    mView.insertSuccess();
                }
            }
        }));
    }

    public void getCommentList(String articleId) {
        compositeDisposable.add(BaseApiServiceHelper.getCommentsList(articleId).subscribe(new BaseConsumer<BaseBean<List<CommentsBean>>>() {
            @Override
            protected void onSuccessData(BaseBean<List<CommentsBean>> result) {
                if(result.isRequestSuccess()){
                    List<CommentsBean> data = result.getData();
                    if(data!=null){
                        mView.getCommentsList(data);
                    }

                }
            }
        }));

    }


}
