package com.bw.movie.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.PinglunNews;
import com.bw.movie.bean.XiangqingNews;
import com.bw.movie.di.component.DaggerxiangqingComponent;
import com.bw.movie.di.module.xiangqingModule;
import com.bw.movie.mvp.contract.xiangqingContract;
import com.bw.movie.mvp.presenter.xiangqingPresenter;
import com.bw.movie.mvp.ui.adapter.YingpingAdapter;
import com.bw.movie.mvp.ui.adapter.juzhaoAdapter;
import com.bw.movie.mvp.ui.adapter.yugaoAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class xiangqingActivity extends BaseActivity<xiangqingPresenter> implements xiangqingContract.View {

    @BindView(R.id.xiangqing_simp)
    SimpleDraweeView xiangqingSimp;
    @BindView(R.id.btn_xiangqing)
    Button btnXiangqing;
    @BindView(R.id.btn_yugao)
    Button btnYugao;
    @BindView(R.id.btn_juzhao)
    Button btnJuzhao;
    @BindView(R.id.btn_yingping)
    Button btnYingping;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.xiangqing_name)
    TextView xiangqingName;

    private SharedPreferences sp;
    private int uid;
    private XiangqingNews.ResultBean result;
    private List<PinglunNews.ResultBean> result1;
    private int id;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerxiangqingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .xiangqingModule(new xiangqingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_xiangqing; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 12);
        sp = getSharedPreferences("flag", MODE_PRIVATE);
        uid = sp.getInt("uid", 1);
        mPresenter.xiangqing(id);

        Intent intentping = getIntent();
        String id1 = intentping.getStringExtra("id");
        HashMap<String, String> map = new HashMap<>();
        map.put("movieId", String.valueOf(id));
        map.put("page", "1");
        map.put("count", "5");
        mPresenter.pinglun(map);

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
    public void data(XiangqingNews xiangqingNews) {
        result = xiangqingNews.getResult();
        String[] split = result.getImageUrl().split("\\|");
        Uri uri = Uri.parse(split[0]);
        xiangqingSimp.setImageURI(uri);
        xiangqingName.setText(result.getName());
    }

    //评论
    @Override
    public void datapinglun(PinglunNews pinglunNews) {
        result1 = pinglunNews.getResult();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_xiangqing, R.id.btn_yugao, R.id.btn_juzhao, R.id.btn_yingping,R.id.img_back, R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_xiangqing:
                showPopwindow();
                break;
            case R.id.btn_yugao:
                yugaoPopwindow();
                break;
            case R.id.btn_juzhao:
                juzhaoPopwindow();
                break;
            case R.id.btn_yingping:
                yingpingPopwindow();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_buy:
                Intent intent = new Intent(xiangqingActivity.this, BuyTicketActivity.class);
                startActivity(intent);
                break;
        }
    }

    //影评
    private void yingpingPopwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.yingping_popwindow, null);
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(xiangqingActivity.this.findViewById(R.id.btn_xiangqing),
                Gravity.BOTTOM, 0, 0);

        RecyclerView yingping_recyView = view.findViewById(R.id.yingping_recyView);
        LinearLayoutManager manager = new LinearLayoutManager(xiangqingActivity.this, LinearLayoutManager.VERTICAL, false);
        yingping_recyView.setLayoutManager(manager);
        //Toast.makeText(this, "result1:" + result1, Toast.LENGTH_SHORT).show();
        YingpingAdapter adapter = new YingpingAdapter(xiangqingActivity.this, result1);
        yingping_recyView.setAdapter(adapter);
    }

    //剧照
    private void juzhaoPopwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.juzhao_popwindow, null);
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(xiangqingActivity.this.findViewById(R.id.btn_xiangqing),
                Gravity.BOTTOM, 0, 0);

        List<String> posterList = result.getPosterList();
        GridLayoutManager manager = new GridLayoutManager(xiangqingActivity.this, 2, GridLayoutManager.VERTICAL, false);
        RecyclerView recyView = view.findViewById(R.id.juzhao_recyView);
        recyView.setLayoutManager(manager);
        juzhaoAdapter adapter = new juzhaoAdapter(xiangqingActivity.this, posterList);
        recyView.setAdapter(adapter);
    }

    //预告
    private void yugaoPopwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.yugao_popwindow, null);
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(xiangqingActivity.this.findViewById(R.id.btn_xiangqing),
                Gravity.BOTTOM, 0, 0);

        RecyclerView yugaorecyView = view.findViewById(R.id.yugao_recyView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(xiangqingActivity.this, 1, GridLayoutManager.VERTICAL, false);
        yugaorecyView.setLayoutManager(gridLayoutManager);
        ;
        List<XiangqingNews.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
        yugaoAdapter myYuGaoAdapter = new yugaoAdapter(xiangqingActivity.this, shortFilmList);
        yugaorecyView.setAdapter(myYuGaoAdapter);
    }

    //详情
    private void showPopwindow() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_popwindow, null);
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xfffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(xiangqingActivity.this.findViewById(R.id.btn_xiangqing),
                Gravity.BOTTOM, 0, 0);


        TextView mingzi = view.findViewById(R.id.mingzi);
        mingzi.setText(result.getName());
        SimpleDraweeView simp = view.findViewById(R.id.pop_simp);
        String[] split = result.getImageUrl().split("\\|");
        Uri uri = Uri.parse(split[0]);
        simp.setImageURI(uri);
        TextView leixing = view.findViewById(R.id.leixing);
        leixing.setText("类型：" + result.getMovieTypes());
        TextView chandi = view.findViewById(R.id.chandi);
        chandi.setText("产地：" + result.getPlaceOrigin());
        TextView shichang = view.findViewById(R.id.shichang);
        shichang.setText("时常：" + result.getDirector() + "分");
        TextView daoyan = view.findViewById(R.id.daoyan);
        daoyan.setText("导演：:" + result.getDirector());
        TextView jianjie = view.findViewById(R.id.jianjie);
        jianjie.setText("     " + result.getSummary());
        TextView renyuan = view.findViewById(R.id.renyuan);
        renyuan.setText(" 主演：  " + result.getStarring());
    }
}
