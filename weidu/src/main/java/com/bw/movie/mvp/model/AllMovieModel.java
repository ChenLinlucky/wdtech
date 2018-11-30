package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.AllYingyuanNews;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.bw.movie.mvp.contract.AllMovieContract;

import io.reactivex.Observable;


@FragmentScope
public class AllMovieModel extends BaseModel implements AllMovieContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public AllMovieModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<AllYingyuanNews> requestAll() {
        Observable<AllYingyuanNews> all = mRepositoryManager.obtainRetrofitService(ApiService.class).all();
        return all;
    }
}