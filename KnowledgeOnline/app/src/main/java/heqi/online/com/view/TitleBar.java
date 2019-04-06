package heqi.online.com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import heqi.online.com.R;


/**
 * author : by
 * date: 2018/11/20 0020  下午 4:20.
 * describe  顶部标题栏
 */

public class TitleBar extends RelativeLayout {

    private ImageView ivback;
    private TextView tv_center;
    private TextView tv_right;
    private RelativeLayout relativeLayout;

    private onRightTextClickListener onRightTextClickListener;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.titlebar, this);
        ivback = findViewById(R.id.iv_back_titlebar);
        tv_center = findViewById(R.id.tv_center_titlebar);
        tv_right = findViewById(R.id.tv_right_titlebar);

        relativeLayout = findViewById(R.id.relativeLayout_titleBar);
        canBack(true);
    }

    public void canBack(boolean canback) {
        if (canback) {
            ivback.setVisibility(VISIBLE);
            ivback.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        } else {
            ivback.setVisibility(INVISIBLE);
        }
    }

    public void setTv_center(String text) {
        tv_center.setText(text);
    }

    public void setTv_right(String text) {
        tv_right.setText(text);
    }

    public void setOnTvRightTextListener( onRightTextClickListener onTvRightTextListener) {
       this.onRightTextClickListener = onTvRightTextListener;
        tv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightTextClickListener != null) {
                    onRightTextClickListener.onRightTextListener();
                }
            }
        });
    }


    public static interface onRightTextClickListener {
        void onRightTextListener();
    }

    /**
     * 解决Fragment fitsSystemWindows属性失效问题
     */
    public void setSystemBarPadding(Activity activity){
        int top = new SystemBarTintManager(activity).getConfig().getStatusBarHeight();
        relativeLayout.setPadding(relativeLayout.getPaddingLeft(), top, relativeLayout.getPaddingRight(), relativeLayout.getPaddingBottom());
    }

    //设置titleBar 的背景
    public void setMyBackGround(Drawable drawable){
        relativeLayout.setBackground(drawable);
    }
}
