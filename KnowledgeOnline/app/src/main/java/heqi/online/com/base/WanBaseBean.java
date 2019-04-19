package heqi.online.com.base;

/**
 * author : by
 * date: 2019/4/19 0019  上午 10:45.
 * describe
 */

public class WanBaseBean<T> {
    private int errorCode;//错误码
    private String errorMsg; //错误信息
    private T data;//返回数据

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg == null ? "" : errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
