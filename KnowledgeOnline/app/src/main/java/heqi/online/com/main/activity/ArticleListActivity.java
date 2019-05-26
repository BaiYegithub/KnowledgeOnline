package heqi.online.com.main.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.adapter.ArticlesAdapter;
import heqi.online.com.main.bean.ArticleTypeBean;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.WanBannerBean;
import heqi.online.com.main.inter.IHomePageArticle;
import heqi.online.com.main.presenter.HomePagePresenter;
import heqi.online.com.view.TitleBar;

/**
 * Created by Administrator on 2019/4/29.
 */

public class ArticleListActivity extends BaseActivity implements IHomePageArticle {

    @BindView(R.id.titlebar_acArticleList)
    TitleBar titlebarAcArticleList;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String fid;
    private HomePagePresenter homePagePresenter;
    private boolean canLoadMore = false;//默认不能加载更多
    private int currentPage = 1;

    private ArticlesAdapter articlesAdapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_articlelist;
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        Intent intent = getIntent();
        fid = intent.getStringExtra("fid");
    }

    @Override
    protected void initViewAndData() {
        titlebarAcArticleList.setTv_center("Ta发布的");

        recyclerView.setLayoutManager(new LinearLayoutManager(ArticleListActivity.this));

        articlesAdapter = new ArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);

        refreshLayout.autoRefresh();

    }

    @Override
    protected void initHttp() {
        homePagePresenter = new HomePagePresenter(this, this);
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                currentPage=1;
                homePagePresenter.getPublishArticles(fid, currentPage, 20);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if(canLoadMore){
                    currentPage++;
                    homePagePresenter.getPublishArticles(fid,currentPage,20);
                }else {
                    showToast("没有更多数据了");
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });
        articlesAdapter.setOnItemClickListener(new ArticlesAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(HomePageBean.DataBean dataBean) {
                ArticleDetailActivity.navToArticleDetailActivity(ArticleListActivity.this, dataBean);
            }
        });

    }

    @Override
    protected void destroyResources() {

    }

    @Override
    public void getHomePageBean(HomePageBean data) {
        if (data.getTotalPage() >= currentPage) { //判断是否能够加载更多
            canLoadMore = true;
        }

        if (currentPage == 1) {
            refreshLayout.finishRefresh();
            articlesAdapter.refreshList(data.getData());
        } else {
            refreshLayout.finishLoadMore();
            articlesAdapter.addDataList(data.getData());
        }
    }

    @Override
    public void getBannerList(List<WanBannerBean> data) {

    }

    @Override
    public void getArticleTypes(List<ArticleTypeBean> data) {

    }
}
