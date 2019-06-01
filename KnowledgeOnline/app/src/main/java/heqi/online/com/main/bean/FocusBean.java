package heqi.online.com.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/4/28.
 */

public class FocusBean {

    /**
     * currentPage : 1
     * pageSize : 20
     * totalPage : 0
     * totalSize : 3
     * data : [{"id":0,"uid":"15810206306","fid":"杨洋","nickName":"baibai","userPic":"http://192.168.1.117:8080/pic/1556341136607.png","userSex":"男","userAge":20,"mobile":null,"email":null,"status":1},{"id":0,"uid":"15810206306","fid":"15810206306","nickName":"baibai","userPic":"http://192.168.1.117:8080/pic/1556341136607.png","userSex":"男","userAge":20,"mobile":null,"email":null,"status":1},{"id":0,"uid":"15810206306","fid":"yangzi668","nickName":"baibai","userPic":"http://192.168.1.117:8080/pic/1556341136607.png","userSex":"男","userAge":20,"mobile":null,"email":null,"status":1}]
     */

    private int currentPage;
    private int pageSize;
    private int totalPage;
    private int totalSize;
    private List<DataBean> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 0
         * uid : 15810206306
         * fid : 杨洋
         * nickName : baibai
         * userPic : http://192.168.1.117:8080/pic/1556341136607.png
         * userSex : 男
         * userAge : 20
         * mobile : null
         * email : null
         * status : 1
         */

        private int id;
        private String uid;
        private String fid;
        private String nickName;
        private String userPic;
        private String userSex;
        private int userAge;
        private String mobile;
        private String email;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public int getUserAge() {
            return userAge;
        }

        public void setUserAge(int userAge) {
            this.userAge = userAge;
        }

        public Object getMobile() {
            return mobile;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
