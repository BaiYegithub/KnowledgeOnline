package heqi.online.com.main.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/27.
 */

public class CourseBean {

    /**
     * currentPage : 1
     * pageSize : 20
     * totalPage : 0
     * totalSize : 5
     * data : [{"id":2,"courseNo":"111","courseName":"啊啊","coursePrice":111,"courseDesc":"暗粉色居然还能投放方面发几个，明细账新东方","courseTeacher":"方法","coursePic":"zehrytd/cxhdz","status":1},{"id":3,"courseNo":"333","courseName":"333","coursePrice":333,"courseDesc":"333","courseTeacher":"333","coursePic":"333","status":1},{"id":4,"courseNo":"444","courseName":"44","coursePrice":444,"courseDesc":"444","courseTeacher":"444","coursePic":"444","status":1},{"id":5,"courseNo":"555","courseName":"555","coursePrice":555,"courseDesc":"555","courseTeacher":"555","coursePic":"555","status":1},{"id":6,"courseNo":"666","courseName":"666","coursePrice":666,"courseDesc":"666","courseTeacher":"666","coursePic":"666","status":0}]
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

    public static class DataBean {
        /**
         * id : 2
         * courseNo : 111
         * courseName : 啊啊
         * coursePrice : 111
         * courseDesc : 暗粉色居然还能投放方面发几个，明细账新东方
         * courseTeacher : 方法
         * coursePic : zehrytd/cxhdz
         * status : 1
         */

        private int id;
        private String courseNo;
        private String courseName;
        private int coursePrice;
        private String courseDesc;
        private String courseTeacher;
        private String coursePic;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCourseNo() {
            return courseNo == null ? "" : courseNo;
        }

        public void setCourseNo(String courseNo) {
            this.courseNo = courseNo;
        }

        public String getCourseName() {
            return courseName == null ? "" : courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getCoursePrice() {
            return coursePrice;
        }

        public void setCoursePrice(int coursePrice) {
            this.coursePrice = coursePrice;
        }

        public String getCourseDesc() {
            return courseDesc == null ? "" : courseDesc;
        }

        public void setCourseDesc(String courseDesc) {
            this.courseDesc = courseDesc;
        }

        public String getCourseTeacher() {
            return courseTeacher == null ? "" : courseTeacher;
        }

        public void setCourseTeacher(String courseTeacher) {
            this.courseTeacher = courseTeacher;
        }

        public String getCoursePic() {
            return coursePic == null ? "" : coursePic;
        }

        public void setCoursePic(String coursePic) {
            this.coursePic = coursePic;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
