package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.WillNews;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.bw.movie.mvp.contract.WillItemContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import io.reactivex.Observable;


@FragmentScope
public class WillItemModel extends BaseModel implements WillItemContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WillItemModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<WillNews> requestWill() {
        Observable<WillNews> will = mRepositoryManager.obtainRetrofitService(ApiService.class).will();
        return will;
    }
}