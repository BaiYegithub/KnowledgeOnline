package heqi.online.com.main.activity;

import heqi.online.com.MainActivity;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;

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
        openActivity(MainActivity.class);
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
