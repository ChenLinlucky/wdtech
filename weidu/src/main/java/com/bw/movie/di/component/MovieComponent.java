package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.di.module.MovieModule;
import com.bw.movie.mvp.ui.fragment.MovieFragment;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = MovieModule.class, dependencies = AppComponent.class)
public interface MovieComponent {
    void inject(MovieFragment fragment);
}