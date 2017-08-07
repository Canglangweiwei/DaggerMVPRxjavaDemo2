package zjl.com.dagger_mvp_rxjava_demo2.ui.module;

import dagger.Module;
import dagger.Provides;
import zjl.com.dagger_mvp_rxjava_demo2.ui.contract.NewsContract;
import zjl.com.dagger_mvp_rxjava_demo2.utils.Validator;

@SuppressWarnings("ALL")
@Module
public class NewsModule {

    private final NewsContract.View view;
    private Validator validator;

    /**
     * 构造器
     *
     * @param view      页面
     * @param validator 账户、密码验证工具
     */
    public NewsModule(NewsContract.View view, Validator validator) {
        this.view = view;
        this.validator = validator;
    }

    /**
     * 在@Module注解的类中，使用@Provider注解，说明提供依赖注入的具体对象
     */
    @Provides
    public Validator provideValidator() {
        return validator;
    }
}
