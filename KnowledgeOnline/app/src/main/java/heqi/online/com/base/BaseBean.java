package heqi.online.com.base;

import heqi.online.com.utils.ConstantUtil;
import heqi.online.com.utils.UIUtils;

/**
 * Created by Administrator on 2018/1/27 0027.
 */

public class BaseBean<T> {


    /**
     * code : 100000
     * dataType : 0
     * message : 登录失败
     * data :
     */

    private String code; //返回code 000000 表示成功
    private Integer dataType;  //返回dataType Integer类型
    private String message;  //提示信息
    private T data;  //返回数据

    private boolean isNeedToast = true;//自定义boolean 判断是否需要吐司message  默认true

    public BaseBean(String code, int dataType, String message, T data) {
        this.code = code;
        this.dataType = dataType;
        this.message = message;
        this.data = data;
    }

    public BaseBean() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDataType() {
        if (dataType == null) {
            return 0;
        }
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //判断是否请求成功
    public boolean isRequestSuccess() {
        if (code.equals(ConstantUtil.SUCCESS_CODE)) {

            return true;
        } else {
            if (isNeedToast) {
                UIUtils.showToast(message);
            }

            return false;
        }
    }

    /**
     * 默认弹出提示信息，如不需要弹出信息，请在调用 isRequestSuccess 之前调用，并传入false(比如说版本更新)
     *
     * @param isNeedToast 传入是否需要吐司
     * @return
     */
    public void setIsNeedToast(boolean isNeedToast) {
        this.isNeedToast = isNeedToast;
    }

}
