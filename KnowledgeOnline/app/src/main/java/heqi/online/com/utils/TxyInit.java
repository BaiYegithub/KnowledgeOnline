package heqi.online.com.utils;

import android.content.Intent;
import android.widget.Toast;

import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

import java.util.List;

import heqi.online.com.R;
import heqi.online.com.base.MyApplication;
import heqi.online.com.login.LoginActivity;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * Created by Administrator on 2019/5/26.
 */

public class TxyInit {

    public static int SDK_APPID = 1400213681;

    public static void initTxy() {
        TIMSdkConfig timsdkconfig = new TIMSdkConfig(SDK_APPID);
        //腾讯云初始化sdk
        TIMManager.getInstance().init(MyApplication.getInstance(), timsdkconfig);
        //设置当前用户的用户配置，登录前设置  单点登录

        TIMUserConfig timUserConfig = new TIMUserConfig().setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                Toast.makeText(context, "您的账号在其他设备登录，已被迫下线，请重新登录", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                SharedPreferenceUtils.clear();
                context.startActivity(intent);
            }

            @Override
            public void onUserSigExpired() {

            }
        });

        TIMManager.getInstance().setUserConfig(timUserConfig);
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                return false;
            }
        });

        if (MsfSdkUtils.isMainProcess(MyApplication.getAppContext())) {
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    if (notification.getGroupReceiveMsgOpt() == TIMGroupReceiveMessageOpt.ReceiveAndNotify) {
                        //消息被设置为需要提醒
                        notification.doNotify(MyApplication.getAppContext(), R.mipmap.ic_launcher);
                    }
                }
            });
        }


    }
}
