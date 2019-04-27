package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.bean.CourseBean;
import heqi.online.com.main.inter.ICourseList;

/**
 * Created by Administrator on 2019/4/27.
 */

public class CourseListPresenter extends BaseAbstractPresenter<ICourseList> {

    public CourseListPresenter(ICourseList mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getCourseList(int currentPage, int pageSize) {
        compositeDisposable.add(BaseApiServiceHelper.getCourseBean(currentPage, pageSize).subscribe(new BaseConsumer<BaseBean<CourseBean>>() {
            @Override
            protected void onSuccessData(BaseBean<CourseBean> result) {
                if(result.isRequestSuccess()){
                    mView.getCourse(result.getData());
                }
            }
        }));
    }
}
