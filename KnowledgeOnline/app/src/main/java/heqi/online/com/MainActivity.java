package heqi.online.com;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.RadioGroup;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import heqi.online.com.adapter.MainPagerAdapter;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.fragment.CollectionFragment;
import heqi.online.com.main.fragment.FocousFragment;
import heqi.online.com.main.fragment.HomePageFragment;
import heqi.online.com.main.fragment.MyselfFragment;
import heqi.online.com.utils.ConstantUtil;
import heqi.online.com.utils.SharedPreferenceUtils;
import heqi.online.com.view.MainRadioButton;
import heqi.online.com.view.NoScrollViewPager;
/*登录成功进入主界面，包含4个fragment，附着在activity上的视图(轻量级的碎片)*/
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
        TIMManager.getInstance().login((String) SharedPreferenceUtils.get(ConstantUtil.LoginAccount,""), (String) SharedPreferenceUtils.get(ConstantUtil.UrlSig,""), new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d("baiye", "login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.d("baiye", "login succ");
            }
        });
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
