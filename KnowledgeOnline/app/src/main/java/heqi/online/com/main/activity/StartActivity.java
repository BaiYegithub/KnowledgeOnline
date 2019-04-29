package heqi.online.com.main.activity;

import heqi.online.com.MainActivity;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.login.LoginActivity;
import heqi.online.com.utils.ConstantUtil;
import heqi.online.com.utils.SharedPreferenceUtils;

/**
 * Created by Administrator on 2019/4/21.
 */

public class StartActivity extends BaseActivity {
    @Override
    protected int initContentView() {
        return R.layout.activity_start;
    }

    @Override
    protected void initViewAndData() {
        boolean isLogin = (boolean) SharedPreferenceUtils.get(ConstantUtil.isLogin, false);
        if (isLogin) {
            openActivity(MainActivity.class);
        } else {
            openActivity(LoginActivity.class);
        }

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
