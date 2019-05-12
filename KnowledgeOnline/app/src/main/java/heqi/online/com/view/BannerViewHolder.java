package heqi.online.com.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.zhouwei.mzbanner.holder.MZViewHolder;

import heqi.online.com.R;
import heqi.online.com.main.activity.ArticleDetailActivity;
import heqi.online.com.main.bean.WanBannerBean;
import heqi.online.com.utils.GlideUtil;

/**
 * Created by Administrator on 2018/11/22 0022.
 */

public  class BannerViewHolder implements MZViewHolder<WanBannerBean> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.viewhoder_banner_item,null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);

        return view;
    }

    @Override
    public void onBind(final Context context, final int position, final WanBannerBean data) {
        // 数据绑定
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"点击了："+position,Toast.LENGTH_SHORT).show();
                ArticleDetailActivity.navToArticleDetailActivity(context,data.getUrl());
            }
        });
        GlideUtil.setImageAngle(mImageView,data.getImagePath(),R.drawable.course_placeholder,10);
    }
}
