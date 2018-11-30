package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.widget.Toast;

import com.bw.movie.bean.ChangePasswordBean;
import com.bw.movie.mvp.contract.ChangePasswordContract;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;


@ActivityScope
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordContract.Model, ChangePasswordContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ChangePasswordPresenter(ChangePasswordContract.Model model, ChangePasswordContract.View rootView) {
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
    public void changePasswordBeanObservable(HashMap<String, String> hashMap, HashMap<String, String> hashMap1){
        Observable<ChangePasswordBean> observable = mModel.requestChangePassword(hashMap, hashMap1);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ChangePasswordBean>() {
                    @Override
                    public void accept(ChangePasswordBean changePasswordBean) throws Exception {
                             mRootView.data(changePasswordBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mApplication, throwable.getMessage()+"", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
