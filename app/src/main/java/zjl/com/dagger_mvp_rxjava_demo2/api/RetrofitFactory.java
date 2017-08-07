package zjl.com.dagger_mvp_rxjava_demo2.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsDetailBean;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsListBean;

/**
 * 网络请求管理工厂
 */
public class RetrofitFactory {

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl("http://news-at.zhihu.com/api/4/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .client(okHttpClient)
            .build();

    private static final ApiService apiService = sRetrofit.create(ApiService.class);

    public static Observable<NewsListBean> getLatestNews() {
        return apiService.getLatestNews();
    }

    public static Observable<NewsListBean> getBeforeNews(String date) {
        return apiService.getBeforeNews(date);
    }

    public static Observable<NewsDetailBean> getNewsDetail(int id) {
        return apiService.getNewsDetail(id);
    }
}