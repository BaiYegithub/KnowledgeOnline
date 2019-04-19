package heqi.online.com.main.inter;

import java.util.List;

import heqi.online.com.base.BaseView;
import heqi.online.com.main.bean.WxArticlesBelongBean;

/**
 * author : by
 * date: 2019/4/19 0019  上午 10:59.
 * describe
 */

public interface IWxArticles extends BaseView {
    //获取微信文章列表
    void getWxArticlesList(List<WxArticlesBelongBean> data);
}
