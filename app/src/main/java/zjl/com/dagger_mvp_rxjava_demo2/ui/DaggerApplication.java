package zjl.com.dagger_mvp_rxjava_demo2.ui;

import android.app.Application;
import android.content.Context;

import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppModule;
import zjl.com.dagger_mvp_rxjava_demo2.app.DaggerAppComponent;

@SuppressWarnings("ALL")
public class DaggerApplication extends Application {

    private AppComponent appComponent;

    public static DaggerApplication get(Context context) {
        return (DaggerApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplicationComponent();
    }

    private void setApplicationComponent() {
        // Dagger开头的注入类DaggerAppComponent
        appComponent = DaggerAppComponent.builder()
                // 此时appModule方法是过时方法，因为我们没有使用到任何一个module中提供的对象
                .appModule(new AppModule(this))
                .build();
    }

    /**
     * 获取AppComponent
     */
    public AppComponent component() {
        if (appComponent == null) {
            setApplicationComponent();
        }
        return appComponent;
    }
}
