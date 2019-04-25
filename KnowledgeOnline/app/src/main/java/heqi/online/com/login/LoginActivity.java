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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import heqi.online.com.MainActivity;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.login.inter.ILogin;
import heqi.online.com.login.presenter.LoginPresenter;
import heqi.online.com.view.ClearEditText;
import heqi.online.com.view.TitleBar;

/**
 * Created by Administrator on 2019/4/6.
 */

public class LoginActivity extends BaseActivity implements ILogin {
    @BindView(R.id.titleBarAcLogin)
    TitleBar titleBarAcLogin;
    @BindView(R.id.et_phoneNum_acLogin)
    ClearEditText etPhoneNumAcLogin;
    @BindView(R.id.et_pass_acLogin)
    EditText etPassAcLogin;
    @BindView(R.id.iv_eye_acLogin)
    ImageView ivEyeAcLogin;
    @BindView(R.id.rlv_et_pass_acLogin)
    RelativeLayout rlvEtPassAcLogin;
    @BindView(R.id.bt_login_acLogin)
    Button btLoginAcLogin;
    @BindView(R.id.tv_forgetPass_acLogin)
    TextView tvForgetPassAcLogin;
    @BindView(R.id.rlv_acLogin)
    RelativeLayout rlvAcLogin;

    private boolean cansee;//判断密码是否可见,true为可见,false为不可见
    private LoginPresenter loginPresenter;

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewAndData() {
        titleBarAcLogin.canBack(false);
        titleBarAcLogin.setMyBackGround(null);
        titleBarAcLogin.setTv_center("登录");
        titleBarAcLogin.setTv_right("新用户注册");

    }

    @Override
    protected void initHttp() {
        loginPresenter = new LoginPresenter(this,this);
    }

    @Override
    protected void initListener() {
        titleBarAcLogin.setOnTvRightTextListener(new TitleBar.onRightTextClickListener() {
            @Override
            public void onRightTextListener() {
                openActivity(RegistActivity.class);
            }
        });
    }

    @Override
    protected void destroyResources() {

    }


    @OnClick({R.id.iv_eye_acLogin, R.id.bt_login_acLogin, R.id.tv_forgetPass_acLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_eye_acLogin:
                if (cansee) {
                    //密文密码
                    etPassAcLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivEyeAcLogin.setImageDrawable(getResources().getDrawable(R.drawable.close_eye));
                } else {
                    //明文密码
                    etPassAcLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivEyeAcLogin.setImageDrawable(getResources().getDrawable(R.drawable.open_eye));
                }
                cansee = !cansee;
                etPassAcLogin.postInvalidate();
                //光标放到末尾
                CharSequence text = etPassAcLogin.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
                break;
            case R.id.bt_login_acLogin:
                loginPresenter.login(etPhoneNumAcLogin.getText().toString(),etPassAcLogin.getText().toString());
                break;
            case R.id.tv_forgetPass_acLogin:
                break;
        }
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void loginSuccess() {
        openActivity(MainActivity.class);
    }


}
