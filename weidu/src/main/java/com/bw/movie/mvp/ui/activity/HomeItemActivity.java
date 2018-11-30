package com.bw.movie.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.di.component.DaggerHomeItemComponent;
import com.bw.movie.di.module.HomeItemModule;
import com.bw.movie.mvp.contract.HomeItemContract;
import com.bw.movie.mvp.presenter.HomeItemPresenter;
import com.bw.movie.mvp.ui.fragment.HotItemFragment;
import com.bw.movie.mvp.ui.fragment.LookingItemFragment;
import com.bw.movie.mvp.ui.fragment.WillItemFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeItemActivity extends BaseActivity<HomeItemPresenter> implements HomeItemContract.View {


    @BindView(R.id.home_item_tab)
    TabLayout homeItemTab;
    @BindView(R.id.home_item_viewpage)
    ViewPager homeItemViewpage;
    @BindView(R.id.img_back)
    ImageView imgBack;
    private ArrayList<String> list;
    private ArrayList<Fragment> list1;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHomeItemComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeItemModule(new HomeItemModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home_item; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        homeItemTab.setTabMode(TabLayout.MODE_FIXED);
        list = new ArrayList<>();
        list.add("热门电影");
        list.add("正在上映");
        list.add("即将上映");
        list1 = new ArrayList<>();
        list1.add(new HotItemFragment());
        list1.add(new LookingItemFragment());
        list1.add(new WillItemFragment());

        homeItemTab.addTab(homeItemTab.newTab().setText(list.get(0)));
        homeItemTab.addTab(homeItemTab.newTab().setText(list.get(1)));
        homeItemTab.addTab(homeItemTab.newTab().setText(list.get(2)));
        MyTabAdapter myTabAdapter = new MyTabAdapter(getSupportFragmentManager());
        homeItemViewpage.setAdapter(myTabAdapter);
        homeItemTab.setupWithViewPager(homeItemViewpage);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


    private class MyTabAdapter extends FragmentPagerAdapter {

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list1.get(position);
        }

        @Override
        public int getCount() {
            return list1.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }

}
