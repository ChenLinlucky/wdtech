package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.TuiMovieContract;
import com.bw.movie.mvp.model.TuiMovieModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class TuiMovieModule {
    private TuiMovieContract.View view;

    /**
     * 构建TuiMovieModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TuiMovieModule(TuiMovieContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    TuiMovieContract.View provideTuiMovieView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    TuiMovieContract.Model provideTuiMovieModel(TuiMovieModel model) {
        return model;
    }
}