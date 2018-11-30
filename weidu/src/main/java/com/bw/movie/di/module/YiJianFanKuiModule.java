package com.bw.movie.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.bw.movie.mvp.contract.YiJianFanKuiContract;
import com.bw.movie.mvp.model.YiJianFanKuiModel;


@Module
public class YiJianFanKuiModule {
    private YiJianFanKuiContract.View view;

    /**
     * 构建YiJianFanKuiModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public YiJianFanKuiModule(YiJianFanKuiContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    YiJianFanKuiContract.View provideYiJianFanKuiView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    YiJianFanKuiContract.Model provideYiJianFanKuiModel(YiJianFanKuiModel model) {
        return model;
    }
}