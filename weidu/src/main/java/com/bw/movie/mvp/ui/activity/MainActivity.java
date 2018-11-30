package com.bw.movie.mvp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.di.component.DaggerMainComponent;
import com.bw.movie.di.module.MainModule;
import com.bw.movie.mvp.adapter.YingdaoAdapter;
import com.bw.movie.mvp.contract.MainContract;
import com.bw.movie.mvp.presenter.MainPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.btn_tiyan)
    Button btnTiyan;
    private int[] image = new int[]{R.drawable.ic111, R.drawable.ic2222, R.drawable.ic3333, R.drawable.ic4444};
    private SharedPreferences sp;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        viewpage.setAdapter(new YingdaoAdapter(MainActivity.this, image));
        viewpage.setPageMargin(20);
        // viewpage.setOffscreenPageLimit(image.length);


        //首次展示引导页，第二次直接进入主页面
        sp = getSharedPreferences("WelCome", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if(isFirst==true){
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("isFirst",false);
            edit.commit();
        }else{
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }

        //默认隐藏
        btnTiyan.setVisibility(View.GONE);
        //滑动事件处理-------立即体验
        viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (image.length - 1 == position) {
                    btnTiyan.setVisibility(View.VISIBLE);
                }else{
                    btnTiyan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnTiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
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
