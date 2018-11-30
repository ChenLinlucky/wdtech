package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.LookingItemContract;
import com.bw.movie.mvp.model.LookingItemModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class LookingItemModule {
    private LookingItemContract.View view;

    /**
     * 构建LookingItemModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LookingItemModule(LookingItemContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    LookingItemContract.View provideLookingItemView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    LookingItemContract.Model provideLookingItemModel(LookingItemModel model) {
        return model;
    }
}