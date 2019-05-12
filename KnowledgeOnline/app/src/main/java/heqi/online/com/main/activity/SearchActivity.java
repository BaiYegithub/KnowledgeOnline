package heqi.online.com.main.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;

/**
 * Created by Administrator on 2019/5/12.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.et_search_layout)
    EditText etSearchLayout;
    @BindView(R.id.bt_search_layout)
    Button btSearchLayout;
    @BindView(R.id.ll_search_out)
    LinearLayout llSearchOut;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected int initContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViewAndData() {

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
    

    @OnClick(R.id.bt_search_layout)
    public void onViewClicked() {
    }
}
