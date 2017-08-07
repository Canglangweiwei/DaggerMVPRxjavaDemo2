package zjl.com.dagger_mvp_rxjava_demo2.ui.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsBean;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsListBean;
import zjl.com.dagger_mvp_rxjava_demo2.ui.store.NewsStore;
import zjl.com.dagger_mvp_rxjava_demo2.ui.contract.NewsContract;
import zjl.com.dagger_mvp_rxjava_demo2.utils.Validator;


@SuppressWarnings("ALL")
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View view;
    private Subscription mSubscription;
    private ArrayList<NewsBean> listdata = new ArrayList<>();
    private Validator validator;

    public void checkInput(String username, String password) {
        view.canLogin(validator.validUsername(username) && validator.validPassword(password));
    }

    @Inject
    NewsStore newsStore;

    @Inject
    public NewsPresenter(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void getBeforeNewsListData(String date) {
        mSubscription = newsStore.getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsListBean>() {
                    @Override
                    public void call(NewsListBean newsListBean) {
                        view.getDate(newsListBean.getDate());
                        for (NewsBean newsBean : newsListBean.getStories()) {
                            listdata.add(newsBean);
                        }
                        view.load(listdata);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void getNewsListData() {
        mSubscription = newsStore.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsListBean>() {
                    @Override
                    public void call(NewsListBean newsListBean) {
                        view.getDate(newsListBean.getDate());
                        listdata.clear();
                        for (NewsBean newsBean : newsListBean.getStories()) {
                            listdata.add(newsBean);
                        }
                        view.refresh(listdata);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void getRefreshNewsListData() {
        mSubscription = newsStore.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsListBean>() {
                    @Override
                    public void call(NewsListBean newsListBean) {
                        view.getDate(newsListBean.getDate());
                        listdata.clear();
                        for (NewsBean newsBean : newsListBean.getStories()) {
                            listdata.add(newsBean);
                        }
                        view.refresh(listdata);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void attachView(@NonNull NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null
                && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        view = null;
    }
}
