package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.WillItemContract;
import com.bw.movie.mvp.model.WillItemModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class WillItemModule {
    private WillItemContract.View view;

    /**
     * 构建WillItemModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WillItemModule(WillItemContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    WillItemContract.View provideWillItemView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    WillItemContract.Model provideWillItemModel(WillItemModel model) {
        return model;
    }
}