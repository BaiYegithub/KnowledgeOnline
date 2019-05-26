package heqi.online.com.main.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.adapter.ArticlesAdapter;
import heqi.online.com.main.bean.ArticleTypeBean;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.WanBannerBean;
import heqi.online.com.main.inter.IHomePageArticle;
import heqi.online.com.main.presenter.HomePagePresenter;

/**
 * Created by Administrator on 2019/5/12.
 */

public class SearchActivity extends BaseActivity implements IHomePageArticle {
    @BindView(R.id.et_search_layout)
    EditText etSearchLayout;
    @BindView(R.id.bt_search_layout)
    Button btSearchLayout;
    @BindView(R.id.ll_search_out)
    LinearLayout llSearchOut;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private boolean canLoadMore = false;//默认不能加载更多
    ArticlesAdapter articlesAdapter;
    private HomePagePresenter homePagePresenter;

    private int currentPage = 1;

    @Override
    protected int initContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViewAndData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articlesAdapter = new ArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);
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
                currentPage = 1;
                homePagePresenter.getHomePageArticles(etSearchLayout.getText().toString(), null, currentPage, 20);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if(canLoadMore){
                    currentPage++;
                    homePagePresenter.getHomePageArticles(etSearchLayout.getText().toString(),null,currentPage,20);
                }else {
                    showToast("没有更多数据了");
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });

        articlesAdapter.setOnItemClickListener(new ArticlesAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(HomePageBean.DataBean dataBean) {
                ArticleDetailActivity.navToArticleDetailActivity(SearchActivity.this, dataBean);
            }
        });
    }

    @Override
    protected void destroyResources() {

    }


    @OnClick(R.id.bt_search_layout)
    public void onViewClicked() {
        refreshLayout.autoRefresh();
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
