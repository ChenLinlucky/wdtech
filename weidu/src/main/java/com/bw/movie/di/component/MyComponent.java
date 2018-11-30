package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.fragment.MyFragment;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.MyModule;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = MyModule.class, dependencies = AppComponent.class)
public interface MyComponent {
    void inject(MyFragment fragment);
}