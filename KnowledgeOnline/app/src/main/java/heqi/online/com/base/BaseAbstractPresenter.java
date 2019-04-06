package heqi.online.com.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * author : by
 * date: 2018/11/9 0009  下午 2:56.
 * describe  presenter 的抽象基类
 */

public abstract class BaseAbstractPresenter<T extends BaseView> implements BasePresenter {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public T mView;

    public BaseAbstractPresenter(T mView) {
        this.mView = mView;
    }

    @Override
    public void detachView() {
        if (compositeDisposable != null) { //不判断compositeDisposable 是否disposed ,因为源码里面判断过了
            compositeDisposable.dispose();
        }
    }
}
