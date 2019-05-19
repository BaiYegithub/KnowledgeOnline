package heqi.online.com.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heqi.online.com.R;

/**
 * Created by Administrator on 2019/5/19.
 */

public class TypeDialog extends Dialog {

    //radioGroup 类型选择器
    @BindView(R.id.rg_dialog_type)
    RadioGroup rgDialogType;
    @BindView(R.id.bt_commit_dialog_type)
    Button btCommitDialogType;

    private OnCommitClick onCommitClick;

    public TypeDialog(@NonNull Context context) {
        super(context, R.style.customDialog);
        setContentView(R.layout.dialog_type);
        ButterKnife.bind(this);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕宽的0.8倍
        dialogWindow.setAttributes(lp);
    }

    @OnClick(R.id.bt_commit_dialog_type)
    public void onViewClicked() {
        if (onCommitClick != null) {
            onCommitClick.CommitClick();
        }
    }

    //设置提交的点击事件
    public void setOnCommitClick(OnCommitClick onCommitClick) {
        this.onCommitClick = onCommitClick;
    }

    public  interface OnCommitClick {
        void CommitClick();
    }

    public void addRadioButton(String s){
        RadioButton rb = (RadioButton) LayoutInflater.from(this.getContext()).inflate(R.layout.type_rb,rgDialogType,false);
        rb.setText(s );
        rgDialogType.addView(rb);
    }
}
