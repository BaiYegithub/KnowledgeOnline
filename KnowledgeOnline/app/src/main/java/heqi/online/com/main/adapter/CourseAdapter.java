package heqi.online.com.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import heqi.online.com.R;
import heqi.online.com.inter.OnDeleteClickLister;
import heqi.online.com.main.bean.CourseBean;
import heqi.online.com.utils.GlideUtil;

/**
 * Created by Administrator on 2019/4/27.
 */

public class CourseAdapter extends BaseRecyclerViewAdapter<CourseBean.DataBean> {


    //点击删除按钮监听
    private OnDeleteClickLister mDeleteClickListener;

    public CourseAdapter(Context context, List<CourseBean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, CourseBean.DataBean bean, int position) {
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
        //课程封面
        ImageView ivImage = (ImageView) holder.getView(R.id.iv_img_course);
        GlideUtil.setImageTiling(ivImage, bean.getCoursePic(), R.drawable.bg);
        //课程名称
        TextView tvCourseName = (TextView) holder.getView(R.id.tv_courseName);
        tvCourseName.setText(bean.getCourseName());
        //课程描述
        TextView tvDesc = (TextView) holder.getView(R.id.tv_desc_course);
        tvDesc.setText(bean.getCourseDesc());
        //课程价格
        TextView tvPrice = (TextView) holder.getView(R.id.tv_price_course);
        tvPrice.setText(bean.getCoursePic());
    }

    /**
     * @param listener 设置点击删除事件
     */
    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }
}
