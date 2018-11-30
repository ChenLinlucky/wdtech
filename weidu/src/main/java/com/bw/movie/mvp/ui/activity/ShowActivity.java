package com.bw.movie.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.di.component.DaggerShowComponent;
import com.bw.movie.di.module.ShowModule;
import com.bw.movie.mvp.contract.ShowContract;
import com.bw.movie.mvp.presenter.ShowPresenter;
import com.bw.movie.mvp.ui.fragment.HomeFragment;
import com.bw.movie.mvp.ui.fragment.MovieFragment;
import com.bw.movie.mvp.ui.fragment.MyFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ShowActivity extends BaseActivity<ShowPresenter> implements ShowContract.View {

    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private FragmentManager manager;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerShowComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .showModule(new ShowModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_show; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ArrayList<Fragment> list = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        MovieFragment movieFragment = new MovieFragment();
        MyFragment myFragment = new MyFragment();
        list.add(homeFragment);
        list.add(movieFragment);
        list.add(myFragment);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment,homeFragment).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();

                switch (checkedId){
                    case R.id.rb_1:
                        transaction.replace(R.id.fragment,homeFragment);
                        break;
                    case R.id.rb_2:
                        transaction.replace(R.id.fragment,movieFragment);
                        break;
                    case R.id.rb_3:
                        transaction.replace(R.id.fragment,myFragment);
                        break;

                }
                transaction.commit();
            }
        });
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
}
