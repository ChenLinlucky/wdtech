package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.HotNews;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.bw.movie.mvp.contract.HotItemContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import io.reactivex.Observable;


@FragmentScope
public class HotItemModel extends BaseModel implements HotItemContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HotItemModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
    @Override
    public Observable<HotNews> requestHot() {
        Observable<HotNews> hot = mRepositoryManager.obtainRetrofitService(ApiService.class).hot();
        return hot;
    }
}