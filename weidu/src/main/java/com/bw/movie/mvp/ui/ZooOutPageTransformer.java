package com.bw.movie.mvp.ui;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ZooOutPageTransformer implements ViewPager.PageTransformer{
    //自由控制缩放比例
    private static final float MAX_SCALE = 1f;
    private static final float MIN_SCALE = 0.2f;//0.85f
    @Override
    public void transformPage(@NonNull View page, float position) {
        if(position<=1){
            float scaleFactor = MIN_SCALE +(1 - Math.abs(position)*(MAX_SCALE-MIN_SCALE));
            page.setScaleX(scaleFactor);

            if (position>0){
                page.setTranslationX(-scaleFactor*2);
            }else if(position<0){
                page.setTranslationX(scaleFactor*2);
            }

            page.setScaleY(scaleFactor);
        }else{
            page.setScaleY(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
