package com.bw.movie.di.module;

import com.bw.movie.mvp.contract.MovieContract;
import com.bw.movie.mvp.model.MovieModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class MovieModule {
    private MovieContract.View view;

    /**
     * 构建MovieModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MovieModule(MovieContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    MovieContract.View provideMovieView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    MovieContract.Model provideMovieModel(MovieModel model) {
        return model;
    }
}