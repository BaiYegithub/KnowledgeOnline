package heqi.online.com;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import heqi.online.com.adapter.MainPagerAdapter;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.fragment.CollectionFragment;
import heqi.online.com.main.fragment.FocousFragment;
import heqi.online.com.main.fragment.HomePageFragment;
import heqi.online.com.main.fragment.MyselfFragment;
import heqi.online.com.view.MainRadioButton;
import heqi.online.com.view.NoScrollViewPager;

public class MainActivity extends BaseActivity {

    //viewPager
    @BindView(R.id.vp_mainac)
    NoScrollViewPager vpMainac;
    //首页按钮
    @BindView(R.id.main_homepage)
    MainRadioButton mainHomepage;
    //
    @BindView(R.id.main_focus)
    MainRadioButton mainFocus;
    @BindView(R.id.main_study)
    MainRadioButton mainStudy;
    @BindView(R.id.main_user_self)
    MainRadioButton mainUserSelf;
    @BindView(R.id.main_radio_group)
    RadioGroup mainRadioGroup;

    MainPagerAdapter pagerAdapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewAndData() {
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), listFragment());
        vpMainac.setAdapter(pagerAdapter);
        vpMainac.setOffscreenPageLimit(4);
    }

    private List<Fragment> listFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new FocousFragment());
        fragmentList.add(new CollectionFragment());
        fragmentList.add(new MyselfFragment());
        return fragmentList;
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        //radioGroup加选择监听
        mainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.main_homepage: //首页
                        vpMainac.setCurrentItem(0, false);
                        break;
                    case R.id.main_focus: //课程
                        vpMainac.setCurrentItem(1, false);
                        break;
                    case R.id.main_study: //学习
                        vpMainac.setCurrentItem(2, false);
                        break;
                    case R.id.main_user_self: //我的
                        vpMainac.setCurrentItem(3, false);
                        break;
                    default:
                        vpMainac.setCurrentItem(0, false);
                        break;
                }
            }
        });
    }

    @Override
    protected void destroyResources() {

    }

}
