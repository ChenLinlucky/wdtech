package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.fragment.HomeFragment;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.HomeModule;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment fragment);
}