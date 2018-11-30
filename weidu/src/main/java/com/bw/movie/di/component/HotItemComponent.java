package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.di.module.HotItemModule;
import com.bw.movie.mvp.ui.fragment.HotItemFragment;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = HotItemModule.class, dependencies = AppComponent.class)
public interface HotItemComponent {
    void inject(HotItemFragment fragment);
}