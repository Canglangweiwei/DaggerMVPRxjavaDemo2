package zjl.com.dagger_mvp_rxjava_demo2.ui.component;

import dagger.Component;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.NewsDetailActivity;
import zjl.com.dagger_mvp_rxjava_demo2.ui.module.NewsDetailModule;

/**
 * 简单说就是，可以通过Component访问到Module中提供的依赖注入对象。
 * 假设，如果有两个Module，AModule、BModule，
 * 如果Component只注册了AModule，而没有注册BModule，那么BModule中提供的对象，无法进行依赖注入！
 */
@SuppressWarnings("ALL")
@Component(dependencies = AppComponent.class, modules = {NewsDetailModule.class})
public interface NewsDetailPresenterComponent {
    void inject(NewsDetailActivity newsDetailActivity);
}
