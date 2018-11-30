package com.bw.movie.mvp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.service.MyApp;
import com.bw.movie.app.utils.EncryptUtil;
import com.bw.movie.bean.EventCode;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.WeiXinLoginBean;
import com.bw.movie.di.component.DaggerLoginComponent;
import com.bw.movie.di.module.LoginModule;
import com.bw.movie.mvp.contract.LoginContract;
import com.bw.movie.mvp.presenter.LoginPresenter;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.BuildConfig;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.img_eye)
    ImageView imgEye;
    @BindView(R.id.img_weixinLogin)
    ImageView imgWeixinLogin;
    private SharedPreferences config;
    private SharedPreferences.Editor edit;
    private String phone;
    private String pwd;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        regToWx();

        if (!EventBus.getDefault().isRegistered(this) || EventBus.getDefault() == null) {
            EventBus.getDefault().register(this);
        }
    }

    private void regToWx() {
        MyApp.mWxApi= WXAPIFactory.createWXAPI(LoginActivity.this, MyApp.appid,true);
        MyApp.mWxApi.registerApp(MyApp.appid);
    }


    @Override
    public void data(LoginBean loginBean) {
        LoginBean.ResultBean result = loginBean.getResult();
        String sessionId = result.getSessionId();
        int userId = result.getUserId();
        LoginBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();
        long birthday = userInfo.getBirthday();
        int id = userInfo.getId();
        long lastLoginTime = userInfo.getLastLoginTime();
        String nickName = userInfo.getNickName();
        int sex = userInfo.getSex();
        String headPic = userInfo.getHeadPic();

        if (TextUtils.equals(loginBean.getStatus(), "0000")) {
            //获取对象
            config = getSharedPreferences("config", MODE_PRIVATE);
            edit = config.edit();
            edit.putString("sessionId", sessionId);
            edit.putInt("userId", userId);
            edit.putLong("birthday", birthday);
            edit.putInt("id", id);
            edit.putLong("lastLoginTime", lastLoginTime);
            edit.putString("nickName", nickName);
            edit.putString("phone","18811499641");
            edit.putInt("sex", sex);
            edit.putString("headPic", headPic);
            edit.putBoolean("denglu", true);
            edit.putBoolean("isLogin", true);
            edit.commit();
            Log.d("LoginActivity", sessionId);
           // Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
          //  Toast.makeText(this, "result:" + result.toString(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void WeChatData(WeiXinLoginBean weiXinLoginBean) {
        WeiXinLoginBean.ResultBean result = weiXinLoginBean.getResult();
        String sessionId = result.getSessionId();
        int userId = result.getUserId();
        WeiXinLoginBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();
        long birthday = userInfo.getBirthday();
        int id = userInfo.getId();
        long lastLoginTime = userInfo.getLastLoginTime();
        String nickName = userInfo.getNickName();
        int sex = userInfo.getSex();
        String headPic = userInfo.getHeadPic();

        if (TextUtils.equals(weiXinLoginBean.getStatus(), "0000")) {
            //获取对象
            config = getSharedPreferences("config", MODE_PRIVATE);
            edit = config.edit();
            edit.putString("sessionId", sessionId);
            edit.putInt("userId", userId);
            edit.putLong("birthday", birthday);
            edit.putInt("id", id);
            edit.putLong("lastLoginTime", lastLoginTime);
            edit.putString("nickName", nickName);
            edit.putInt("sex", sex);
            edit.putString("phone","18811499641");
            edit.putString("headPic", headPic);
            edit.putBoolean("denglu", true);
            edit.putBoolean("isLogin", true);
            edit.commit();
            Log.d("LoginActivity", sessionId);
            Toast.makeText(this, weiXinLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "result:" + result.toString(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, weiXinLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == 1) {
            String phone = data.getExtras().getString("phone");
            editPhone.setText(phone);
        }
    }

    @OnClick({R.id.img_eye, R.id.text_login, R.id.btn_login,R.id.img_weixinLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_eye:
                break;
            case R.id.text_login:
                Intent intent = new Intent(LoginActivity.this, ReginActivity.class);
                startActivityForResult(intent, 999);
                break;
            case R.id.btn_login:
                HashMap<String, String> map = new HashMap<>();
                phone = editPhone.getText().toString();
                pwd = editPwd.getText().toString();
                if (!TextUtils.isEmpty(phone) && phone.trim() != "" && !TextUtils.isEmpty(pwd) && pwd.trim() != "") {
                    //加密
                    if (pwd.length() >= 6) {
                        String encrypt = EncryptUtil.encrypt(pwd);
                        map.put("phone", phone);
                        map.put("pwd", encrypt);

                        if (BuildConfig.DEBUG) Log.d("Login encrypt =======", encrypt+"");
                        mPresenter.loginBeanObservable(map);

                        //解密
                        String decrypt = EncryptUtil.decrypt(encrypt);
                    } else {
                        Toast.makeText(this, "密码长度大于6位", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_weixinLogin:
                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wxb3852e6a6b7d9516";
                //向微信发送请求
                MyApp.mWxApi.sendReq(req);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getcode(EventCode eventCode){
        String code = eventCode.getCode();
        Toast.makeText(this, "code!!!!!!"+code, Toast.LENGTH_SHORT).show();
        mPresenter.weiXinLoginBeanObservable(code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}