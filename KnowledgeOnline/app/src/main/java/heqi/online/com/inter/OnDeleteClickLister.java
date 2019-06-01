package heqi.online.com.inter;

import android.view.View;

/**
 * @author heqi
 * @describe 描述 侧滑点击删除按钮的回调监听
 */
public interface OnDeleteClickLister {
    //点击删除按钮的回调监听
    void onDeleteClick(View view, int position);
}