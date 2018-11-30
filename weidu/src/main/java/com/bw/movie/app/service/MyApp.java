package com.bw.movie.app.service;



import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.jess.arms.base.BaseApplication;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class MyApp extends BaseApplication{
    private static Context mContext;

    public static IWXAPI mWxApi;

    public static String appid= "wxb3852e6a6b7d9516";
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mContext=getApplicationContext();
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("images")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();

        registerToWX();
    }

    public static Context getContext(){
        return mContext;
    }

    private void registerToWX() {
        mWxApi = WXAPIFactory.createWXAPI(this, appid, false);
        // 将该app注册到微信
        mWxApi.registerApp(appid);
    }
}
