package heqi.online.com.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heqi.online.com.R;
import heqi.online.com.main.bean.ArticleTypeBean;

/**
 * Created by Administrator on 2019/5/19.
 */

public class TypeDialog extends Dialog {

    @BindView(R.id.ll_dialog_type)
    LinearLayout llDialogType;
    @BindView(R.id.bt_commit_dialog_type)
    Button btCommitDialogType;

    private List<CheckBox> checkBoxList = new ArrayList<>();
    private OnCommitClick onCommitClick;
    private List<ArticleTypeBean> thisList;

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
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < checkBoxList.size(); i++) {
                if(checkBoxList.get(i).isChecked()){
                    if(sb.length()>0){
                        sb.append(",").append(thisList.get(i).getTypeCode());
                    }else {
                        sb.append(thisList.get(i).getTypeCode());
                    }
                }
            }

            onCommitClick.CommitClick(sb.toString());
        }
    }

    //设置提交的点击事件
    public void setOnCommitClick(OnCommitClick onCommitClick) {
        this.onCommitClick = onCommitClick;
    }

    public interface OnCommitClick {
        void CommitClick(String typeCodes);
    }

    public void addCheckBox(List<ArticleTypeBean> list) {
        thisList = list;
        for (int i = 0; i < list.size(); i++) {
            CheckBox cb = (CheckBox) LayoutInflater.from(this.getContext()).inflate(R.layout.type_rb, llDialogType, false);
            cb.setId(i);
            cb.setText(list.get(i).getTypeContent());
            checkBoxList.add(cb);
            llDialogType.addView(cb);
        }
    }
}
