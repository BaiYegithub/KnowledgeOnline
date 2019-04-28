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
import heqi.online.com.main.bean.CommentsBean;
import heqi.online.com.utils.GlideUtil;

/**
 * Created by Administrator on 2019/4/29.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommentsBean> commentsBeanList = new ArrayList<>();

    private OnItemLongClick onItemLongClick;

    public CommentAdapter() {

    }

    public void refreshList(List<CommentsBean> giveCommentsBeanList) {
        commentsBeanList.clear();
        commentsBeanList.addAll(giveCommentsBeanList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        commentViewHolder.tvComment.setText(commentsBeanList.get(position).getContent());
        commentViewHolder.tvCommentTime.setText(commentsBeanList.get(position).getCreateTime());
        commentViewHolder.tvPerson.setText(commentsBeanList.get(position).getFromUid());
        GlideUtil.setImageTiling(commentViewHolder.ivHead, commentsBeanList.get(position).getUserPic(), R.drawable.headimg_default);
        //长按事件
        commentViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClick != null) {
                    onItemLongClick.OnItemLongClickListener(commentsBeanList.get(position));
                }


                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsBeanList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_head_itemComment)
        ImageView ivHead;

        @BindView(R.id.tv_comment_itemComment)
        TextView tvComment;

        @BindView(R.id.tv_commentTime)
        TextView tvCommentTime;

        @BindView(R.id.tv_person)
        TextView tvPerson;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }

    public interface OnItemLongClick {
        void OnItemLongClickListener(CommentsBean commentsBean);
    }
}
