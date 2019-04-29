package heqi.online.com.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.adapter.CommentAdapter;
import heqi.online.com.main.bean.CommentsBean;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.MsgCollectBean;
import heqi.online.com.main.inter.IArticleDetail;
import heqi.online.com.main.presenter.ArticleDetailPresenter;
import heqi.online.com.utils.UIUtils;
import heqi.online.com.utils.WebViewHelper;
import heqi.online.com.view.ReplyDialog;

public class ArticleDetailActivity extends BaseActivity implements IArticleDetail {

    //返回按钮
    @BindView(R.id.iv_back_titlebar)
    ImageView ivBackTitlebar;
    //title 中间文字
    @BindView(R.id.tv_center_titlebar)
    TextView tvCenterTitlebar;
    //右侧文字
    @BindView(R.id.tv_right_titlebar)
    TextView tvRightTitlebar;
    //relative
    @BindView(R.id.relativeLayout_titleBar)
    RelativeLayout relativeLayoutTitleBar;
    //进度条
    @BindView(R.id.progressbar_acJiangYiPlay)
    ProgressBar progressbarAcJiangYiPlay;
    //webView
    @BindView(R.id.wb_article_acArticle)
    WebView wbArticleAcArticle;

    //标题文字，默认隐藏
    @BindView(R.id.tv_title_acArticleDetail)
    TextView tvTitle;

    //title 右侧图片
    @BindView(R.id.iv_right_titlebar)
    ImageView iv_right;
    //用户名称
    @BindView(R.id.tv_author_acDetail)
    TextView tvAuthor;
    //确认按钮
    @BindView(R.id.bt_confirm_acDetail)
    Button btConfirm;
    //关注
    @BindView(R.id.rlv_acDetail)
    RelativeLayout rlvAcDetail;
    //评论输入框
    @BindView(R.id.et_comments)
    EditText etComments;
    @BindView(R.id.rcv_comments_acArticle)
    RecyclerView rcvCommentsAcArticle;

    private boolean isCollected;//判断是否已收藏

    private String aid;

    private String fid;
    private String url;

    private HomePageBean.DataBean dataBean;
    private ArticleDetailPresenter articleDetailPresenter;
    private boolean isFocus;
    private CommentAdapter commentAdapter;
    private ReplyDialog replyDialog;

    private CommentsBean myCommentsBean;


    /**
     * @param context 上下文
     * @param url     地址
     *                跳转到本Activity
     */
    public static void navToArticleDetailActivity(Context context, String url) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void navToArticleDetailActivity(Context context, HomePageBean.DataBean databean) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("dataBean", databean);
        context.startActivity(intent);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        dataBean = (HomePageBean.DataBean) intent.getSerializableExtra("dataBean");

        replyDialog = new ReplyDialog(ArticleDetailActivity.this);

    }

    @Override
    protected void initViewAndData() {

        tvCenterTitlebar.setText("文章详情");
        iv_right.setVisibility(View.VISIBLE);
        WebSettings wbSettings = wbArticleAcArticle.getSettings();
        wbSettings.setJavaScriptEnabled(true);
        wbSettings.setAllowFileAccess(true);
        wbArticleAcArticle.setWebViewClient(WebViewHelper.getwebviewclient(progressbarAcJiangYiPlay)); //显示进度条
        wbArticleAcArticle.setWebChromeClient(WebViewHelper.getwebchrome(progressbarAcJiangYiPlay));
        //支持屏幕缩放
        wbSettings.setSupportZoom(true);
        wbSettings.setBuiltInZoomControls(true);
        wbSettings.setDisplayZoomControls(false); //隐藏缩放控件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            wbSettings.setAllowFileAccessFromFileURLs(true);
            wbSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (url != null) {
            wbArticleAcArticle.loadUrl(url);
        }
        articleDetailPresenter = new ArticleDetailPresenter(this, this);
        if (dataBean != null) {
            articleDetailPresenter.getArticleDetail(dataBean.getArticleId());
        }


        rcvCommentsAcArticle.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter();
        rcvCommentsAcArticle.setAdapter(commentAdapter);


    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleDetailPresenter.collectOrNotArticle(!isCollected, UIUtils.getUid(), aid);
            }
        });
        //关注按钮的点击事件
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleDetailPresenter.FocusUserOrNot(isFocus, fid);
            }
        });

        //长按事件
        commentAdapter.setOnItemLongClick(new CommentAdapter.OnItemLongClick() {
            @Override
            public void OnItemLongClickListener(CommentsBean commentsBean) {
                myCommentsBean = commentsBean;
                replyDialog.show();
            }
        });

        replyDialog.setOnClickListener(new ReplyDialog.OnClickListener() {
            @Override
            public void onReply(String strEt) {
                if (myCommentsBean != null) {
                    articleDetailPresenter.replyInsert(myCommentsBean.getFromUid(), myCommentsBean.getFromUid(), "reply", myCommentsBean.getFromUid(), strEt);
                }
            }

            @Override
            public void onDelete() {
                articleDetailPresenter.deleteComment(aid, myCommentsBean.getId());
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (wbArticleAcArticle != null) {

            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = wbArticleAcArticle.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(wbArticleAcArticle);
            }

            wbArticleAcArticle.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            wbArticleAcArticle.getSettings().setJavaScriptEnabled(false);
            wbArticleAcArticle.clearHistory();
            wbArticleAcArticle.clearView();
            wbArticleAcArticle.removeAllViews();
            wbArticleAcArticle.destroy();

        }
        super.onDestroy();
    }

    @Override
    protected void destroyResources() {

    }


    @Override
    public void getArticleDetail(HomePageBean.DataBean data) {
        aid = data.getArticleId();
        fid = data.getLoginAccount();
        if (data.getArticleContent().startsWith("https")) {
            wbArticleAcArticle.loadUrl(data.getArticleContent());
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(data.getTitle());
            wbArticleAcArticle.loadData(data.getArticleContent(), "text/html", null);
        }
        rlvAcDetail.setVisibility(View.VISIBLE);

        if (data.getStatus() == 0) { //未收藏
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collect));
            isCollected = false;
        } else {
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collected));
            isCollected = true;
        }

        if (data.getFocus() == 0) { //未关注
            btConfirm.setText("未关注");
            isFocus = false;
        } else {//已关注
            btConfirm.setText("已关注");
            isFocus = true;
        }

        tvAuthor.setText("作者:" + data.getAuthor());
        articleDetailPresenter.getCommentList(aid);
    }

    @Override
    public void ClickSuccess(boolean isCollect) {
        if (isCollect) {
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collected));
            isCollected = true;
            showToast("收藏成功");
        } else {
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collect));
            isCollected = false;
            showToast("取消收藏成功");
            EventBus.getDefault().post(new MsgCollectBean(true));
        }
    }

    @Override
    public void changeFocus(boolean b) {
        //改变focus的状态
        isFocus = b;
        btConfirm.setText(isFocus ? "已关注" : "未关注");
    }

    @Override
    public void insertSuccess() {
        etComments.setText("");
        replyDialog.dismiss();
        articleDetailPresenter.getCommentList(aid);
    }

    @Override
    public void getCommentsList(List<CommentsBean> data) {
        commentAdapter.refreshList(data);
    }


    @OnClick({R.id.iv_back_titlebar, R.id.bt_commit_acArticle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_titlebar:
                finish();
                break;
            case R.id.bt_commit_acArticle:
                articleDetailPresenter.insertComments(aid, etComments.getText().toString());
                break;
        }
    }

}
