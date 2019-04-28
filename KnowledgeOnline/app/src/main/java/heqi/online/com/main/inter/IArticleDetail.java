package heqi.online.com.main.inter;

import java.util.List;

import heqi.online.com.base.BaseView;
import heqi.online.com.main.bean.CommentsBean;
import heqi.online.com.main.bean.HomePageBean;

/**
 * Created by Administrator on 2019/4/25.
 */

public interface IArticleDetail extends BaseView {
    //获取文章详情
    void getArticleDetail(HomePageBean.DataBean data);

    //操作成功
    void ClickSuccess(boolean isCollect);

    //改变关注状态
    void changeFocus(boolean b);

    //插入评论成功
    void insertSuccess();

    void getCommentsList(List<CommentsBean> data);
}
