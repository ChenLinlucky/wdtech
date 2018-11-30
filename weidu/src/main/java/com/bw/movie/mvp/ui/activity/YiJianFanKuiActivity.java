package com.bw.movie.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.YiJianFanKuiBean;
import com.bw.movie.di.component.DaggerYiJianFanKuiComponent;
import com.bw.movie.di.module.YiJianFanKuiModule;
import com.bw.movie.mvp.contract.YiJianFanKuiContract;
import com.bw.movie.mvp.presenter.YiJianFanKuiPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class YiJianFanKuiActivity extends BaseActivity<YiJianFanKuiPresenter> implements YiJianFanKuiContract.View {

    @BindView(R.id.edit_yijian)
    EditText editYijian;
    @BindView(R.id.btn_tijiao)
    Button btnTijiao;
    private SharedPreferences config;
    private String s;
    private HashMap<String, String> map;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerYiJianFanKuiComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .yiJianFanKuiModule(new YiJianFanKuiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_yi_jian_fan_kui; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
    public void data(YiJianFanKuiBean yiJianFanKuiBean) {

        if (config.getBoolean("isLogin",false)){
            Toast.makeText(this, yiJianFanKuiBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_tijiao)
    public void onViewClicked() {

        map = new HashMap<>();
        config = getSharedPreferences("config", Context.MODE_PRIVATE);
        int userId = config.getInt("userId", 0);
        map.put("userId", userId+"");
        map.put("sessionId", config.getString("sessionId", ""));
        s = editYijian.getText().toString();
        mPresenter.FanKui(map, s);
    }
}
