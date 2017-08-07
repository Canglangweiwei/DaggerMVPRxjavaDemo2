package zjl.com.dagger_mvp_rxjava_demo2.ui.store;

import javax.inject.Inject;

import rx.Observable;
import zjl.com.dagger_mvp_rxjava_demo2.api.RetrofitFactory;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsDetailBean;

@SuppressWarnings("ALL")
public class NewsDetailStore {

    @Inject
    public NewsDetailStore() {
        super();
    }

    public Observable<NewsDetailBean> getNewsDetail(int id) {
        return RetrofitFactory.getNewsDetail(id);
    }
}
