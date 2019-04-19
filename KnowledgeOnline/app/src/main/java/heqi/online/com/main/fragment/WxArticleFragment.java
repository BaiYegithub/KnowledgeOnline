package heqi.online.com.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import heqi.online.com.R;
import heqi.online.com.base.LazyBaseFragment;
import heqi.online.com.main.activity.ArticleDetailActivity;
import heqi.online.com.main.adapter.WxArticlesAdapter;
import heqi.online.com.main.bean.WxArticlesListBean;
import heqi.online.com.main.inter.IWxArticlesList;
import heqi.online.com.main.presenter.WxArticlesListPresenter;

/**
 * author : by
 * date: 2019/4/19 0019  上午 11:30.
 * describe
 */

public class WxArticleFragment extends LazyBaseFragment implements IWxArticlesList {
    //recyclerView  刷新列表
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //刷新控件
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private WxArticlesAdapter wxArticlesAdapter;
    private int id;

    private int curPage = 1;
    private WxArticlesListPresenter wxArticlesListPresenter;
    private boolean canLoadMore = false;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_wx_articles;
    }

    public static Fragment newInstance(Bundle bundle) {

        WxArticleFragment wxArticleFragment = new WxArticleFragment();
        wxArticleFragment.setArguments(bundle);
        return wxArticleFragment;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        //自动刷新
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {

        wxArticlesListPresenter = new WxArticlesListPresenter(this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        wxArticlesAdapter = new WxArticlesAdapter();
        recyclerView.setAdapter(wxArticlesAdapter);

        Bundle bundle = getArguments();
        id = bundle.getInt("id");

    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        //下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                curPage = 1;
                wxArticlesListPresenter.getWxArticleList(id, curPage);
            }
        });
        //加载更多监听
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (canLoadMore) {
                    curPage++;
                    wxArticlesListPresenter.getWxArticleList(id, curPage);
                }else {
                    refreshLayout.finishLoadMore();
                }
            }
        });

        wxArticlesAdapter.setOnItemClickListener(new WxArticlesAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String url) {
                ArticleDetailActivity.navToArticleDetailActivity(getActivity(),url);
            }
        });

    }

    @Override
    protected void destroyResources() {

    }

    @Override
    public void getWxArticlesList(WxArticlesListBean data) {
        if (data.getTotal() >= curPage) {
            canLoadMore = true;
        }
        List<WxArticlesListBean.DatasBean> datas = data.getDatas();
        if (curPage == 1) {
            wxArticlesAdapter.refreshList(datas);
            refreshLayout.finishRefresh();//更新完成
        } else {
            wxArticlesAdapter.addDataList(datas);
            refreshLayout.finishLoadMore();
        }
    }
}
