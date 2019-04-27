package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.main.inter.IGetCourseBean;

/**
 * Created by Administrator on 2019/4/27.
 */

public class GetCourseBeanPresenter extends BaseAbstractPresenter<IGetCourseBean> {

    public GetCourseBeanPresenter(IGetCourseBean mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }


}
