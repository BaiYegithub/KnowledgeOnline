package heqi.online.com.login.bean;

/**
 * Created by Administrator on 2019/4/8.
 */

public class LoginBean {
    //登陆用户名
    private String loginAccount;
    //用户头像
    private String userPic;
    //用户性别
    private String userSex;
    //用户年龄
    private Integer userAge;
    //用户手机号
    private String mobile;
    //用户email
    private String email;
    //用户昵称
    private String nickName;
    //腾讯云userSig
    private String urlSig;
    //status
    private int status;


    public String getUrlSig() {
        return urlSig == null ? "" : urlSig;
    }

    public void setUrlSig(String urlSig) {
        this.urlSig = urlSig;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLoginAccount() {
        return loginAccount == null ? "" : loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getUserPic() {
        return userPic == null ? "" : userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserSex() {
        return userSex == null ? "" : userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge==null?0:userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName == null ? "" : nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
