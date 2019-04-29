package heqi.online.com.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import heqi.online.com.R;
import heqi.online.com.main.bean.FocusBean;
import heqi.online.com.utils.GlideUtil;

/**
 * Created by Administrator on 2019/4/28.
 */

public class FocusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FocusBean.DataBean> beanList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public FocusAdapter() {
    }

    public void refreshList(List<FocusBean.DataBean> giveList) {
        beanList.clear();
        beanList.addAll(giveList);
        notifyDataSetChanged();
    }

    public void addList(List<FocusBean.DataBean> giveList) {
        beanList.addAll(giveList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_focus, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        GlideUtil.setImageTiling(itemViewHolder.ivPic, beanList.get(position).getUserPic(), R.drawable.headimg_default);
        itemViewHolder.tvName.setText(beanList.get(position).getNickName());
        //点击事件
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(beanList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_pic_itemFocus)
        ImageView ivPic;

        @BindView(R.id.tv_name_itemFocus)
        TextView tvName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FocusBean.DataBean dataBean);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
