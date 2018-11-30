package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.HomeItemContract;
import com.bw.movie.mvp.model.HomeItemModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class HomeItemModule {
    private HomeItemContract.View view;

    /**
     * 构建HomeItemModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HomeItemModule(HomeItemContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HomeItemContract.View provideHomeItemView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HomeItemContract.Model provideHomeItemModel(HomeItemModel model) {
        return model;
    }
}