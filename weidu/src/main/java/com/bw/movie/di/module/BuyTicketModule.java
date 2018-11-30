package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.BuyTicketContract;
import com.bw.movie.mvp.model.BuyTicketModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class BuyTicketModule {
    private BuyTicketContract.View view;

    /**
     * 构建BuyTicketModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BuyTicketModule(BuyTicketContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BuyTicketContract.View provideBuyTicketView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BuyTicketContract.Model provideBuyTicketModel(BuyTicketModel model) {
        return model;
    }
}