package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.activity.MainActivity;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}