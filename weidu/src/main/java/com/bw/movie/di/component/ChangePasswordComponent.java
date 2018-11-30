package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.activity.ChangePasswordActivity;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.ChangePasswordModule;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = ChangePasswordModule.class, dependencies = AppComponent.class)
public interface ChangePasswordComponent {
    void inject(ChangePasswordActivity activity);
}