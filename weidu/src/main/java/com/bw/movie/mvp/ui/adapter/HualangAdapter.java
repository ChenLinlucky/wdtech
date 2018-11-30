package com.bw.movie.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.movie.bean.HotNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HualangAdapter extends PagerAdapter{
    private List<HotNews.ResultBean> list;
    private Context context;
    public HualangAdapter(List<HotNews.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER);
        simpleDraweeView.setImageURI(Uri.parse(list.get(position).getImageUrl()));
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
