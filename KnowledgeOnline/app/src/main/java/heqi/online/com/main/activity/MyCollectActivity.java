package heqi.online.com.main.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.inter.OnDeleteClickLister;
import heqi.online.com.main.adapter.BaseRecyclerViewAdapter;
import heqi.online.com.main.adapter.CollectAdapter;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.MsgCollectBean;
import heqi.online.com.main.inter.IArticleDetail;
import heqi.online.com.main.inter.IHomePageArticle;
import heqi.online.com.main.presenter.ArticleDetailPresenter;
import heqi.online.com.main.presenter.HomePagePresenter;
import heqi.online.com.utils.UIUtils;
import heqi.online.com.view.SlideRecyclerView;

/**
 * Created by Administrator on 2019/4/27.
 */

public class MyCollectActivity extends BaseActivity implements IHomePageArticle, IArticleDetail {
    @BindView(R.id.iv_back_titlebar)
    ImageView ivBackTitlebar;
    @BindView(R.id.tv_center_titlebar)
    TextView tvCenterTitlebar;
    @BindView(R.id.tv_right_titlebar)
    TextView tvRightTitlebar;
    @BindView(R.id.iv_right_titlebar)
    ImageView ivRightTitlebar;
    @BindView(R.id.relativeLayout_titleBar)
    RelativeLayout relativeLayoutTitleBar;
    @BindView(R.id.slide_recyclerView)
    SlideRecyclerView slideRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private HomePagePresenter homePagePresenter;

    private int currentPage = 1;//当前页默认为1
    List<HomePageBean.DataBean> dataBeanList = new ArrayList<>();
    private CollectAdapter collectAdapter;
    private boolean canLoadMore;//是否能够加载更多
    private ArticleDetailPresenter articleDetailPresenter;

    private int delPosition;

    @Override
    protected int initContentView() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initViewAndData() {

        EventBus.getDefault().register(this);

        tvCenterTitlebar.setText("我的收藏");
        slideRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        collectAdapter = new CollectAdapter(this, dataBeanList, R.layout.item_article);
        slideRecyclerView.setAdapter(collectAdapter);
        //自动刷新
        homePagePresenter = new HomePagePresenter(this, this);
        refreshLayout.autoRefresh();

        articleDetailPresenter = new ArticleDetailPresenter(this, this);
    }

    @Override
    protected void initHttp() {


    }

    @Override
    protected void initListener() {
        //刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                currentPage = 1;
                homePagePresenter.getMyCollection(currentPage, 20);
            }
        });
        //上拉加载更多监听
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (canLoadMore) {
                    currentPage++;
                    homePagePresenter.getMyCollection(currentPage, 20);
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });

        collectAdapter.setOnDeleteClickListener(new OnDeleteClickLister() {
            @Override
            public void onDeleteClick(View view, int position) {
                articleDetailPresenter.collectOrNotArticle(false, UIUtils.getUid(), dataBeanList.get(position).getArticleId());
                delPosition = position;
            }
        });

        collectAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.Adapter adapter, View v, int position) {
                ArticleDetailActivity.navToArticleDetailActivity(MyCollectActivity.this, dataBeanList.get(position));
            }
        });
    }

    @Subscribe
    public void OnMesEvent(MsgCollectBean msgCollectBean) {
        if (msgCollectBean.isChange) {//如果改变了
            refreshLayout.autoRefresh();
        }
    }

    @Override
    protected void destroyResources() {
        EventBus.getDefault().unregister(this);
    }


    @OnClick(R.id.iv_back_titlebar)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getHomePageBean(HomePageBean data) {
        List<HomePageBean.DataBean> beanList = data.getData();
        if (currentPage == 1) {
            dataBeanList.clear();
            dataBeanList.addAll(beanList);
            collectAdapter.notifyDataSetChanged();
            refreshLayout.finishRefresh();
        } else {
            dataBeanList.addAll(beanList);
            collectAdapter.notifyDataSetChanged();
            refreshLayout.finishLoadMore();
        }

        if (data.getTotalPage() >= currentPage) {
            canLoadMore = true;
        } else {
            canLoadMore = false;
        }

    }

    @Override
    public void getArticleDetail(HomePageBean.DataBean data) {

    }

    @Override
    public void ClickSuccess(boolean isCollect) {
        dataBeanList.remove(delPosition);
        collectAdapter.notifyDataSetChanged();
        slideRecyclerView.closeMenu();
    }
}
