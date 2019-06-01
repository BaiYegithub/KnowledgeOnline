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
import heqi.online.com.main.bean.HomePageBean;

/**
 * author : heqi
 * date: 2019/4/19 0019  下午 3:14.
 * describe  微信文章列表的适配器
 */

public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomePageBean.DataBean> datas = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public ArticlesAdapter() {
    }

    public void addDataList(List<HomePageBean.DataBean> datalist) {
        datas.addAll(datalist);
        notifyDataSetChanged();
    }

    public void refreshList(List<HomePageBean.DataBean> datalist) {
        datas.clear();
        datas.addAll(datalist);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleVh(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ArticleVh articleVh = (ArticleVh) holder;
        articleVh.tvAuthor.setText(datas.get(position).getAuthor());
        articleVh.tvTagsName.setText(datas.get(position).getChapterName());
        articleVh.tvTitle.setText(datas.get(position).getTitle());
        articleVh.tvTime.setText(datas.get(position).getPublishTime());
        //设置单机事件
        articleVh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(datas.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ArticleVh extends RecyclerView.ViewHolder {

        //名字
        @BindView(R.id.tv_author_item_article)
        TextView tvAuthor;
        //tagName
        @BindView(R.id.tv_tagsName)
        TextView tvTagsName;
        //title  文章的title
        @BindView(R.id.tv_title_item_article)
        TextView tvTitle;
        //收藏按钮
        @BindView(R.id.iv_collect_item_article)
        ImageView ivCollect;
        //日期
        @BindView(R.id.tv_time_item_article)
        TextView tvTime;

        public ArticleVh(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(HomePageBean.DataBean dataBean);
    }
}
