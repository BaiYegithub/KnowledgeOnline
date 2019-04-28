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

import butterknife.BindView;
import heqi.online.com.R;
import heqi.online.com.base.BaseFragment;
import heqi.online.com.main.adapter.FocusAdapter;
import heqi.online.com.main.bean.FocusBean;
import heqi.online.com.main.inter.IGetFocus;
import heqi.online.com.main.presenter.GetFocusPresenter;

/**
 * Created by Administrator on 2019/4/28.
 */

public class FocousListFragment extends BaseFragment implements IGetFocus {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int from;
    private FocusAdapter focusAdapter;

    private int curPage = 1;
    private GetFocusPresenter getFocusPresenter;

    private boolean canLoadMore = false;

    public static Fragment newInstance(Bundle bundle) {
        FocousListFragment focousListFragment = new FocousListFragment();
        focousListFragment.setArguments(bundle);
        return focousListFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_focuslist;
    }


    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        from = bundle.getInt("from");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        focusAdapter = new FocusAdapter();
        recyclerView.setAdapter(focusAdapter);
        getFocusPresenter = new GetFocusPresenter(this, this);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                curPage = 1;
                getFocusPresenter.getFocusList(from, curPage, 20);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (canLoadMore) {
                    curPage++;
                    getFocusPresenter.getFocusList(from, curPage, 20);
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });
    }

    @Override
    protected void destroyResources() {

    }


    @Override
    public void getFocusBean(FocusBean data) {

        if (curPage <= data.getTotalPage()) {
            canLoadMore = true;
        } else {
            canLoadMore = false;
        }

        if (curPage == 1) {
            focusAdapter.refreshList(data.getData());
            refreshLayout.finishRefresh();
        } else {
            focusAdapter.addList(data.getData());
            refreshLayout.finishLoadMore();
        }
    }
}
