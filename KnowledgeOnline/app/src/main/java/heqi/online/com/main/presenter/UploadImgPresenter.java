package heqi.online.com.main.presenter;

import android.arch.lifecycle.LifecycleOwner;

import heqi.online.com.base.BaseAbstractPresenter;
import heqi.online.com.base.BaseBean;
import heqi.online.com.http.network.BaseApiServiceHelper;
import heqi.online.com.http.network.BaseConsumer;
import heqi.online.com.main.inter.IUploadImg;

/**
 * Created by Administrator on 2019/4/26.
 */

public class UploadImgPresenter extends BaseAbstractPresenter<IUploadImg> {

    public UploadImgPresenter(IUploadImg mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void uploadImg(String filePath, String loginAccount) {
        mView.showLoading();
        compositeDisposable.add(BaseApiServiceHelper.upload(filePath, loginAccount).subscribe(new BaseConsumer<BaseBean<String>>(mView) {
            @Override
            protected void onSuccessData(BaseBean<String> result) {
                if (result.isRequestSuccess()) {//如果请求成功
                    mView.showToast("头像上传成功");
                    mView.uploadSuccess(result.getData());
                }
            }
        }));
    }
}
