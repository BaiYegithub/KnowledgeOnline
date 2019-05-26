package heqi.online.com.main.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import butterknife.BindView;
import heqi.online.com.R;
import heqi.online.com.base.BaseActivity;
import heqi.online.com.main.im.ui.ConversationFragment;
import heqi.online.com.view.TitleBar;

public class ChatListActivity extends BaseActivity {


    @BindView(R.id.frame_AcChatList)
    FrameLayout frameAcChatList;
    @BindView(R.id.titlebar_acChatList)
    TitleBar titlebarAcChatList;

    @Override
    protected int initContentView() {
        return R.layout.activity_chat_list;
    }

    @Override
    protected void initViewAndData() {

        titlebarAcChatList.setTv_center("群组消息");

        ConversationFragment conversationFragment = new ConversationFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_AcChatList, conversationFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }

}
