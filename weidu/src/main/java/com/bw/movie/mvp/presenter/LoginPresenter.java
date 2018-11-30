package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.WeiXinLoginBean;
import com.bw.movie.mvp.contract.LoginContract;
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
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
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
    public void loginBeanObservable(Map<String,String> map){
        Observable<LoginBean> loginBeanObservable = mModel.reuquestLogin(map);
        loginBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        mRootView.data(loginBean);
                    }
                });
    }
    @SuppressLint("CheckResult")
    public void weiXinLoginBeanObservable(String code){
        Observable<WeiXinLoginBean> weiXinLoginBeanObservable = mModel.requesyWechat(code);
        weiXinLoginBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeiXinLoginBean>() {
                    @Override
                    public void accept(WeiXinLoginBean weiXinLoginBean) throws Exception {
                        mRootView.WeChatData(weiXinLoginBean);
                    }
                });
    }
}
