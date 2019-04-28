package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.inter.IUpdate;
import heqi.online.com.utils.UIUtils;

/**
 * Created by Administrator on 2019/4/28.
 */

public class UpdatePresenter extends BaseAbstractPresenter<IUpdate> {

    public UpdatePresenter(IUpdate mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void update(String nickName, String userSex, int userAge) {
        compositeDisposable.add(BaseApiServiceHelper.update(UIUtils.getUid(), nickName, userSex, userAge).subscribe(new BaseConsumer<BaseBean>() {
            @Override
            protected void onSuccessData(BaseBean result) {
                if(result.isRequestSuccess()){
                    mView.UpdateSuccess();
                }
            }
        }));
    }
}
