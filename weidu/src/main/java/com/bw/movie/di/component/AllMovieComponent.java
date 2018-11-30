package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.fragment.AllMovieFragment;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.AllMovieModule;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = AllMovieModule.class, dependencies = AppComponent.class)
public interface AllMovieComponent {
    void inject(AllMovieFragment fragment);
}