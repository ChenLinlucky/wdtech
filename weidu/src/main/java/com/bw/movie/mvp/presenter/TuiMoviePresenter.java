package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.TuiYingyuanNews;
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

import com.bw.movie.mvp.contract.TuiMovieContract;


@FragmentScope
public class TuiMoviePresenter extends BasePresenter<TuiMovieContract.Model, TuiMovieContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public TuiMoviePresenter(TuiMovieContract.Model model, TuiMovieContract.View rootView) {
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
    public void tuijian(){
        Observable<TuiYingyuanNews> requesttuijian = mModel.requesttuijian();
        requesttuijian.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TuiYingyuanNews>() {
                    @Override
                    public void accept(TuiYingyuanNews tuiYingyuanNews) throws Exception {
                        mRootView.data(tuiYingyuanNews);
                    }
                });
    }
}
