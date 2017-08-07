package zjl.com.dagger_mvp_rxjava_demo2.ui.contract;

import java.util.ArrayList;

import zjl.com.dagger_mvp_rxjava_demo2.BasePresenter;
import zjl.com.dagger_mvp_rxjava_demo2.BaseView;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsBean;

@SuppressWarnings("ALL")
public interface NewsContract {

    interface View extends BaseView {
        void getDate(String date);

        void load(ArrayList<NewsBean> list);

        void refresh(ArrayList<NewsBean> list);

        void canLogin(boolean allowedLogin);
    }

    interface Presenter extends BasePresenter<View> {
        void getBeforeNewsListData(String date);

        void getNewsListData();

        void getRefreshNewsListData();
    }
}