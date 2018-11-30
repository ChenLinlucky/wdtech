package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.activity.ShowActivity;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.ShowModule;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = ShowModule.class, dependencies = AppComponent.class)
public interface ShowComponent {
    void inject(ShowActivity activity);
}