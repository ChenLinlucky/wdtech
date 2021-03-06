package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.HotNews;
import com.bw.movie.bean.LookingNews;
import com.bw.movie.bean.WillNews;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.bw.movie.mvp.contract.HomeContract;


@FragmentScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
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
    @SuppressLint("CheckResult")
    public void hot(){
        Observable<HotNews> hotNewsObservable = mModel.requestHot();
        hotNewsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotNews>() {
                    @Override
                    public void accept(HotNews hotNews) throws Exception {
                        mRootView.data(hotNews);
                    }
                });
    }
    @SuppressLint("CheckResult")
    public void looking(){
        Observable<LookingNews> lookingNewsObservable = mModel.requestLooking();
        lookingNewsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LookingNews>() {
                    @Override
                    public void accept(LookingNews lookingNews) throws Exception {
                        mRootView.datalooking(lookingNews);
                    }
                });
    }
    @SuppressLint("CheckResult")
    public void will(){
        Observable<WillNews> willNewsObservable = mModel.requestWill();
        willNewsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WillNews>() {
                    @Override
                    public void accept(WillNews willNews) throws Exception {
                        mRootView.datawill(willNews);
                    }
                });
    }
}
