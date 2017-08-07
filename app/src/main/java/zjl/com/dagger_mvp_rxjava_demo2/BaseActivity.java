package zjl.com.dagger_mvp_rxjava_demo2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.DaggerApplication;

@SuppressWarnings("ALL")
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        setupComponent(DaggerApplication.get(this).component());
        ButterKnife.bind(this);
        initUi();
        initDatas();
        initListener();
    }

    /**
     * 页面绑定
     */
    public abstract int initContentView();

    /**
     * Dagger2绑定
     *
     * @param component AppComponent
     */
    protected abstract void setupComponent(AppComponent component);

    /**
     * 初始化UI
     */
    protected abstract void initUi();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 初始化监听事件
     */
    protected abstract void initListener();

    /**
     * 取消ButterKnife绑定
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
