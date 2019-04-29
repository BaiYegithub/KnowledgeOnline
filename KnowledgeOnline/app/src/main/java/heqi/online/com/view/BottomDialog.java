package heqi.online.com.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import heqi.online.com.R;


/**
 * author : by
 * date: 2018/11/22 0022  上午 10:43.
 * describe 性别选择和头像选择界面底部弹出的dialog
 */

public class BottomDialog extends Dialog {


    private final TextView tv_one;
    private final TextView tv_two;
    private OnTextClickListener onTextClickListener;
    private final TextView tv_three;

    public BottomDialog(@NonNull Context context) {
        super(context, R.style.bottomDialog);
        this.setContentView(R.layout.item_bottomdialog);
        tv_one = findViewById(R.id.tv_one_itemBottomDialog);
        tv_two = findViewById(R.id.tv_two_itemBottomDialog);

        tv_three = findViewById(R.id.tv_three_itemBottomDialog);
        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialog.super.dismiss();
            }
        });
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    public void setTv_oneText(String text) {
        tv_one.setText(text);
    }

    public void setTv_oneColorAndSize(int color, int size) {
        tv_one.setTextColor(color);
        tv_one.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
    }


    public void setTv_twoText(String text) {
        tv_two.setText(text);
    }

    public void setTv_twoColorAndSize(int color, int size) {
        tv_two.setTextColor(color);
        tv_two.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
    }

    public void setCancleColorAndSize(int color, int size) {
        tv_three.setTextColor(color);
        tv_three.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
    }

    public interface OnTextClickListener {
        void onOneTextClickListener();

        void onTwoTextClickListener();
    }

    public void SetOnTextClickListener(OnTextClickListener onTextClickListener1) {
        this.onTextClickListener = onTextClickListener1;
        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTextClickListener != null) {
                    onTextClickListener.onOneTextClickListener();
                }
            }
        });

        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTextClickListener != null) {
                    onTextClickListener.onTwoTextClickListener();
                }
            }
        });
    }

}
