package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.PinglunNews;
import com.bw.movie.bean.XiangqingNews;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.bw.movie.mvp.contract.xiangqingContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import java.util.HashMap;

import io.reactivex.Observable;


@ActivityScope
public class xiangqingModel extends BaseModel implements xiangqingContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public xiangqingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<XiangqingNews> requestxiangqing(int movieId) {
        Observable<XiangqingNews> xiangqing = mRepositoryManager.obtainRetrofitService(ApiService.class).xiangqing(movieId);
        return xiangqing;
    }

    @Override
    public Observable<PinglunNews> requestpingnlun(HashMap<String, String> map) {
        Observable<PinglunNews> pinglun = mRepositoryManager.obtainRetrofitService(ApiService.class).pinglun(map);
        return pinglun;
    }
}