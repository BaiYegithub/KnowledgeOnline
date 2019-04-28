package heqi.online.com.main.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.inter.IUpdate;
import heqi.online.com.main.presenter.UpdatePresenter;
import heqi.online.com.utils.ConstantUtil;
import heqi.online.com.utils.SharedPreferenceUtils;

/**
 * Created by Administrator on 2019/4/28.
 */

public class ChangeActivity extends BaseActivity implements IUpdate {
    //输入框
    @BindView(R.id.et_acChange)
    EditText etAcChange;
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
    private String key;
    private String value;
    private UpdatePresenter updatePresenter;


    public static void navToChangeActivity(Context context, String key, String value) {
        Intent intent = new Intent(context, ChangeActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("value", value);
        context.startActivity(intent);

    }

    @Override
    protected void initIntent() {
        super.initIntent();
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        value = intent.getStringExtra("value");

        if (key.equals(ConstantUtil.Age)) {
            etAcChange.setInputType(InputType.TYPE_CLASS_NUMBER);
            tvCenterTitlebar.setText("修改年龄");
        } else if (key.equals(ConstantUtil.NickName)) {
            tvCenterTitlebar.setText("修改昵称");
        }

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_change;
    }

    @Override
    protected void initViewAndData() {

        tvRightTitlebar.setText("确认修改");
        etAcChange.setHint(value);

        updatePresenter = new UpdatePresenter(this, this);

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


    @OnClick({R.id.iv_back_titlebar, R.id.tv_right_titlebar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_titlebar:
                finish();
                break;
            case R.id.tv_right_titlebar:
                if (key.equals(ConstantUtil.NickName)) {
                    updatePresenter.update(etAcChange.getText().toString(), (String) SharedPreferenceUtils.get(ConstantUtil.Sex, ""), (int) SharedPreferenceUtils.get(ConstantUtil.Age, 0));
                } else if (key.equals(ConstantUtil.Age)) {
                    try {
                        updatePresenter.update((String) SharedPreferenceUtils.get(ConstantUtil.NickName, ""), (String) SharedPreferenceUtils.get(ConstantUtil.Sex, ""), Integer.parseInt(etAcChange.getText().toString()));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void UpdateSuccess() {
        showToast("修改成功");
        if (key.equals(ConstantUtil.NickName)) {
            SharedPreferenceUtils.put(ConstantUtil.NickName, etAcChange.getText().toString());
        } else if (key.equals(ConstantUtil.Age)) {
            SharedPreferenceUtils.put(ConstantUtil.Age, Integer.parseInt(etAcChange.getText().toString()));
        }
        finish();
    }
}
