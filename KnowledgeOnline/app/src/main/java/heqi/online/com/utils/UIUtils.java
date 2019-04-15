package heqi.online.com.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;

import heqi.online.com.base.MyApplication;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * author : by
 * date: 2018/11/9 0009  上午 10:45.
 * describe
 */

public class UIUtils {

    /**
     * dip转为 px
     */
    public static int dip2px(float dipValue) {
        final float scale = MyApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px 转为 dip
     */
    public static int px2dip(float pxValue) {
        final float scale = MyApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int getColor(int id) {
        return ContextCompat.getColor(MyApplication.getAppContext(), id);
    }

    public static Context getContext() {
        return MyApplication.getAppContext();
    }


    private static Toast toast;

    public static void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showLongToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), content, Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }


    /**
     * 底部虚拟按键栏的高度，在计算软键盘是否存在时，需要计算虚拟按键的高度
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    /**
     * 检测软键盘是否弹出
     */
    public static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - (rect.bottom + getSoftButtonsBarHeight(activity)) != 0;
    }

    /**
     * 隐藏键盘
     */
    public static void hideSoft(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    // 两次点击按钮之间的点击间隔不能少于500毫秒
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;

    /*
    *
    * 点击事件防抖动的方法
    * */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /*
    *
    * 点击事件防抖动的方法
    * */
    public static boolean isFastClick(long stopTime) {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= stopTime) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * @param filePath
     * @return 多图片上传得到MultipartBody
     */
    public static MultipartBody filesToMultipartBody(List<String> filePath) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (filePath != null && filePath.size() > 0) {
            for (String path : filePath) {
                path = PhotoUtils.compressImage(path);
                File file = new File(path);
                // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                builder.addFormDataPart(filePath.size() > 1 ? "fileList" : "file", file.getName(), requestBody);
            }
            builder.setType(MultipartBody.FORM);
        }
        return builder.build();
    }

    /**
     * @param activity
     * @param uri
     * @return 通过uri获取文件的绝对路径
     */
    public static String getRealFilePath(Activity activity, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = activity.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * @param a 原Float 类型的值
     * @param n 保留几位小数
     * @return float保留几位小数的操作
     */
    public static float getRoundFloat(float a, int n) {
        float b = (float) (Math.round(a * 10 * n)) / (10 * n);
        return b;
    }

    /**
     * 获取sd卡存储目录
     *
     * @param dirName 功能文件夹名
     */
    public static String getSDCardCachePATH(String dirName) {
        String path = "";
        if (!externalMemoryAvailable()) {
            path = Environment.getDataDirectory() + "/data/cn.bjou.app/" + dirName;
        } else {
            path = Environment.getExternalStorageDirectory() + "/Android/data/cn.bjou.app/" + dirName;
        }
        return path;
    }

    private static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取输入框内容（去除前后空格）
     */
    public static String getEditTextString(EditText editText) {
        return editText.getText().toString().trim();
    }


    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(blockSize);
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }


    /**
     * @param wbAcJiangYi webView 设置
     */
    public static void webSettings(WebView wbAcJiangYi, String pdfUrl) {
        WebSettings wbSettings = wbAcJiangYi.getSettings();
        wbSettings.setJavaScriptEnabled(true);
        wbSettings.setAllowFileAccess(true);
        wbAcJiangYi.setWebChromeClient(new WebChromeClient());
        wbAcJiangYi.setWebViewClient(new WebViewClient());
        //支持屏幕缩放
        wbSettings.setSupportZoom(true);
        wbSettings.setBuiltInZoomControls(true);
        wbSettings.setDisplayZoomControls(false); //隐藏缩放控件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            wbSettings.setAllowFileAccessFromFileURLs(true);
            wbSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        wbAcJiangYi.loadUrl(pdfUrl);
    }

    /**
     * 获取是否存在NavigationBar
     *
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar() {
        boolean hasNavigationBar = false;
        Resources rs = UIUtils.getContext().getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }

    /**
     * 获取虚拟功能键高度
     *
     * @param context
     * @return
     */
    public static int getVirtualBarHeigh() {
        int vh = 0;
        WindowManager windowManager = (WindowManager) UIUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }


}
