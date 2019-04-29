package heqi.online.com.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heqi.online.com.R;

/**
 * Created by Administrator on 2019/4/29.
 */

public class ReplyDialog extends Dialog {


    @BindView(R.id.tv_reply)
    TextView tvReply;
    @BindView(R.id.tv_del)
    TextView tvDel;
    //回复内容输入框
    @BindView(R.id.et_reply)
    EditText etReply;

    private OnClickListener onClickListener;

    public ReplyDialog(@NonNull Context context) {
        super(context, R.style.customDialog);
        setContentView(R.layout.dialog_reply);
        ButterKnife.bind(this);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕宽的0.8倍
        dialogWindow.setAttributes(lp);
    }

    @OnClick({R.id.tv_reply, R.id.tv_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reply:
                if (onClickListener != null) {
                    onClickListener.onReply(etReply.getText().toString());
                }
                break;
            case R.id.tv_del:
                if (onClickListener != null) {
                    onClickListener.onDelete();
                }
                break;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onReply(String strEt);

        void onDelete();
    }


}
