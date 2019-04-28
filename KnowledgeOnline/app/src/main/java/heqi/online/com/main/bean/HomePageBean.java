package heqi.online.com.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/4/25.
 */

public class HomePageBean {


    /**
     * currentPage : 1
     * pageSize : 20
     * totalPage : 0
     * totalSize : 6
     * data : [{"articleId":"bc4493f4304144bcbf0d58dc05078239_20190422","author":"羊毛","chapterName":"online","title":"地球日","articleContent":null,"publishTime":"2019-04-22T05:24:39.000+0000"},{"articleId":"b3efab1b157548b59d2ee9a4a8a2c055_20190421","author":"baibai","chapterName":"online","title":"我爱老婆","articleContent":null,"publishTime":"2019-04-21T05:13:28.000+0000"},{"articleId":"b3efaa1a157548b59d2ee9a4a8a2c055_20190421","author":"baibai","chapterName":"online","title":"两会","articleContent":null,"publishTime":"2019-04-21T07:34:09.000+0000"},{"articleId":"59745afc6fa94f3f842c1a1132cef08a_20190423","author":"baibai","chapterName":"online","title":"养生","articleContent":null,"publishTime":"2019-04-23T02:18:40.000+0000"},{"articleId":"838c87f8bcb74317a48686af07488e8e_20190423","author":"星星","chapterName":"online","title":"招聘","articleContent":null,"publishTime":"2019-04-23T02:16:29.000+0000"},{"articleId":"63b60f524ff9418287404f55a8f8d549_20190423","author":"太阳","chapterName":"online","title":"美容美发","articleContent":null,"publishTime":"2019-04-23T02:17:33.000+0000"}]
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
         * articleId : bc4493f4304144bcbf0d58dc05078239_20190422
         * author : 羊毛
         * chapterName : online
         * title : 地球日
         * articleContent : null
         * publishTime : 2019-04-22T05:24:39.000+0000
         */

        private String articleId;
        private String author;
        private String chapterName;
        private String title;
        private String articleContent;
        private String publishTime;
        private int status; //0是未收藏  1是收藏
        private String loginAccount;
        private int focus; //0是未关注  1是已关注

        public int getFocus() {
            return focus;
        }

        public void setFocus(int focus) {
            this.focus = focus;
        }

        public String getLoginAccount() {
            return loginAccount == null ? "" : loginAccount;
        }

        public void setLoginAccount(String loginAccount) {
            this.loginAccount = loginAccount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getArticleId() {
            return articleId == null ? "" : articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getAuthor() {
            return author == null ? "" : author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getChapterName() {
            return chapterName == null ? "" : chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticleContent() {
            return articleContent == null ? "" : articleContent;
        }

        public void setArticleContent(String articleContent) {
            this.articleContent = articleContent;
        }

        public String getPublishTime() {
            return publishTime == null ? "" : publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
    }
}
