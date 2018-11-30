package com.bw.movie.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.YiJianFanKuiModule;

import com.jess.arms.di.scope.ActivityScope;
import com.bw.movie.mvp.ui.activity.YiJianFanKuiActivity;

@ActivityScope
@Component(modules = YiJianFanKuiModule.class, dependencies = AppComponent.class)
public interface YiJianFanKuiComponent {
    void inject(YiJianFanKuiActivity activity);
}