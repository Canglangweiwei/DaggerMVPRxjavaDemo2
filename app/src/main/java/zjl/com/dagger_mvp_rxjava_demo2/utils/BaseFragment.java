package zjl.com.dagger_mvp_rxjava_demo2.utils;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.DaggerApplication;

@SuppressWarnings("ALL")
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(initContentView(), container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupComponent(DaggerApplication.get(getActivity()).component());
        initUi();
        initDatas();
        initListener();
    }

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
     * Dagger2绑定
     *
     * @param component AppComponent
     */
    protected abstract void setupComponent(AppComponent component);

    /**
     * 页面绑定
     */
    public abstract int initContentView();
}
