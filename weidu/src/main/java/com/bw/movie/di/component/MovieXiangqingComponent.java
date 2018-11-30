package com.bw.movie.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.MovieXiangqingModule;

import com.jess.arms.di.scope.ActivityScope;
import com.bw.movie.mvp.ui.activity.MovieXiangqingActivity;

@ActivityScope
@Component(modules = MovieXiangqingModule.class, dependencies = AppComponent.class)
public interface MovieXiangqingComponent {
    void inject(MovieXiangqingActivity activity);
}