package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.activity.ReginActivity;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.ReginModule;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = ReginModule.class, dependencies = AppComponent.class)
public interface ReginComponent {
    void inject(ReginActivity activity);
}