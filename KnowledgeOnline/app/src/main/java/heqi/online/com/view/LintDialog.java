package heqi.online.com.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import heqi.online.com.R;

/**
 * author : by
 * date: 2019/4/15 0015  上午 11:26.
 * describe   添加链接的弹框
 */

public class LintDialog extends Dialog {

    @BindView(R.id.et_lint_dialogLint)
    EditText etLintDialogLint;
    @BindView(R.id.et_lintContent_dialogLint)
    EditText etLintContentDialogLint;
    //确认按钮
    @BindView(R.id.bt_confirm_dialogLint)
    Button btConfirmDialog;

    private OnConfirmClick onConfirmClick;

    public LintDialog(@NonNull Context context) {
        super(context, R.style.customDialog);
        setContentView(R.layout.dialog_lint);
        ButterKnife.bind(this);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕宽的0.8倍
        dialogWindow.setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //点击事件
        btConfirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onConfirmClick != null) {
                    onConfirmClick.onConfirm();
                }
            }
        });

    }


    //获取链接地址
    public String getEtLintAddress() {
        return etLintDialogLint.getText().toString();
    }

    /**
     * @return 获取链接内容
     */
    public String getEtLintContent() {
        return etLintContentDialogLint.getText().toString();
    }

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public interface OnConfirmClick {
        void onConfirm();
    }
}
