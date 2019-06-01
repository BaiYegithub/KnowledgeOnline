package heqi.online.com.base;

/*进行网络请求的类*/
public interface BasePresenter<T extends BaseView> {
    void detachView();
}
