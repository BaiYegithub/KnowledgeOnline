package heqi.online.com.main.inter;

import heqi.online.com.base.BaseView;
import heqi.online.com.main.bean.WxArticlesListBean;

/**
 * author : by
 * date: 2019/4/19 0019  下午 2:28.
 * describe
 */

public interface IWxArticlesList extends BaseView {

    //获取微信文章列表
    void getWxArticlesList(WxArticlesListBean data);
}
