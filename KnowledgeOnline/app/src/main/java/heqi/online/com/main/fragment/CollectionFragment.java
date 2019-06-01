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
import heqi.online.com.main.bean.WxArticlesBelongBean;
import heqi.online.com.main.inter.IWxArticles;
import heqi.online.com.main.presenter.WxArticlesPresenter;

/**
 * Created by Administrator on 2019/4/6.附着在activity的视图
 */

public class CollectionFragment extends BaseFragment implements IWxArticles {

    @BindView(R.id.tab_fragCollect)
    TabLayout tabFragCollect;
    @BindView(R.id.vp_fragCollect)
    ViewPager vpFragCollect;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_collection;
    }

    @Override
    protected void initViewAndData(View view, Bundle savedInstanceState) {
        setSystemBarPadding(getActivity(),tabFragCollect);
    }

    @Override
    protected void initHttp() {
        WxArticlesPresenter wxArticlesPresenter = new WxArticlesPresenter(this, this);
        wxArticlesPresenter.getWxArticles();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }

    @Override
    public void getWxArticlesList(List<WxArticlesBelongBean> data) {

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("id", data.get(i).getId());
            fragmentList.add(WxArticleFragment.newInstance(bundle));
            stringList.add(data.get(i).getName());
        }

        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), fragmentList, stringList);
        vpFragCollect.setAdapter(vpAdapter);
        tabFragCollect.setupWithViewPager(vpFragCollect);
    }

}
