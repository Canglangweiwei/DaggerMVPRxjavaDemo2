package zjl.com.dagger_mvp_rxjava_demo2.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsDetailBean;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsListBean;

@SuppressWarnings("ALL")
public interface ApiService {

    @GET("stories/latest")
    Observable<NewsListBean> getLatestNews();

    @GET("stories/before/{date}")
    Observable<NewsListBean> getBeforeNews(@Path("date") String date);

    @GET("story/{id}")
    Observable<NewsDetailBean> getNewsDetail(@Path("id") int id);
}