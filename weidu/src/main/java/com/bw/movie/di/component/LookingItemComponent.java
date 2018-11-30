package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.fragment.LookingItemFragment;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.LookingItemModule;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = LookingItemModule.class, dependencies = AppComponent.class)
public interface LookingItemComponent {
    void inject(LookingItemFragment fragment);
}