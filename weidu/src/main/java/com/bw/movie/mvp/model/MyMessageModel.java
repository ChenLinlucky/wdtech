package com.bw.movie.mvp.model;

import android.app.Application;

import com.bw.movie.bean.TouxiangNews;
import com.bw.movie.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.bw.movie.mvp.contract.MyMessageContract;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


@ActivityScope
public class MyMessageModel extends BaseModel implements MyMessageContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MyMessageModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<TouxiangNews> settingData(HashMap<String, String> map, File outFile) {
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", outFile.getName(),
                RequestBody.create(MediaType.parse("image/*"), outFile));
        Observable<TouxiangNews> touxiang = mRepositoryManager.obtainRetrofitService(ApiService.class).Touxiang(map, filePart);
        return touxiang;
    }
}