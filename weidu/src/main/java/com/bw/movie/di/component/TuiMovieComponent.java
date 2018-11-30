package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.fragment.TuiMovieFragment;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.TuiMovieModule;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = TuiMovieModule.class, dependencies = AppComponent.class)
public interface TuiMovieComponent {
    void inject(TuiMovieFragment fragment);
}