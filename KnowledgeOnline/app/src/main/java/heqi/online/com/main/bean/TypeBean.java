package heqi.online.com.main.bean;

/**
 * Created by Administrator on 2019/5/31.
 * 文章类型的实体类
 */

public class TypeBean {

    /**
     * id : 1
     * typeCode : 1000
     * typeContent : 科学技术
     */

    private int id;
    private String typeCode;
    private String typeContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }
}
