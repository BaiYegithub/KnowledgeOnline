package heqi.online.com.login.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.login.inter.ILogin;

/**
 * Created by Administrator on 2019/4/6.
 */

public class LoginPresenter extends BaseAbstractPresenter<ILogin> {

    public LoginPresenter(ILogin mView, LifecycleOwner lifecycleOwner) {
        super(mView,lifecycleOwner);
    }

    public void register(String userName,String password){
        compositeDisposable.add(BaseApiServiceHelper.register(userName,password).subscribe(new BaseConsumer<BaseBean>() {
            @Override
            protected void onSuccessData(BaseBean result) {
                if(result.isRequestSuccess()){
                    mView.registerSuccess();
                }
            }
        }));

    }
}
