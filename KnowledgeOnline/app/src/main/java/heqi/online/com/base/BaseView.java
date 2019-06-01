package heqi.online.com.base;

/*view是activity和fragment的抽象*/
public interface BaseView<T> {
    //显示加载框
    void showLoading();
    //显示带自定义文字的加载框
    void showLoading(String text);
    //隐藏加载框
    void hideLoading();
    //显示吐司
    void showToast(String text);
    //显示没有网络的缺省页
    void showNoNetWork();
    //隐藏没有网络的缺省页
    void hideNoNetWork();
}
