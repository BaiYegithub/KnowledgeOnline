package heqi.online.com.main.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import heqi.online.com.R;
import heqi.online.com.main.bean.SlidingBean;
import heqi.online.com.utils.LogUtil;


/**
 * @author Create by hq.
 *         describe:
 */

public class CourseSlidingAdapter extends RecyclerView.Adapter {
    private ArrayList<SlidingBean> slidingArray = new ArrayList<>();
    private HashSet<String> codeHash = new HashSet<>();//存储点击的名字
    private HashSet<Integer> positionHash=new HashSet<>();//存储点击的position

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScreenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_viewholder_course_screen, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ScreenViewHolder) {
            ScreenViewHolder viewHolder = (ScreenViewHolder) holder;
            SlidingBean slidingBean = slidingArray.get(position);
            LogUtil.i("sliding", "size:" + slidingArray.size());
            LogUtil.i("sliding", "type:" + slidingBean.getType());
            if (slidingBean.getType() == 1) {
                viewHolder.tv_title.setText(slidingBean.getName());
                viewHolder.tv_title.setVisibility(View.VISIBLE);
                viewHolder.cb_item.setVisibility(View.GONE);
            } else {
                viewHolder.cb_item.setText(slidingBean.getName());
                viewHolder.cb_item.setVisibility(View.VISIBLE);
                viewHolder.tv_title.setVisibility(View.GONE);
            }
            viewHolder.cb_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SlidingBean localSliding = slidingArray.get(position);
                    if (isChecked) {
                        localSliding.setSelected(true);
                        codeHash.add(localSliding.getCode());
                        positionHash.add(position);
                    } else {
                        localSliding.setSelected(true);
                        codeHash.remove(localSliding.getCode());
                        positionHash.remove(position);
                    }
                }
            });
            viewHolder.cb_item.setChecked(slidingBean.isSelected());

        }
    }

    @Override
    public int getItemCount() {

        return slidingArray == null ? 0 : slidingArray.size();
    }

    class ScreenViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_courseScreen)
        CheckBox cb_item;
        @BindView(R.id.title_courseScreen)
        TextView tv_title;

        public ScreenViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 设置侧滑视图数据
     */
    public void setData(ArrayList<SlidingBean> slidingArray) {
        if (slidingArray != null) {
            this.slidingArray.clear();
            this.slidingArray.addAll(slidingArray);//侧滑视图按钮数据
            notifyDataSetChanged();
        }
    }
    /**
     * 是否有数据
     * */
    public boolean getIsHaveData(){
        return slidingArray.size()>0;
    }
    /**
     * 返回被选中item的code集合
     */
    public String getChoiceCode() {
        int length = codeHash.size();
        StringBuffer sb = new StringBuffer();
        String arr = "[]";
        if (length > 0) {
            int index = 0;
            for (String str : codeHash) {
                if (index == 0) {
                    sb.append("[" + str + ",");
                } else if (index == length - 1) {
                    sb.append(str + "]");
                } else {
                    sb.append(str + ",");
                }
                index++;
            }
            arr = sb.toString();
        }
        return arr;
    }

    /**
     * 重置侧滑视图数据
     */
    public void resetSlidingList() {
        codeHash.clear();
        for (int position:positionHash){
            slidingArray.get(position).setSelected(false);
            notifyItemChanged(position);
        }
        positionHash.clear();
    }

    /**
     * 动态设置GridManager的每行列数
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {//TODO 判断一行显示几个(一行显示个数=SpanCount/SpanSize)
                    SlidingBean slidingBean = slidingArray.get(position);
                    if (slidingBean.getType() == 1) {
                        return 3;
                    } else {
                        return 1;
                    }
                }
            });
        }
    }
}
