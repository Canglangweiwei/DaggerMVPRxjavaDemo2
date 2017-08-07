package zjl.com.dagger_mvp_rxjava_demo2.app;

import dagger.Component;
import zjl.com.dagger_mvp_rxjava_demo2.ui.DaggerApplication;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(DaggerApplication daggerApplication);
}
