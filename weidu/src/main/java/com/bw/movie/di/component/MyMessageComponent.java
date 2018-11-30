package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.di.module.MyMessageModule;
import com.bw.movie.mvp.ui.activity.MyMessageActivity;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = MyMessageModule.class, dependencies = AppComponent.class)
public interface MyMessageComponent {
    void inject(MyMessageActivity activity);
}