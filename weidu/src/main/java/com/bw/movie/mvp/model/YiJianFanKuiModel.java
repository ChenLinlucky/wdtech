package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.YiJianFanKuiBean;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.bw.movie.mvp.contract.YiJianFanKuiContract;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.HeaderMap;


@ActivityScope
public class YiJianFanKuiModel extends BaseModel implements YiJianFanKuiContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public YiJianFanKuiModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<YiJianFanKuiBean> FankuiData(@HeaderMap HashMap<String,String> map,@Field("content") String content) {
        Observable<YiJianFanKuiBean> yiJianFanKuiBeanObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).YiJianfankui(map, content);
        return yiJianFanKuiBeanObservable;
    }
}