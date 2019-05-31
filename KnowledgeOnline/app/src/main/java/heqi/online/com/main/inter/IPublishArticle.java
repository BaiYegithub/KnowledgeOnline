package heqi.online.com.main.inter;

import java.util.List;

import heqi.online.com.base.BaseView;
import heqi.online.com.main.bean.ArticleTypeBean;

/**
 * Created by Administrator on 2019/4/21.
 */

public interface IPublishArticle extends BaseView {

    //发布文章成功
    void publishSuccess();
    //获取文章所有类型
    void getArticleTypes(List<ArticleTypeBean> data);
}
