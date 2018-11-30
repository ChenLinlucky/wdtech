package com.bw.movie.mvp.ui.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.utils.EncryptUtil;
import com.bw.movie.bean.ChangePasswordBean;
import com.bw.movie.di.component.DaggerChangePasswordComponent;
import com.bw.movie.di.module.ChangePasswordModule;
import com.bw.movie.mvp.contract.ChangePasswordContract;
import com.bw.movie.mvp.presenter.ChangePasswordPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenter> implements ChangePasswordContract.View {

    @BindView(R.id.oldPwd)
    EditText oldPwd;
    @BindView(R.id.newPwd)
    EditText newPwd;
    @BindView(R.id.newPwd2)
    EditText newPwd2;
    @BindView(R.id.btn_confrimPassword)
    Button btnConfrimPassword;
    private String s;
    private String s1;
    private String s2;
    private String message;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerChangePasswordComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .changePasswordModule(new ChangePasswordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_change_password; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        SharedPreferences config = ChangePasswordActivity.this.getSharedPreferences("config", MODE_PRIVATE);
        boolean isLogin = config.getBoolean("isLogin", true);
        if (isLogin){
            String sessionId = config.getString("sessionId", "");
            int userId = config.getInt("userId", 0);
            //点击确定修改密码--？？？？
            btnConfrimPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId",userId+"");
                    hashMap.put("sessionId",sessionId);
                    HashMap<String, String> hashMap1 = new HashMap<>();
                    hashMap1.put("oldPwd",EncryptUtil.encrypt(oldPwd.getText().toString().trim()));
                    hashMap1.put("newPwd",EncryptUtil.encrypt(newPwd.getText().toString().trim()));
                    hashMap1.put("newPwd2",EncryptUtil.encrypt(newPwd2.getText().toString().trim()));
               /*     s = oldPwd.getText().toString();
                    s1 = newPwd.getText().toString();
                    s2 = newPwd2.getText().toString();
                    String encrypt = EncryptUtil.encrypt(s);
                    String encrypt1 = EncryptUtil.encrypt(s1);
                    String encrypt2 = EncryptUtil.encrypt(s2);*/
                    mPresenter.changePasswordBeanObservable(hashMap,hashMap1);
                }
            });
        }else {
            Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
        }


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


    @Override
    public void data(ChangePasswordBean changePasswordBean) {
        String message = changePasswordBean.getMessage();
        Toast.makeText(this, message+"", Toast.LENGTH_SHORT).show();
        finish();
    }
}
