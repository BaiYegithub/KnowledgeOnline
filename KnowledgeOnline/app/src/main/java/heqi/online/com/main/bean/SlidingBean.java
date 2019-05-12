package heqi.online.com.main.bean;

/**
 * @author Create by hq
 *         describe:
 */

public class SlidingBean {
    private int type;//1 标题  2 item
    private int id;//id
    private String code;//编码
    private String name;//内容
    private boolean isSelected;//判断是否选中

    public SlidingBean(int type, int id, String code, String name, boolean isSelected) {
        this.type = type;
        this.id = id;
        this.code = code;
        this.name = name;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
