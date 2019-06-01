package heqi.online.com.main.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.bean.FocusBean;
import heqi.online.com.utils.GlideUtil;
import heqi.online.com.view.CircleImageView;

/**
 * Created by Administrator on 2019/4/28. 用户信息详情页
 */

public class PersonActivity extends BaseActivity {

    @BindView(R.id.iv_back_titlebar)
    ImageView ivBackTitlebar;
    @BindView(R.id.tv_center_titlebar)
    TextView tvCenterTitlebar;
    @BindView(R.id.tv_right_titlebar)
    TextView tvRightTitlebar;
    @BindView(R.id.iv_right_titlebar)
    ImageView ivRightTitlebar;
    @BindView(R.id.relativeLayout_titleBar)
    RelativeLayout relativeLayoutTitleBar;
    @BindView(R.id.iv_head_acPerson)
    CircleImageView ivHeadAcPerson;
    @BindView(R.id.tv_nickName_acPerson)
    TextView tvNickNameAcPerson;
    @BindView(R.id.tv_age_acPerson)
    TextView tvAgeAcPerson;
    @BindView(R.id.bt_findArticle_acPerson)
    Button btFindArticleAcPerson;

    @BindView(R.id.tv_email_acPerson)
    TextView tvEmail;
    private FocusBean.DataBean dataBean;

    @Override
    protected int initContentView() {
        return R.layout.activity_person;
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        Intent intent = getIntent();
        dataBean = (FocusBean.DataBean) intent.getSerializableExtra("dataBean");
    }

    @Override
    protected void initViewAndData() {
        GlideUtil.setImageTiling(ivHeadAcPerson, dataBean.getUserPic(), R.drawable.headimg_default);
        tvCenterTitlebar.setText("个人主页");
        tvAgeAcPerson.setText("年龄 : " + dataBean.getUserAge());
        tvNickNameAcPerson.setText("昵称 : " + dataBean.getNickName());
        if(TextUtils.isEmpty(dataBean.getEmail())){
            tvEmail.setText("邮箱 : 暂无邮箱");
        }else {
            tvEmail.setText("邮箱 : "+dataBean.getEmail());
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


    @OnClick({R.id.iv_back_titlebar, R.id.bt_findArticle_acPerson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_titlebar:
                finish();
                break;
            case R.id.bt_findArticle_acPerson:
                Intent intent = new Intent(PersonActivity.this, ArticleListActivity.class);
                intent.putExtra("fid", TextUtils.isEmpty(dataBean.getUid()) ? dataBean.getFid() : dataBean.getUid());
                startActivity(intent);
                break;
        }
    }
}
