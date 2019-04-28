package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.bean.FocusBean;
import heqi.online.com.main.inter.IGetFocus;

/**
 * Created by Administrator on 2019/4/28.
 */

public class GetFocusPresenter extends BaseAbstractPresenter<IGetFocus> {

    public GetFocusPresenter(IGetFocus mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getFocusList(int from, int curPage, int pageSize) {
        compositeDisposable.add(BaseApiServiceHelper.getFocusList(from, curPage, pageSize).subscribe(new BaseConsumer<BaseBean<FocusBean>>() {
            @Override
            protected void onSuccessData(BaseBean<FocusBean> result) {
                if(result.isRequestSuccess()){
                    FocusBean data = result.getData();
                    mView.getFocusBean(data);
                }
            }
        }));
    }
}
