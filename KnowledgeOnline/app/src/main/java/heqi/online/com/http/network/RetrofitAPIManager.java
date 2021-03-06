package heqi.online.com.http.network;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import heqi.online.com.http.url.HttpUrlUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @describe 描述 retrofit 网络请求类
 */
public class RetrofitAPIManager {
    private static RetrofitAPIManager retrofitAPIManager;

    private ApiService apiService;

    private WanApiService wanApiService;

    public static RetrofitAPIManager getInstance() {
        if (retrofitAPIManager == null) {
            synchronized (RetrofitAPIManager.class) {
                retrofitAPIManager = new RetrofitAPIManager();
            }
        }
        return retrofitAPIManager;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public WanApiService getWanApiService() {
        return wanApiService;
    }

    private RetrofitAPIManager() {//创建retrofit+okhttp
        apiService = setURLRetrofit(HttpUrlUtils.BASE_URL).create(ApiService.class);
        wanApiService = setURLRetrofit(HttpUrlUtils.BASE_URL2).create(WanApiService.class);
    }

    public static Retrofit setURLRetrofit(String url) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(genericClient())
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient genericClient() {

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

}
