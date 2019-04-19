package heqi.online.com.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.LinkedList;
import java.util.List;

import heqi.online.com.R;

/**
 * Created by Administrator on 2019/4/6.
 */

public class MyApplication extends Application{

    private static MyApplication instance;
    private static Context mContext;
    private List<Activity> activities = new LinkedList<>();

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.basic_background, R.color.firstGray);//全局设置 header和footer 背景颜色 文字颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return mContext;
    }

    /**
     * @param finishActivity 需要销毁的Activity
     *                       销毁传入的activity
     */
    public void finishSomeOneActivity(Class finishActivity) {
        String activityName = finishActivity.getName();
        String getActivityName = null;
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                getActivityName = activity.getClass().getName();
//                getActivityName = getActivityName.substring(getActivityName.lastIndexOf(".") + 1);
                if (activityName.equals(getActivityName)) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * @param saveActivity 保留的Activity其他全部关闭
     *                     销毁除了传入参数以外的视图
     */
    public void finishAllActivityExceptThis(Activity saveActivity) {
        String activityName = saveActivity.getClass().getName();
        String getActivityName = null;
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                getActivityName = activity.getClass().getName();
//                getActivityName = getActivityName.substring(getActivityName.lastIndexOf(".") + 1);
                if (!activityName.equals(getActivityName)) {
                    activity.finish();
                }
            }
        }
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activities != null && activities.size() > 0) {
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        } else {
            activities.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void removeAllActivity() {
        try {
            for (Activity ac : activities) {
                if (ac != null) {
                    ac.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
