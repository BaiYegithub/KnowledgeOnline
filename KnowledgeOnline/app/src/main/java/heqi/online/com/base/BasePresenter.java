package heqi.online.com.base;

/**
 * author : by
 * date: 2018/11/9 0009  上午 11:04.
 * describe  presenter 接口，用于定义关闭view 防止oom
 */

public interface BasePresenter<T extends BaseView> {
    void detachView();
}
