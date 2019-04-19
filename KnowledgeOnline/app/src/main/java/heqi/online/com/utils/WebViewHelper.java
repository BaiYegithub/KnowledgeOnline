package heqi.online.com.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2018/10/31 0031.
 */

public class WebViewHelper {

    private static WebViewClient webViewClient;
    private static ProgressBar pro;
    private static ProgressBar pgb;
    private static WebChromeClient webChromeClient;

    public static WebViewClient getwebviewclient(ProgressBar progressBar) {
        pro = progressBar;
        if (webViewClient == null) {
            webViewClient = new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return super.shouldOverrideUrlLoading(view, request);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pro.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    pro.setVisibility(View.GONE);
                }
            };
        }
        return webViewClient;

    }

    public static WebChromeClient getwebchrome(final ProgressBar progressBar) {

        pgb = progressBar;
        if (webChromeClient == null) {
            webChromeClient = new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    pgb.setProgress(newProgress);
                }
            };
        }
        return webChromeClient;
    }

}
