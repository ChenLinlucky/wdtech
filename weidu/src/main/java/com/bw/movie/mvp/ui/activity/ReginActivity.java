package com.bw.movie.mvp.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.utils.EncryptUtil;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.di.component.DaggerReginComponent;
import com.bw.movie.di.module.ReginModule;
import com.bw.movie.mvp.contract.ReginContract;
import com.bw.movie.mvp.presenter.ReginPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ReginActivity extends BaseActivity<ReginPresenter> implements ReginContract.View {

    @BindView(R.id.edit_zhucename)
    EditText editZhucename;
    @BindView(R.id.edit_zhucepwd)
    EditText editZhucepwd;
    @BindView(R.id.edit_zhcuepwd2)
    EditText editZhcuepwd2;
    @BindView(R.id.edit_zhuceemile)
    EditText editZhuceemile;
    @BindView(R.id.edit_zhuceriqi)
    EditText editZhuceriqi;
    @BindView(R.id.btn_boys)
    RadioButton btnBoys;
    @BindView(R.id.btn_girl)
    RadioButton btnGirl;
    @BindView(R.id.radio_zhuce)
    RadioGroup radioZhuce;
    @BindView(R.id.edit_zhucephone)
    EditText editZhucephone;
    private String pwd;
    private int setNum;
    private String phone1;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerReginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .reginModule(new ReginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_regin; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        radioZhuce.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_boys:
                        setNum=1;
                        break;
                    case R.id.btn_girl:
                        setNum=2;
                        break;

                }
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
    public void data(RegisterBean registerBean) {
        String message1 = registerBean.getMessage();
        Toast.makeText(this, message1, Toast.LENGTH_SHORT).show();
        String status = registerBean.getStatus();
        String message = registerBean.getMessage();
        if (status.equalsIgnoreCase("0000")) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("name", phone1);
            intent.putExtras(bundle);
            setResult(1, intent);
            finish();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_zhuce)
    public void onViewClicked() {
        HashMap<String, String> map = new HashMap<>();
        String name = editZhucename.getText().toString();
        phone1 = editZhucephone.getText().toString();
        pwd = editZhucepwd.getText().toString();
        String pwd2 = editZhcuepwd2.getText().toString();
        String emile = editZhuceemile.getText().toString();
        String riqi = editZhuceriqi.getText().toString();


        if (!TextUtils.isEmpty(name) && name.trim() != "") {
            if (!TextUtils.isEmpty(this.phone1) && this.phone1.length() == 11 && this.phone1.trim() != "") {
                if (pwd.trim() != "" && !TextUtils.isEmpty(pwd)) {
                    if (!isMatcherFinded("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,18}$", pwd)) {
                        if (TextUtils.isEmpty(pwd) && pwd.trim() == "") {
                            Toast.makeText(ReginActivity.this, "输入的密码不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ReginActivity.this, "密码格式不对,必须密码(6-18个字母、字符、数字、相结合)", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (pwd.equals(pwd2)) {
                            if (!isMatcherFinded("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$", emile)) {
                                if (TextUtils.isEmpty(emile) && emile.trim() == "") {
                                    Toast.makeText(ReginActivity.this, "输入的邮箱不能为空", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ReginActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                                }
                            } else {

                                pwd = EncryptUtil.encrypt(pwd);
                                String encrypt1 = EncryptUtil.encrypt(pwd2);
                                map.put("nickName", name);
                                map.put("phone", this.phone1);
                                map.put("pwd", pwd);
                                map.put("pwd2", encrypt1);
                                map.put("birthday", riqi);
                                map.put("sex", setNum + "");
                                map.put("email", emile);
                                mPresenter.registerBeanObservable(map);
                            }
                        } else {
                            if (TextUtils.isEmpty(pwd2) && pwd2.trim() == "") {
                                Toast.makeText(ReginActivity.this, "输入的确认密码不能为空", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ReginActivity.this, "两次密码不同", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {
                    Toast.makeText(ReginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (TextUtils.isEmpty(this.phone1) && this.phone1.trim() == "") {
                    Toast.makeText(ReginActivity.this, "输入的账号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReginActivity.this, "证号格式不对", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(ReginActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
        }
    }
    //正则表达式
    public static boolean isMatcherFinded(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    @OnClick({R.id.edit_zhuceemile, R.id.edit_zhuceriqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_zhuceemile:
                break;
            case R.id.edit_zhuceriqi:
                new DatePickerDialog(ReginActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editZhuceriqi.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2000, 1, 2).show();
                break;
        }
    }
}
