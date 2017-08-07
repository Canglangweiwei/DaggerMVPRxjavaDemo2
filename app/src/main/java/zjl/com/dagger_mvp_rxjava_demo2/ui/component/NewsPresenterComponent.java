package zjl.com.dagger_mvp_rxjava_demo2.ui.component;

import dagger.Component;
import zjl.com.dagger_mvp_rxjava_demo2.app.AppComponent;
import zjl.com.dagger_mvp_rxjava_demo2.ui.NewsActivity;
import zjl.com.dagger_mvp_rxjava_demo2.ui.NewsFragment;
import zjl.com.dagger_mvp_rxjava_demo2.ui.module.NewsModule;

/**
 * 简单说就是，可以通过Component访问到Module中提供的依赖注入对象。
 * 假设，如果有两个Module，AModule、BModule，
 * 如果Component只注册了AModule，而没有注册BModule，那么BModule中提供的对象，无法进行依赖注入！
 */
@SuppressWarnings("ALL")
@Component(dependencies = AppComponent.class, modules = {NewsModule.class})
public interface NewsPresenterComponent {

    /**
     * 可以注册多个页面1
     */
    void inject(NewsActivity newsActivity);

    /**
     * 可以注册多个页面2
     */
    void inject(NewsFragment newsFragment);

    /**
     * 可以注册多个页面......
     */

    /**
     * 可以注册多个页面......
     */
}
