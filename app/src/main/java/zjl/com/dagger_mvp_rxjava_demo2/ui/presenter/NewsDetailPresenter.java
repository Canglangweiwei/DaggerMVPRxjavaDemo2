package zjl.com.dagger_mvp_rxjava_demo2.ui.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsDetailBean;
import zjl.com.dagger_mvp_rxjava_demo2.ui.store.NewsDetailStore;
import zjl.com.dagger_mvp_rxjava_demo2.ui.contract.NewsDetailContract;
import zjl.com.dagger_mvp_rxjava_demo2.utils.HtmlUtil;

@SuppressWarnings("ALL")
public class NewsDetailPresenter implements NewsDetailContract.Presenter {

    private int id;
    private NewsDetailContract.View view;
    private Subscription mSubscription;

    @Inject
    NewsDetailStore newsDetailStore;

    /**
     * 这个注解是用来说明该注解下方的属性或方法需要依赖注入。
     * （如果使用在类构造方法上，则该类也会被注册在DI容器中作为注入对象。很重要，理解这个，就能理解了Presenter注入到Activity的步骤！）
     *
     * @param id
     */
    @Inject
    public NewsDetailPresenter(int id) {
        this.id = id;
    }

    @Override
    public void getNewsDetailData() {
        mSubscription = newsDetailStore.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsDetailBean>() {

                    @Override
                    public void call(NewsDetailBean newsDetailBean) {
                        StringBuffer stringBuffer = HtmlUtil.handleHtml(newsDetailBean.getBody());
                        view.showData(newsDetailBean.getImage(), newsDetailBean.getTitle(), newsDetailBean.getImage_source(), stringBuffer);
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    /**
     * 建立页面关联
     */
    @Override
    public void attachView(@NonNull NewsDetailContract.View view) {
        this.view = view;
    }

    /**
     * 解除页面关联
     */
    @Override
    public void detachView() {
        if (mSubscription != null
                && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        view = null;
    }
}
