package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.RegisterBean;
import com.bw.movie.mvp.contract.ReginContract;
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

import java.util.Map;


@ActivityScope
public class ReginPresenter extends BasePresenter<ReginContract.Model, ReginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ReginPresenter(ReginContract.Model model, ReginContract.View rootView) {
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
    public void registerBeanObservable(Map<String,String> map){
        Observable<RegisterBean> requestregister = mModel.requestregister(map);
        requestregister.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        mRootView.data(registerBean);
                    }
                });
    }
}
