package heqi.online.com.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseFragment;
import heqi.online.com.main.activity.WriteArticleActivity;

/**
 * Created by Administrator on 2019/4/6.
 */

public class HomePageFragment extends BaseFragment {
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

    @Override
    protected int getLayoutId() {
        return R.layout.frag_homepage;
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {
        setSystemBarPadding(getActivity(), relativeLayoutTitleBar);
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }

    @OnClick(R.id.tv_write_fragHome)
    public void onViewClicked() {
        openActivity(WriteArticleActivity.class);
    }

}
