package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.di.module.HomeItemModule;
import com.bw.movie.mvp.ui.activity.HomeItemActivity;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = HomeItemModule.class, dependencies = AppComponent.class)
public interface HomeItemComponent {
    void inject(HomeItemActivity activity);
}