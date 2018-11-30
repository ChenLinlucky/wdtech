package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.MovieXiangNews;
import com.bw.movie.bean.PaiXiangNews;
import com.bw.movie.bean.YingYuanPinglunNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.YingyuanXiangNews;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.bw.movie.mvp.contract.MovieXiangqingContract;

import java.util.Map;

import io.reactivex.Observable;


@ActivityScope
public class MovieXiangqingModel extends BaseModel implements MovieXiangqingContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MovieXiangqingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<YingyuanXiangNews> requestYingyuanXiang(int cinemaId) {
        Observable<YingyuanXiangNews> yingyuanxiangqing = mRepositoryManager.obtainRetrofitService(ApiService.class).yingyuanxiangqing(cinemaId);
        return yingyuanxiangqing;
    }

    @Override
    public Observable<YingyuanPaiqi> requestYingyuanPaiqi(Map<String,String>map) {
        Observable<YingyuanPaiqi> yingyuanPaiqiObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).YingyuanPaiqi(map);
        return yingyuanPaiqiObservable;
    }

    @Override
    public Observable<PaiXiangNews> requestXiangqing(Map<String, String> map) {
        Observable<PaiXiangNews> paiXiangNewsObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).PaiXiangqing(map);
        return paiXiangNewsObservable;
    }

    @Override
    public Observable<MovieXiangNews> reuqestMovieXiang(Map<String, String> map) {
        Observable<MovieXiangNews> movieXiangNewsObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).movieXiang(map);
        return movieXiangNewsObservable;
    }

    @Override
    public Observable<YingYuanPinglunNews> requestYingyuanPinglun(Map<String, String> map) {
        Observable<YingYuanPinglunNews> yingYuanPinglunNewsObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).YPinglung(map);
        return yingYuanPinglunNewsObservable;
    }

}