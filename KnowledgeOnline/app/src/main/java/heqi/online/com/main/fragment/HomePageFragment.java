package heqi.online.com.main.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.CustomViewPager;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import heqi.online.com.R;
import heqi.online.com.base.BaseFragment;
import heqi.online.com.main.activity.ArticleDetailActivity;
import heqi.online.com.main.activity.SearchActivity;
import heqi.online.com.main.activity.WriteArticleActivity;
import heqi.online.com.main.adapter.ArticlesAdapter;
import heqi.online.com.main.adapter.CourseSlidingAdapter;
import heqi.online.com.main.bean.ArticleTypeBean;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.MsgPublishBean;
import heqi.online.com.main.bean.SlidingBean;
import heqi.online.com.main.bean.WanBannerBean;
import heqi.online.com.main.inter.IHomePageArticle;
import heqi.online.com.main.presenter.HomePagePresenter;
import heqi.online.com.utils.UIUtils;
import heqi.online.com.view.BannerViewHolder;

/**
 * Created by Administrator on 2019/4/6.
 */

public class HomePageFragment extends BaseFragment implements IHomePageArticle {
    //文本编辑按钮
    @BindView(R.id.tv_write_fragHome)
    ImageView tvWriteFragHome;

    //列表
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //刷新控件
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.et_search_layout)
    TextView etSearchLayout;
    @BindView(R.id.ll_search_out)
    LinearLayout llSearchOut;
    @BindView(R.id.banner_homeFragment)
    MZBannerView mMZBanner;
    @BindView(R.id.bt_select_layout)
    Button btSelectLayout;

    @BindView(R.id.drawerLayout_fragHomepage)
    DrawerLayout drawerLayout;
    @BindView(R.id.rcv_draw_fragHomePage)
    RecyclerView rcvDrawFragHomePage;
    @BindView(R.id.tvReset_sliding_courseFragment)
    TextView tvResetSlidingCourseFragment;
    @BindView(R.id.tvSure_sliding_courseFragment)
    TextView tvSureSlidingCourseFragment;

    private ArticlesAdapter articlesAdapter;

    private int currentPage = 1;
    private boolean canLoadMore = false;//默认不能加载更多
    private HomePagePresenter homePagePresenter;
    private int isPerson = 0;
    private String fid = "";
    private CourseSlidingAdapter courseSlidingAdapter;
    private ArrayList<SlidingBean> list;
    private String strType;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_homepage;
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {
        setSystemBarPadding(getActivity(), llSearchOut);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isPerson = (int) bundle.get("isPerson");
            fid = (String) bundle.get("fid");
        }

        //这一块是修改banner内部的ViewPager样式
        int childCount = mMZBanner.getChildCount();
        if (childCount > 0) {
            View view1 = mMZBanner.getChildAt(0);
            CustomViewPager viewPager = view1.findViewById(R.id.mzbanner_vp);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(viewPager.getLayoutParams());
            params.setMargins(UIUtils.dip2px(15), 0, UIUtils.dip2px(15), 0);
            viewPager.setLayoutParams(params);
        }

        EventBus.getDefault().register(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        articlesAdapter = new ArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);

        refreshLayout.autoRefresh();

        rcvDrawFragHomePage.setLayoutManager(new GridLayoutManager(UIUtils.getContext(), 3));
        courseSlidingAdapter = new CourseSlidingAdapter();
        rcvDrawFragHomePage.setAdapter(courseSlidingAdapter);

    }

    @Override
    protected void initHttp() {
        homePagePresenter = new HomePagePresenter(this, this);
        homePagePresenter.getBannerList();
        homePagePresenter.getTypes();
    }

    @Subscribe
    public void OnMsgEvent(MsgPublishBean msgPublishBean) {
        if (msgPublishBean.isChange) {
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
                    homePagePresenter.getHomePageArticles(null, strType, currentPage, 20);
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
                        homePagePresenter.getHomePageArticles(null, null, currentPage, 20);
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

        etSearchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SearchActivity.class);
            }
        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                strType = courseSlidingAdapter.getChoiceCode();
                strType = strType.substring(1, strType.length()-1);
                refreshLayout.autoRefresh();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void destroyResources() {
        EventBus.getDefault().unregister(this);
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

    @Override
    public void getBannerList(List<WanBannerBean> data) {
        mMZBanner.setPages(data, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.start();

    }

    @Override
    public void getArticleTypes(List<ArticleTypeBean> data) {
        list = new ArrayList();

        list.add(new SlidingBean(1, 1, "123", "文章类型", false));
        for (int i = 0; i < data.size(); i++) {
            list.add(new SlidingBean(2, data.get(i).getId(), data.get(i).getTypeCode(), data.get(i).getTypeContent(), false));
        }
        courseSlidingAdapter.setData(list);
    }

    @OnClick({R.id.bt_select_layout, R.id.tv_write_fragHome, R.id.tvReset_sliding_courseFragment, R.id.tvSure_sliding_courseFragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_select_layout:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_write_fragHome:
                openActivity(WriteArticleActivity.class);
                break;
            case R.id.tvReset_sliding_courseFragment:
                courseSlidingAdapter.resetSlidingList();
                break;
            case R.id.tvSure_sliding_courseFragment:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }
}
