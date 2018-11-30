package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.XiaDanNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.wxBean;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.bw.movie.mvp.contract.BuyTicketContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


@ActivityScope
public class BuyTicketModel extends BaseModel implements BuyTicketContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public BuyTicketModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
    //排期
    @Override
    public Observable<YingyuanPaiqi> requestYingyuanPaiqi(Map<String,String> map) {
        Observable<YingyuanPaiqi> yingyuanPaiqiObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).YingyuanPaiqi(map);
        return yingyuanPaiqiObservable;
    }

    //下单
    @Override
    public Observable<XiaDanNews> requestXiaDan(HashMap<String, String> map, HashMap<String, String> map1) {
        Observable<XiaDanNews> xiaDanNewsObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).XiaDan(map, map1);
        return xiaDanNewsObservable;
    }

    //支付宝--支付
    @Override
    public Observable<ResponseBody> requestzhifubao(HashMap<String, String> map, HashMap<String, String> map1) {
        Observable<ResponseBody> zhifubao = mRepositoryManager.obtainRetrofitService(ApiService.class).zhifubao(map, map1);
        return zhifubao;
    }
    //支付宝--微信
    @Override
    public Observable<wxBean> requestWinxin(HashMap<String, String> map, HashMap<String, String> map1) {
        Observable<wxBean> weixin = mRepositoryManager.obtainRetrofitService(ApiService.class).weixin(map, map1);
        return weixin;
    }

}