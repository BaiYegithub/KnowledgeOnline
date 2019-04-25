package heqi.online.com.login;

import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.login.inter.ILogin;
import heqi.online.com.login.presenter.LoginPresenter;
import heqi.online.com.view.ClearEditText;
import heqi.online.com.view.TitleBar;

/**
 * Created by Administrator on 2019/4/6.
 */

public class RegistActivity extends BaseActivity implements ILogin {
    @BindView(R.id.titleBarAcRegist)
    TitleBar titleBarAcRegist;
    @BindView(R.id.et_phoneNum_acRegist)
    ClearEditText etPhoneNumAcRegist;
    @BindView(R.id.et_pass_acRegist)
    EditText etPassAcRegist;
    @BindView(R.id.iv_eye_acRegist)
    ImageView ivEyeAcRegist;
    @BindView(R.id.rlv_et_pass_acRegist)
    RelativeLayout rlvEtPassAcRegist;
    @BindView(R.id.bt_regist_acRegist)
    Button btRegistAcRegist;
    @BindView(R.id.rlv_acRegist)
    RelativeLayout rlvAcRegist;

    private boolean cansee;//判断密码是否可见,true为可见,false为不可见
    private LoginPresenter loginPresenter;

    @Override
    protected int initContentView() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initViewAndData() {
        titleBarAcRegist.setTv_center("注册");
        titleBarAcRegist.setMyBackGround(null);
    }

    @Override
    protected void initHttp() {
        loginPresenter = new LoginPresenter(this,this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }


    @OnClick({R.id.iv_eye_acRegist, R.id.bt_regist_acRegist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_eye_acRegist:
                if (cansee) {
                    //密文密码
                    etPassAcRegist.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivEyeAcRegist.setImageDrawable(getResources().getDrawable(R.drawable.close_eye));
                } else {
                    //明文密码
                    etPassAcRegist.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivEyeAcRegist.setImageDrawable(getResources().getDrawable(R.drawable.open_eye));
                }
                cansee = !cansee;
                etPassAcRegist.postInvalidate();
                //光标放到末尾
                CharSequence text = etPassAcRegist.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
                break;
            case R.id.bt_regist_acRegist:
                loginPresenter.register(etPhoneNumAcRegist.getText().toString(),etPassAcRegist.getText().toString());
                break;
        }
    }

    @Override
    public void registerSuccess() {
        showToast("注册成功，快去登录吧~");
        finish();
    }

    //登陆成功
    @Override
    public void loginSuccess() {

    }
}
