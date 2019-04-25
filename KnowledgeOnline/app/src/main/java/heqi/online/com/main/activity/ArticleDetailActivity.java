package heqi.online.com.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.inter.IArticleDetail;
import heqi.online.com.main.presenter.ArticleDetailPresenter;
import heqi.online.com.utils.UIUtils;
import heqi.online.com.utils.WebViewHelper;

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

    private boolean isCollected;//判断是否已收藏

    private String aid;

    private String url;
    private HomePageBean.DataBean dataBean;
    private ArticleDetailPresenter articleDetailPresenter;


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
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleDetailPresenter.collectOrNotArticle(!isCollected,UIUtils.getUid(),aid);
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


    @OnClick(R.id.iv_back_titlebar)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getArticleDetail(HomePageBean.DataBean data) {
        aid = data.getArticleId();
        if (data.getArticleContent().startsWith("https")) {
            wbArticleAcArticle.loadUrl(data.getArticleContent());
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(data.getTitle());
            wbArticleAcArticle.loadData(data.getArticleContent(), "text/html", null);
        }

        if (data.getStatus() == 0) { //未收藏
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collect));
            isCollected = false;
        } else {
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collected));
            isCollected = true;
        }
    }

    @Override
    public void ClickSuccess(boolean isCollect) {
        if(isCollect){
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collected));
            isCollected = true;
        }else {
            iv_right.setImageDrawable(getResources().getDrawable(R.drawable.iv_collect));
            isCollected = false;
        }
    }
}
