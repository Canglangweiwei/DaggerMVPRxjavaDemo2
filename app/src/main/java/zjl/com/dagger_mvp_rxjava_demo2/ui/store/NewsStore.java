package zjl.com.dagger_mvp_rxjava_demo2.ui.store;

import javax.inject.Inject;

import rx.Observable;
import zjl.com.dagger_mvp_rxjava_demo2.api.RetrofitFactory;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsListBean;

/**
 * 数据请求管理仓库
 */
@SuppressWarnings("ALL")
public class NewsStore {

    @Inject
    public NewsStore() {
        super();
    }

    /**
     * 获取最新新闻列表
     */
    public Observable<NewsListBean> getLatestNews() {
        return RetrofitFactory.getLatestNews();
    }

    /**
     * 获取当前日期之前的新闻列表
     *
     * @param date 日期
     */
    public Observable<NewsListBean> getBeforeNews(String date) {
        return RetrofitFactory.getBeforeNews(date);
    }
}













