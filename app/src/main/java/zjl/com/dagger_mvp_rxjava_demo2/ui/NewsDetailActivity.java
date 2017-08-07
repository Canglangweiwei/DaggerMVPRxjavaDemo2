package zjl.com.dagger_mvp_rxjava_demo2.ui;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.Bind;
import zjl.com.dagger_mvp_rxjava_demo2.BaseActivity;
import zjl.com.dagger_mvp_rxjava_demo2.R;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.component.DaggerNewsDetailPresenterComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.contract.NewsDetailContract;
import zjl.com.dagger_mvp_rxjava_demo2.ui.module.NewsDetailModule;
import zjl.com.dagger_mvp_rxjava_demo2.ui.presenter.NewsDetailPresenter;

@SuppressWarnings("ALL")
public class NewsDetailActivity extends BaseActivity implements NewsDetailContract.View {

    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.iv_header)
    ImageView mIvHeader;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_source)
    TextView mTvSource;

    @Bind(R.id.wv_news)
    WebView mWvNews;
    @Bind(R.id.nested_view)
    NestedScrollView mNestedView;
    @Bind(R.id.iv_share)
    ImageView iv_share;

    private String title;

    @Inject
    NewsDetailPresenter presenter;

    @Override
    public int initContentView() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerNewsDetailPresenterComponent.builder()
                .appComponent(component)
                .newsDetailModule(new NewsDetailModule(this, getIntent().getIntExtra("new", 0)))
                .build()
                .inject(this);
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mCollapsingToolbarLayout.setTitle("Dagger");
        mCollapsingToolbarLayout.setTitleEnabled(true);
    }

    @Override
    protected void initDatas() {
        presenter.attachView(this);
        presenter.getNewsDetailData();
    }

    @Override
    protected void initListener() {
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    /**
     * 分享
     */
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「纯净日报」的分享：" + title + "，http://daily.zhihu.com/story/" + getIntent().getIntExtra("new", 0));
        startActivity(Intent.createChooser(intent, title));
    }

    @Override
    public void showData(String image, String title, String image_source, StringBuffer body) {
        Glide.with(this).load(image).into(mIvHeader);
        this.title = title;
        mTvTitle.setText(title);
        mTvSource.setText(image_source);
        mWvNews.setDrawingCacheEnabled(true);
        mWvNews.loadDataWithBaseURL("file:///android_asset/", body.toString(), "text/html", "utf-8", null);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
