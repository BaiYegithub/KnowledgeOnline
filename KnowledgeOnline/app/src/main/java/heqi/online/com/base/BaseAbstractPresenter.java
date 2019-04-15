package heqi.online.com.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;

/**
 * author : by
 * date: 2018/11/9 0009  下午 2:56.
 * describe  presenter 的抽象基类
 */

public abstract class BaseAbstractPresenter<T extends BaseView> implements BasePresenter,LifecycleObserver {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public T mView;
    private LifecycleOwner lifecycleOwner;

    public BaseAbstractPresenter(T mView,LifecycleOwner lifecycleOwner) {
        this.mView = mView;
        this.lifecycleOwner = lifecycleOwner;
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void detachView() {
        if (compositeDisposable != null) { //不判断compositeDisposable 是否disposed ,因为源码里面判断过了
            compositeDisposable.dispose();
        }
        lifecycleOwner.getLifecycle().removeObserver(this);

    }
}
