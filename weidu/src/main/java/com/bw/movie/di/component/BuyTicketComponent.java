package com.bw.movie.di.component;

import dagger.Component;

import com.bw.movie.mvp.ui.activity.BuyTicketActivity;
import com.jess.arms.di.component.AppComponent;

import com.bw.movie.di.module.BuyTicketModule;

import com.jess.arms.di.scope.ActivityScope;

@ActivityScope
@Component(modules = BuyTicketModule.class, dependencies = AppComponent.class)
public interface BuyTicketComponent {
    void inject(BuyTicketActivity activity);
}