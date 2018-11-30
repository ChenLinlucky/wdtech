package com.bw.movie.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.TouxiangNews;
import com.bw.movie.di.component.DaggerMyMessageComponent;
import com.bw.movie.di.module.MyMessageModule;
import com.bw.movie.mvp.contract.MyMessageContract;
import com.bw.movie.mvp.presenter.MyMessagePresenter;
import com.bw.movie.mvp.ui.TakePictureManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MyMessageActivity extends BaseActivity<MyMessagePresenter> implements MyMessageContract.View {

    @BindView(R.id.set_touxiang)
    RelativeLayout setTouxiang;
    @BindView(R.id.settx_nickname)
    TextView settxNickname;
    @BindView(R.id.settx_sex)
    TextView settxSex;
    @BindView(R.id.set_qianming)
    RelativeLayout setQianming;
    @BindView(R.id.settx_phone)
    TextView settxPhone;
    @BindView(R.id.settx_youxiang)
    TextView settxYouxiang;
    @BindView(R.id.set_birthday)
    TextView setBirthday;
    @BindView(R.id.set_simp_touxiang)
    SimpleDraweeView setSimpTouxiang;
    @BindView(R.id.reset_password)
    TextView resetPassword;
    @BindView(R.id.btn_ESC)
    Button btnESC;
    private String name;
    private SharedPreferences config;
    private boolean isLogin;
    private TakePictureManager takePictureManager;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMyMessageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .myMessageModule(new MyMessageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_my_message; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //取值
        config = getSharedPreferences("config", Context.MODE_PRIVATE);
        String nickName = config.getString("nickName", "");
        settxNickname.setText(nickName);
        int sex = config.getInt("sex", 1);
        if (sex==1){
            settxSex.setText("男");
        }else {
            settxSex.setText("女");
        }
        long birthday = config.getLong("birthday",0);

        //时间转换--时间戳
        Date date = new Date(birthday);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        setBirthday.setText(format1);
        String phone = config.getString("phone", "18811499641");
        settxPhone.setText(phone);
        String headPic = config.getString("headPic", "");
        setSimpTouxiang.setImageURI(headPic);
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
        //修改密码
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = config.getString("password", "");
                Intent intent = new Intent(MyMessageActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.set_simp_touxiang, R.id.settx_nickname,R.id.btn_ESC})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_simp_touxiang:
                TouxiangPopwindow();
                break;
            case R.id.settx_nickname:
                break;
            case R.id.btn_ESC:
                boolean isLogin = config.getBoolean("isLogin", false);
                TextView textDenglu = findViewById(R.id.text_denglu);
                SimpleDraweeView simeDenglu = findViewById(R.id.simp_denglu);
                if(isLogin){
                    SharedPreferences.Editor edit = config.edit();
                    edit.putBoolean("isLogin", false).commit();
                    edit.putString("headPic","");
                    finish();
                }else{
                    textDenglu.setText("登录 / 注册");
                    Uri parse = Uri.parse("drawable/ic_touxiang.png");
                    simeDenglu.setImageURI(parse);
                }

                break;
        }
    }

    private void TouxiangPopwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.touxiang_popwindow, null);
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xfffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(MyMessageActivity.this.findViewById(R.id.set_simp_touxiang),
                Gravity.BOTTOM, 0, 0);

        Button xiangji = view.findViewById(R.id.btn_xiangji);
        Button xiangce = view.findViewById(R.id.btn_xiangce);
        Button cancel = view.findViewById(R.id.btn_cancel);

        //相机
        xiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureManager = new TakePictureManager(MyMessageActivity.this);
                takePictureManager.setTailor(1,1,350,350);//裁剪
                takePictureManager.startTakeWayByCarema();
                takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
                    @Override
                    public void successful(boolean isTailor, File outFile, Uri filePath) {
                        setSimpTouxiang.setImageURI(filePath);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("userId",config.getString("userId",""));
                        map.put("sessionId",config.getString("sessionId",""));
                        Toast.makeText(MyMessageActivity.this, "map:" + map+outFile, Toast.LENGTH_SHORT).show();
                        mPresenter.SettingTouxiang(map,outFile);
                    }

                    @Override
                    public void failed(int errorCode, List<String> deniedPermissions) {

                    }
                });
            }
        });
        //相册
        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureManager = new TakePictureManager(MyMessageActivity.this);
                takePictureManager.setTailor(1,1,350,350);//裁剪
                takePictureManager.startTakeWayByAlbum();
                takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
                    @Override
                    public void successful(boolean isTailor, File outFile, Uri filePath) {
                        setSimpTouxiang.setImageURI(filePath);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("userId",config.getString("userId",""));
                        map.put("sessionId",config.getString("sessionId",""));
                        mPresenter.SettingTouxiang(map,outFile);
                    }

                    @Override
                    public void failed(int errorCode, List<String> deniedPermissions) {

                    }
                });
            }
        });


    }

    //上传头像
    @Override
    public void outTouxiang(TouxiangNews touxiangNews) {

       // Log.d("xxx", "touxiangNews:" + touxiangNews);
        Toast.makeText(this, touxiangNews.getMessage()+"", Toast.LENGTH_SHORT).show();
        Log.d("xxx", touxiangNews.getMessage());
        String headPath = touxiangNews.getHeadPath();
        config = getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor edit = config.edit();
        edit.putString("headPic", headPath);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePictureManager.attachToActivityForResult(requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePictureManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
