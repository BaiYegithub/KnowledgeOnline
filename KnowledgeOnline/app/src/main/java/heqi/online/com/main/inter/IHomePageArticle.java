package heqi.online.com.main.inter;

import java.util.List;

import heqi.online.com.base.BaseView;
import heqi.online.com.main.bean.HomePageBean;
import heqi.online.com.main.bean.WanBannerBean;

/**
 * Created by Administrator on 2019/4/25.
 */

public interface IHomePageArticle extends BaseView {

    //获取首页文章列表
    void getHomePageBean(HomePageBean data);

    //获取banner List
    void getBannerList(List<WanBannerBean> data);
}
