package heqi.online.com.main.inter;

import heqi.online.com.base.BaseView;
import heqi.online.com.main.bean.CourseBean;

/**
 * Created by Administrator on 2019/4/27.
 */

public interface ICourseList extends BaseView {
    //获取课程数据
    void getCourse(CourseBean data);
}
