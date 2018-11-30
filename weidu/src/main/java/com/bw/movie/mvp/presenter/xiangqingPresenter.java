package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.PinglunNews;
import com.bw.movie.bean.XiangqingNews;
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

import com.bw.movie.mvp.contract.xiangqingContract;

import java.util.HashMap;


@ActivityScope
public class xiangqingPresenter extends BasePresenter<xiangqingContract.Model, xiangqingContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public xiangqingPresenter(xiangqingContract.Model model, xiangqingContract.View rootView) {
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
    public void xiangqing(int movieId){
        Observable<XiangqingNews> requestxiangqing = mModel.requestxiangqing(movieId);
        requestxiangqing.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XiangqingNews>() {
                    @Override
                    public void accept(XiangqingNews xiangqingNews) throws Exception {
                        mRootView.data(xiangqingNews);
                    }
                });
    }
    //影片评论
    @SuppressLint("CheckResult")
    public void pinglun(HashMap<String,String> map){
        Observable<PinglunNews> requestpingnlun = mModel.requestpingnlun(map);
        requestpingnlun.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PinglunNews>() {
                    @Override
                    public void accept(PinglunNews pinglunNews) throws Exception {
                        mRootView.datapinglun(pinglunNews);
                    }
                });
    }
}
