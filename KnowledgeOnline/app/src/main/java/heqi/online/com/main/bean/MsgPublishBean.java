package heqi.online.com.main.bean;

/**
 * Created by Administrator on 2019/4/27.通知类，使用EventBus实现的
 */

public class MsgPublishBean {
    public boolean isChange;

    public MsgPublishBean() {
    }

    public MsgPublishBean(boolean isChange) {
        this.isChange = isChange;
    }
}
