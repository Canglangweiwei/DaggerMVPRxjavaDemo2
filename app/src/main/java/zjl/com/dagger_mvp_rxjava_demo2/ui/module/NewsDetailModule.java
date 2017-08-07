package zjl.com.dagger_mvp_rxjava_demo2.ui.module;

import dagger.Module;
import dagger.Provides;
import zjl.com.dagger_mvp_rxjava_demo2.ui.contract.NewsDetailContract;

@SuppressWarnings("ALL")
@Module
public class NewsDetailModule {

    private final NewsDetailContract.View view;
    private int id;

    public NewsDetailModule(NewsDetailContract.View view, int id) {
        this.view = view;
        this.id = id;
    }

    /**
     * 在@Module注解的类中，使用@Provider注解，说明提供依赖注入的具体对象
     */
    @Provides
    public int provideid() {
        return id;
    }
}
