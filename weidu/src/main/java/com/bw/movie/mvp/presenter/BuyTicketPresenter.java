package com.bw.movie.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.bw.movie.bean.XiaDanNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.wxBean;
import com.bw.movie.mvp.contract.BuyTicketContract;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;


@ActivityScope
public class BuyTicketPresenter extends BasePresenter<BuyTicketContract.Model, BuyTicketContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BuyTicketPresenter(BuyTicketContract.Model model, BuyTicketContract.View rootView) {
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
    //影院排期列表
    @SuppressLint("CheckResult")
    public void yingyuanPaiqiObservable(Map<String,String> map){
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

    //下单
    public void PaiQi(HashMap<String, String> map, HashMap<String, String> map1) {
        Observable<XiaDanNews> xiaDanNewsObservable = mModel.requestXiaDan(map, map1);
        xiaDanNewsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XiaDanNews>() {
                    @Override
                    public void accept(XiaDanNews xiaDanNews) throws Exception {
                        mRootView.XiaDan(xiaDanNews);
                    }
                });
    }

    public void zhifubao(HashMap<String, String> map, HashMap<String, String> map1) {
        Observable<ResponseBody> requestzhifubao = mModel.requestzhifubao(map, map1);
        requestzhifubao.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        mRootView.zhifubao(s);
                    }
                });
    }

    public void weixin(HashMap<String, String> map, HashMap<String, String> map1) {
        Observable<wxBean> wxBeanObservable = mModel.requestWinxin(map, map1);
        wxBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<wxBean>() {
                    @Override
                    public void accept(wxBean wxBean) throws Exception {
                        mRootView.weixin(wxBean);
                    }
                });
    }
}
