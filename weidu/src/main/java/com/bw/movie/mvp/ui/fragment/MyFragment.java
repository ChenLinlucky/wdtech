package com.bw.movie.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.di.component.DaggerMyComponent;
import com.bw.movie.di.module.MyModule;
import com.bw.movie.mvp.contract.MyContract;
import com.bw.movie.mvp.presenter.MyPresenter;
import com.bw.movie.mvp.ui.activity.LoginActivity;
import com.bw.movie.mvp.ui.activity.MyMessageActivity;
import com.bw.movie.mvp.ui.activity.YiJianFanKuiActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {


    @BindView(R.id.xinxi)
    ImageView xinxi;
    @BindView(R.id.guanzhu)
    LinearLayout guanzhu;
    @BindView(R.id.bugPiaojilu)
    LinearLayout bugPiaojilu;
    Unbinder unbinder;
    @BindView(R.id.text_denglu)
    TextView textDenglu;
    @BindView(R.id.simp_denglu)
    SimpleDraweeView simpDenglu;
    @BindView(R.id.YiJianFanKui)
    ImageView YiJianFanKui;

    private SharedPreferences sharedPreferences;
    private boolean denglu;
    private boolean isLogin;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMyComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .myModule(new MyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.simp_denglu, R.id.xinxi, R.id.guanzhu, R.id.bugPiaojilu,R.id.YiJianFanKui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.simp_denglu:
                if (!sharedPreferences.getBoolean("isLogin", false)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "你已经登录，不能重复登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.xinxi:
                isLogin = sharedPreferences.getBoolean("isLogin", false);
                if (isLogin == true) {
                    Intent intent1 = new Intent(getActivity(), MyMessageActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanzhu:
                break;
            case R.id.bugPiaojilu:
                break;
            case R.id.YiJianFanKui:
                Intent intent = new Intent(getActivity(), YiJianFanKuiActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        denglu = sharedPreferences.getBoolean("denglu", false);
        if (denglu == true) {
            String headPic = sharedPreferences.getString("headPic", "");
            String nickName = sharedPreferences.getString("nickName", "");
            textDenglu.setText(nickName);
            Uri parse = Uri.parse(headPic);
            simpDenglu.setImageURI(parse);
        }

    }


}
