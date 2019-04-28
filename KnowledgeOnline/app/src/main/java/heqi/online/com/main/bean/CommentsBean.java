package heqi.online.com.main.bean;

/**
 * Created by Administrator on 2019/4/29.
 */

public class CommentsBean {

    /**
     * id : 1
     * articleId : bc4493f4304144bcbf0d58dc05078239_20190422
     * title : null
     * fromUid : 15810206306
     * content : 添加评论
     * createTime : null
     */

    private int id;
    private String articleId;
    private String title;
    private String fromUid;
    private String content;
    private String createTime;
    private String userPic;

    public String getUserPic() {
        return userPic == null ? "" : userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId == null ? "" : articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFromUid() {
        return fromUid == null ? "" : fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
