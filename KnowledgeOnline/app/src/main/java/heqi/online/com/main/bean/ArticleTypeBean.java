package heqi.online.com.main.bean;

/**
 * Created by Administrator on 2019/5/26.
 */

public class ArticleTypeBean {
    private Integer id;

    private String typeCode;

    private String typeContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode == null ? "" : typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeContent() {
        return typeContent == null ? "" : typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }
}
