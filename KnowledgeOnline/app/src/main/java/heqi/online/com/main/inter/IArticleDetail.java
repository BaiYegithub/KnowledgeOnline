package heqi.online.com.main.inter;

import heqi.online.com.base.BaseView;
import heqi.online.com.main.bean.HomePageBean;

/**
 * Created by Administrator on 2019/4/25.
 */

public interface IArticleDetail extends BaseView {
    //获取文章详情
    void getArticleDetail(HomePageBean.DataBean data);

    //操作成功
    void ClickSuccess(boolean isCollect);
}
