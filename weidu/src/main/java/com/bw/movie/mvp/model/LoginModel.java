package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.WeiXinLoginBean;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.bw.movie.mvp.contract.LoginContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import java.util.Map;

import io.reactivex.Observable;


@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<LoginBean> reuquestLogin(Map<String, String> map) {
        Observable<LoginBean> loginBeanObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).postResponseLogin(map);
        return loginBeanObservable;
    }

    @Override
    public Observable<WeiXinLoginBean> requesyWechat(String code) {
        Observable<WeiXinLoginBean> weiXinLoginBeanObservable = mRepositoryManager.obtainRetrofitService(ApiService.class).WeChat(code);
        return weiXinLoginBeanObservable;
    }
}