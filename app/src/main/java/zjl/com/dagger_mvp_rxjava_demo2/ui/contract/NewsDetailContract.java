package zjl.com.dagger_mvp_rxjava_demo2.ui.contract;


import zjl.com.dagger_mvp_rxjava_demo2.BasePresenter;
import zjl.com.dagger_mvp_rxjava_demo2.BaseView;

@SuppressWarnings("ALL")
public interface NewsDetailContract {

    interface View extends BaseView {
        void showData(String image, String title, String image_source, StringBuffer body);
    }

    interface Presenter extends BasePresenter<View> {
        void getNewsDetailData();
    }
}
