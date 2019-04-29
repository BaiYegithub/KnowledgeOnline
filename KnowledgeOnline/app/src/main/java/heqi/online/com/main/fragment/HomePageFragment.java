package heqi.online.com.main.fragment;

import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import heqi.online.com.R;
import heqi.online.com.base.BaseFragment;
import heqi.online.com.main.activity.ArticleDetailActivity;
import heqi.online.com.main.activity.WriteArticleActivity;
import heqi.online.com.main.adapter.ArticlesAdapter;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.MsgPublishBean;
import heqi.online.com.main.inter.IHomePageArticle;
import heqi.online.com.main.presenter.HomePagePresenter;

/**
 * Created by Administrator on 2019/4/6.
 */

public class HomePageFragment extends BaseFragment implements IHomePageArticle {
    //文本编辑按钮
    @BindView(R.id.tv_write_fragHome)
    ImageView tvWriteFragHome;
    //返回按钮
    @BindView(R.id.iv_back_titlebar)
    ImageView ivBackTitlebar;
    //titleBar 中间文字
    @BindView(R.id.tv_center_titlebar)
    TextView tvCenterTitlebar;
    //右侧文字
    @BindView(R.id.tv_right_titlebar)
    TextView tvRightTitlebar;
    //titleBar 的外层RelativeLayout
    @BindView(R.id.relativeLayout_titleBar)
    RelativeLayout relativeLayoutTitleBar;
    //列表
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //刷新控件
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArticlesAdapter articlesAdapter;

    private int currentPage = 1;
    private boolean canLoadMore = false;//默认不能加载更多
    private HomePagePresenter homePagePresenter;
    private int isPerson = 0;
    private String fid = "";

    @Override
    protected int getLayoutId() {
        return R.layout.frag_homepage;
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {
        setSystemBarPadding(getActivity(), relativeLayoutTitleBar);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isPerson = (int) bundle.get("isPerson");
            fid = (String) bundle.get("fid");
        }

        EventBus.getDefault().register(this);

        ivBackTitlebar.setVisibility(View.GONE);
        tvCenterTitlebar.setText("文章推荐");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        articlesAdapter = new ArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);

        refreshLayout.autoRefresh();
    }

    @Override
    protected void initHttp() {
        homePagePresenter = new HomePagePresenter(this, this);

    }

    @Subscribe
    public void OnMsgEvent(MsgPublishBean msgPublishBean){
        if(msgPublishBean.isChange){
            refreshLayout.autoRefresh();
        }
    }

    @Override
    protected void initListener() {
        //设置下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                currentPage = 1;
                if (isPerson == 1) {
                    homePagePresenter.getPublishArticles(fid, currentPage, 20);
                } else {
                    homePagePresenter.getHomePageArticles(currentPage, 20);
                }

            }
        });
        //设置上拉加载更多监听
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (canLoadMore) {
                    currentPage++;
                    if (isPerson == 1) {
                        homePagePresenter.getPublishArticles(fid, currentPage, 20);
                    } else {
                        homePagePresenter.getHomePageArticles(currentPage, 20);
                    }
                } else {
                    showToast("没有更多数据了");
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });

        articlesAdapter.setOnItemClickListener(new ArticlesAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(HomePageBean.DataBean dataBean) {
                ArticleDetailActivity.navToArticleDetailActivity(getActivity(), dataBean);
            }
        });
    }

    @Override
    protected void destroyResources() {
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.tv_write_fragHome)
    public void onViewClicked() {
        openActivity(WriteArticleActivity.class);
    }

    //获取首页推荐文章列表
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
}
