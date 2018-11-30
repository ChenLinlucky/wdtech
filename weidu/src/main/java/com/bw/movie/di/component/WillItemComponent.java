package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.di.module.WillItemModule;
import com.bw.movie.mvp.ui.fragment.WillItemFragment;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = WillItemModule.class, dependencies = AppComponent.class)
public interface WillItemComponent {
    void inject(WillItemFragment fragment);
}