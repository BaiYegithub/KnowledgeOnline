package heqi.online.com.main.activity;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.bean.CourseBean;
import heqi.online.com.main.inter.ICourseList;
import heqi.online.com.main.presenter.CourseListPresenter;
import heqi.online.com.view.SlideRecyclerView;

/**
 * Created by Administrator on 2019/4/27.
 */

public class CourseListActivity extends BaseActivity implements ICourseList {

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
    private CourseListPresenter courseListPresenter;

    @Override
    protected int initContentView() {
        return R.layout.activity_courselist;
    }

    @Override
    protected void initViewAndData() {
        courseListPresenter = new CourseListPresenter(this, this);
        courseListPresenter.getCourseList(1, 20);
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }
        });
    }

    @Override
    protected void destroyResources() {

    }

    @Override
    public void getCourse(CourseBean data) {

    }


    @OnClick(R.id.iv_back_titlebar)
    public void onViewClicked() {
        finish();
    }
}
