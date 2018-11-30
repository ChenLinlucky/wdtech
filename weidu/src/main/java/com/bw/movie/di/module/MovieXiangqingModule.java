package com.bw.movie.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.bw.movie.mvp.contract.MovieXiangqingContract;
import com.bw.movie.mvp.model.MovieXiangqingModel;


@Module
public class MovieXiangqingModule {
    private MovieXiangqingContract.View view;

    /**
     * 构建MovieXiangqingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MovieXiangqingModule(MovieXiangqingContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MovieXiangqingContract.View provideMovieXiangqingView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MovieXiangqingContract.Model provideMovieXiangqingModel(MovieXiangqingModel model) {
        return model;
    }
}