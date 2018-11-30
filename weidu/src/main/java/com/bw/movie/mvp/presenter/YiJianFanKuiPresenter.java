package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.YiJianFanKuiBean;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import retrofit2.http.Field;
import retrofit2.http.HeaderMap;

import javax.inject.Inject;

import com.bw.movie.mvp.contract.YiJianFanKuiContract;

import java.util.HashMap;


@ActivityScope
public class YiJianFanKuiPresenter extends BasePresenter<YiJianFanKuiContract.Model, YiJianFanKuiContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public YiJianFanKuiPresenter(YiJianFanKuiContract.Model model, YiJianFanKuiContract.View rootView) {
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

    public void FanKui(@HeaderMap HashMap<String,String> map, @Field("content") String content) {
        Observable<YiJianFanKuiBean> yiJianFanKuiBeanObservable = mModel.FankuiData(map, content);
        yiJianFanKuiBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YiJianFanKuiBean>() {
                    @Override
                    public void accept(YiJianFanKuiBean yiJianFanKuiBean) throws Exception {
                        mRootView.data(yiJianFanKuiBean);
                    }
                });
    }
}
