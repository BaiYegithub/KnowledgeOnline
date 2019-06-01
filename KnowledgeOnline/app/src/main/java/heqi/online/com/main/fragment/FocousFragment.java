package heqi.online.com.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import heqi.online.com.R;
import heqi.online.com.base.BaseFragment;
import heqi.online.com.main.adapter.VpAdapter;

/**
 * Created by Administrator on 2019/4/6.
 */

public class FocousFragment extends BaseFragment {
    //tablayout切换控件
    @BindView(R.id.tab_fragFocus)
    TabLayout tabFragFocus;
    //viewpager布局切换控件
    @BindView(R.id.vp_fragFocus)
    ViewPager vpFragFocus;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_focous;
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {

        setSystemBarPadding(getActivity(), tabFragFocus);

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        stringList.add("关注我的");
        stringList.add("我关注的");
        Bundle bundle1 = new Bundle();
        bundle1.putInt("from", 1);
        fragmentList.add(FocousListFragment.newInstance(bundle1));

        Bundle bundle2 = new Bundle();
        bundle2.putInt("from", 2);
        fragmentList.add(FocousListFragment.newInstance(bundle2));

        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), fragmentList, stringList);
        vpFragFocus.setAdapter(vpAdapter);
        tabFragFocus.setupWithViewPager(vpFragFocus);
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

}
