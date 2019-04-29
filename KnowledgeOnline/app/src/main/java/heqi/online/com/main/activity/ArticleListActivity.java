package heqi.online.com.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.fragment.HomePageFragment;

/**
 * Created by Administrator on 2019/4/29.
 */

public class ArticleListActivity extends BaseActivity {
    @BindView(R.id.frameLayout_acArticleList)
    FrameLayout frameLayoutAcArticleList;
    private String fid;

    @Override
    protected int initContentView() {
        return R.layout.activity_articlelist;
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        Intent intent = getIntent();
        fid = intent.getStringExtra("fid");
    }

    @Override
    protected void initViewAndData() {

        HomePageFragment homePageFragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("isPerson", 1);
        bundle.putString("fid",fid);
        homePageFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_acArticleList, homePageFragment).commit();
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
