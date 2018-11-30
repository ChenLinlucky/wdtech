package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.HotItemContract;
import com.bw.movie.mvp.model.HotItemModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class HotItemModule {
    private HotItemContract.View view;

    /**
     * 构建HotItemModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HotItemModule(HotItemContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    HotItemContract.View provideHotItemView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    HotItemContract.Model provideHotItemModel(HotItemModel model) {
        return model;
    }
}