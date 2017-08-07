package zjl.com.dagger_mvp_rxjava_demo2.ui;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.functions.Action1;
import zjl.com.dagger_mvp_rxjava_demo2.BaseActivity;
import zjl.com.dagger_mvp_rxjava_demo2.R;
import zjl.com.dagger_mvp_rxjava_demo2.adapter.NewsListAdapter;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.bean.NewsBean;
import zjl.com.dagger_mvp_rxjava_demo2.ui.component.DaggerNewsPresenterComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.contract.NewsContract;
import zjl.com.dagger_mvp_rxjava_demo2.ui.module.NewsModule;
import zjl.com.dagger_mvp_rxjava_demo2.ui.presenter.NewsPresenter;
import zjl.com.dagger_mvp_rxjava_demo2.utils.Validator;


/**
 * 新闻列表
 */

@SuppressWarnings("ALL")
public class NewsActivity extends BaseActivity implements NewsContract.View {

    @Bind(R.id.mEditUsername)
    EditText mEditUsername;
    @Bind(R.id.mEditPassword)
    EditText mEditPassword;
    @Bind(R.id.btn_login)
    Button btn_login;

    @Bind(R.id.recyclerview)
    XRecyclerView mxRecyclerView;

    private NewsListAdapter newsListAdapter;
    private List<NewsBean> listData = new ArrayList<>();

    @Inject
    NewsPresenter presenter;

    // 当前日期
    private String currentDate;

    @Override
    public int initContentView() {
        return R.layout.activity_main;
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
    protected void initUi() {
        currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mxRecyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mxRecyclerView.setLayoutManager(gridLayoutManager);

        mxRecyclerView.setRefreshProgressStyle(ProgressStyle.BallRotate);           // 设置刷新类型
        mxRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SemiCircleSpin);   // 设置加载类型
        mxRecyclerView.setRefreshing(true);
    }

    @Override
    protected void initDatas() {
        newsListAdapter = new NewsListAdapter(this, listData);
        mxRecyclerView.setAdapter(newsListAdapter);

        presenter.attachView(this);
        presenter.getNewsListData();

        /**
         * 检测用户名输入框
         */
        RxTextView.textChanges(mEditUsername).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                presenter.checkInput(charSequence.toString().trim(), mEditPassword.getText().toString().trim());
            }
        });

        /**
         * 检测密码输入框
         */
        RxTextView.textChanges(mEditPassword).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                presenter.checkInput(mEditUsername.getText().toString().trim(), charSequence.toString().trim());
            }
        });

        // 测试
        loadFragment();
    }

    private void loadFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        NewsFragment fragment = NewsFragment.newInstance();
        transaction.replace(R.id.frame_content, fragment);
        transaction.commit();
    }

    @Override
    protected void initListener() {
        mxRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                /**
                 * 刷新
                 */
                presenter.getRefreshNewsListData();
            }

            @Override
            public void onLoadMore() {
                /**
                 * 加载更多
                 */
                presenter.getBeforeNewsListData(currentDate);
            }
        });
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

    /**
     * 是否允许登录
     *
     * @param allowedLogin (true/false)
     */
    @Override
    public void canLogin(boolean allowedLogin) {
        if (allowedLogin) {
            btn_login.setEnabled(true);
        } else {
            btn_login.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}


















