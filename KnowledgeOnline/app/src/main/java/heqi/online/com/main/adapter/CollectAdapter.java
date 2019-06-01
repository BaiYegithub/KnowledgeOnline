package heqi.online.com.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import heqi.online.com.R;
import heqi.online.com.inter.OnDeleteClickLister;
import heqi.online.com.main.bean.HomePageBean;

/**
 * Created by Administrator on 2019/4/27.用来做收藏展示
 */

public class CollectAdapter extends BaseRecyclerViewAdapter<HomePageBean.DataBean> {

    //点击删除按钮监听
    private OnDeleteClickLister mDeleteClickListener;

    public CollectAdapter(Context context, List<HomePageBean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, HomePageBean.DataBean bean, int position) {
        View view = holder.getView(R.id.tv_delete);
        view.setTag(position);
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                }
            });
        }
        TextView tvAuther = (TextView) holder.getView(R.id.tv_author_item_article);
        tvAuther.setText(bean.getAuthor());
        TextView tvTagsName = (TextView) holder.getView(R.id.tv_tagsName);
        tvTagsName.setText(bean.getChapterName());
        TextView tvTitle = (TextView) holder.getView(R.id.tv_title_item_article);
        tvTitle.setText(bean.getTitle());
        TextView tvTime = (TextView) holder.getView(R.id.tv_time_item_article);
        tvTime.setText(bean.getPublishTime());

    }

    /**
     * @param listener 设置点击删除事件
     */
    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

}
