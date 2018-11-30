package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.MovieXiangNews;
import com.bw.movie.bean.PaiXiangNews;
import com.bw.movie.bean.YingYuanPinglunNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.YingyuanXiangNews;
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

import com.bw.movie.mvp.contract.MovieXiangqingContract;

import java.util.Map;


@ActivityScope
public class MovieXiangqingPresenter extends BasePresenter<MovieXiangqingContract.Model, MovieXiangqingContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MovieXiangqingPresenter(MovieXiangqingContract.Model model, MovieXiangqingContract.View rootView) {
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
    public void yingyuanxiangqing(int cinemaId){
        Observable<YingyuanXiangNews> yingyuanXiangNewsObservable = mModel.requestYingyuanXiang(cinemaId);
        yingyuanXiangNewsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YingyuanXiangNews>() {
                    @Override
                    public void accept(YingyuanXiangNews yingyuanXiangNews) throws Exception {
                         mRootView.data(yingyuanXiangNews);
                    }
                });
    }
    //影院排期列表
    @SuppressLint("CheckResult")
    public void yingyuanPaiqiObservable(Map<String,String>map){
        Observable<YingyuanPaiqi> yingyuanPaiqiObservable = mModel.requestYingyuanPaiqi(map);
        yingyuanPaiqiObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YingyuanPaiqi>() {
                    @Override
                    public void accept(YingyuanPaiqi yingyuanPaiqi) throws Exception {
                        mRootView.YingyuanPaiqidata(yingyuanPaiqi);
                    }
                });
    }

    //排期----Popwindow详情
    @SuppressLint("CheckResult")
    public void paiXiangNewsObservable(Map<String,String> map){
        Observable<PaiXiangNews> paiXiangNewsObservable = mModel.requestXiangqing(map);
        paiXiangNewsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PaiXiangNews>() {
                    @Override
                    public void accept(PaiXiangNews paiXiangNews) throws Exception {
                        mRootView.PaiXiangqinigData(paiXiangNews);
                    }
                });
    }
    //根据影院Id进入详情页面
    @SuppressLint("CheckResult")
    public void movieXiangNewsObservable(Map<String,String> map){
        Observable<MovieXiangNews> movieXiangNewsObservable = mModel.reuqestMovieXiang(map);
        movieXiangNewsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieXiangNews>() {
                    @Override
                    public void accept(MovieXiangNews movieXiangNews) throws Exception {
                        mRootView.MovieXiangData(movieXiangNews);
                    }
                });
    }
    //影片评论
    @SuppressLint("CheckResult")
    public void yingYuanPinglunNewsObservable(Map<String,String> map){
        Observable<YingYuanPinglunNews> yingYuanPinglunNewsObservable = mModel.requestYingyuanPinglun(map);
        yingYuanPinglunNewsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YingYuanPinglunNews>() {
                    @Override
                    public void accept(YingYuanPinglunNews yingYuanPinglunNews) throws Exception {
                        mRootView.YingyuanPinglunData(yingYuanPinglunNews);
                    }
                });
    }

}
