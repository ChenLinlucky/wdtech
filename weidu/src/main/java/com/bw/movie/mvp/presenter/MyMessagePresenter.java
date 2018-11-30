package com.bw.movie.mvp.presenter;

import android.app.Application;

import com.bw.movie.bean.TouxiangNews;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.bw.movie.mvp.contract.MyMessageContract;

import java.io.File;
import java.util.HashMap;


@ActivityScope
public class MyMessagePresenter extends BasePresenter<MyMessageContract.Model, MyMessageContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MyMessagePresenter(MyMessageContract.Model model, MyMessageContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void SettingTouxiang(HashMap<String, String> map, File outFile) {
        Observable<TouxiangNews> touxiangNewsObservable = mModel.settingData(map, outFile);
        touxiangNewsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TouxiangNews>() {
                    @Override
                    public void accept(TouxiangNews touxiangNews) throws Exception {
                        mRootView.outTouxiang(touxiangNews);
                    }
                });
    }
}
