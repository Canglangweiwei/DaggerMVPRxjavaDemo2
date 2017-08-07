package zjl.com.dagger_mvp_rxjava_demo2.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import zjl.com.dagger_mvp_rxjava_demo2.R;
import zjl.com.dagger_mvp_rxjava_demo2.adapter.NewsListAdapter;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsBean;
import zjl.com.dagger_mvp_rxjava_demo2.ui.component.DaggerNewsPresenterComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.contract.NewsContract;
import zjl.com.dagger_mvp_rxjava_demo2.ui.module.NewsModule;
import zjl.com.dagger_mvp_rxjava_demo2.ui.presenter.NewsPresenter;
import zjl.com.dagger_mvp_rxjava_demo2.utils.BaseFragment;
import zjl.com.dagger_mvp_rxjava_demo2.utils.Validator;


@SuppressWarnings("ALL")
public class NewsFragment extends BaseFragment implements NewsContract.View {

    @Bind(R.id.recyclerview)
    XRecyclerView mxRecyclerView;

    private NewsListAdapter newsListAdapter;
    private List<NewsBean> listData = new ArrayList<>();

    @Inject
    NewsPresenter newsPresenter;

    // 当前日期
    private String currentDate;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected void initUi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mxRecyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mxRecyclerView.setLayoutManager(gridLayoutManager);

        mxRecyclerView.setRefreshProgressStyle(ProgressStyle.BallRotate);           // 设置刷新类型
        mxRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SemiCircleSpin);   // 设置加载类型
        mxRecyclerView.setRefreshing(true);
    }

    @Override
    protected void initDatas() {
        newsListAdapter = new NewsListAdapter(getActivity(), listData);
        mxRecyclerView.setAdapter(newsListAdapter);

        newsPresenter.attachView(this);
        newsPresenter.getNewsListData();
    }

    @Override
    protected void initListener() {
        mxRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                /**
                 * 刷新
                 */
                newsPresenter.getRefreshNewsListData();
            }

            @Override
            public void onLoadMore() {
                /**
                 * 加载更多
                 */
                newsPresenter.getBeforeNewsListData(currentDate);
            }
        });
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerNewsPresenterComponent.builder()
                .appComponent(component)
                .newsModule(new NewsModule(this, new Validator()))
                .build()
                .inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_news;
    }

    @Override
    public void getDate(String date) {
        this.currentDate = date;
    }

    @Override
    public void load(ArrayList<NewsBean> list) {
        mxRecyclerView.loadMoreComplete();
        listData.clear();
        listData.addAll(list);
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh(ArrayList<NewsBean> list) {
        mxRecyclerView.refreshComplete();
        listData.clear();
        listData.addAll(list);
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void canLogin(boolean allowedLogin) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsPresenter.detachView();
    }
}
