package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.ChangePasswordBean;
import com.bw.movie.mvp.contract.ChangePasswordContract;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class ChangePasswordModel extends BaseModel implements ChangePasswordContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ChangePasswordModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<ChangePasswordBean> requestChangePassword(HashMap<String, String> hashMap, HashMap<String, String> hashMap1) {
        Observable<ChangePasswordBean> observable = mRepositoryManager.obtainRetrofitService(ApiService.class).ChangePassword(hashMap, hashMap1);
        return observable;
    }
}