package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.activity.xiangqingActivity;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.xiangqingModule;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = xiangqingModule.class, dependencies = AppComponent.class)
public interface xiangqingComponent {
    void inject(xiangqingActivity activity);
}