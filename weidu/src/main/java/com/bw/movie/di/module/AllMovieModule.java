package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.AllMovieContract;
import com.bw.movie.mvp.model.AllMovieModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class AllMovieModule {
    private AllMovieContract.View view;

    /**
     * 构建AllMovieModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AllMovieModule(AllMovieContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    AllMovieContract.View provideAllMovieView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    AllMovieContract.Model provideAllMovieModel(AllMovieModel model) {
        return model;
    }
}