package heqi.online.com.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseFragment;
import heqi.online.com.main.activity.WriteArticleActivity;

/**
 * Created by Administrator on 2019/4/6.
 */

public class HomePageFragment extends BaseFragment {
    @BindView(R.id.tv_write_fragHome)
    ImageView tvWriteFragHome;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_homepage;
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {

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
