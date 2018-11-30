package com.bw.movie.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.MovieXiangNews;
import com.bw.movie.bean.PaiXiangNews;
import com.bw.movie.bean.YingYuanPinglunNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.YingyuanXiangNews;
import com.bw.movie.di.component.DaggerMovieXiangqingComponent;
import com.bw.movie.di.module.MovieXiangqingModule;
import com.bw.movie.mvp.contract.MovieXiangqingContract;
import com.bw.movie.mvp.presenter.MovieXiangqingPresenter;
import com.bw.movie.mvp.ui.XiangcengdieAdapter;
import com.bw.movie.mvp.ui.adapter.XiangPinglunAdapter;
import com.bw.movie.mvp.ui.adapter.YingyuanPaiqiAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MovieXiangqingActivity extends BaseActivity<MovieXiangqingPresenter> implements MovieXiangqingContract.View {

    @BindView(R.id.yingyuan_recyFlow)
    RecyclerCoverFlow yingyuanRecyFlow;
    @BindView(R.id.yingyuan_name)
    TextView yingyuanName;
    @BindView(R.id.yyname)
    TextView yyname;
    @BindView(R.id.yingyuan_simp)
    SimpleDraweeView yingyuanSimp;
    @BindView(R.id.yingyuan_recyView)
    RecyclerView yingyuanRecyView;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.lin_address)
    LinearLayout linAddress;
    private SharedPreferences sp;
    private List<YingyuanPaiqi.ResultBean> result;
    private PaiXiangNews.ResultBean xiangResult;
    private List<YingYuanPinglunNews.ResultBean> pinglunResult;
    private YingyuanXiangNews.ResultBean addressresult;
    private String cengdieName;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMovieXiangqingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .movieXiangqingModule(new MovieXiangqingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_movie_xiangqing; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //查看影院详情
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 12);
       // sp = getSharedPreferences("flag", MODE_PRIVATE);
     //   sp.getInt("id", 12);
        mPresenter.yingyuanxiangqing(id);


        //关注影院排期
        HashMap<String, String> map = new HashMap<>();
        map.put("cinemasId", "2");
        map.put("movieId", "3");
        mPresenter.yingyuanPaiqiObservable(map);

        //排期——Popwindow详情
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("cinemaId","10");
        mPresenter.paiXiangNewsObservable(map1);

        //根据影院Id进入详情页面
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("cinemaId","1");
        mPresenter.movieXiangNewsObservable(map2);
        //popwindow----评论
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("cinemaId","1");
        map3.put("page","1");
        map3.put("count","10");
        mPresenter.yingYuanPinglunNewsObservable(map3);

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




//影院详情地址信息
    @Override
         public void data(YingyuanXiangNews yingyuanXiangNews) {
            addressresult = yingyuanXiangNews.getResult();
            yingyuanName.setText(addressresult.getName());
            yyname.setText(addressresult.getAddress());
            String[] split = addressresult.getLogo().split("\\|");
            Uri parse = Uri.parse(split[0]);
            yingyuanSimp.setImageURI(parse);
    }


    //影院排期列表
    @Override
    public void YingyuanPaiqidata(YingyuanPaiqi yingyuanPaiqi) {
    /*  List<YingyuanPaiqi.ResultBean> result = yingyuanPaiqi.getResult();
        Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();
        YingyuanPaiqiAdapter adapter = new YingyuanPaiqiAdapter(MovieXiangqingActivity.this, result);
        yingyuanRecyView.setAdapter(adapter);*/
        //滑动
        yingyuanRecyFlow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            private int id;
            @Override
            public void onItemSelected(int position) {
                id = MovieXiangqingActivity.this.result.get(position).getId();
                LinearLayoutManager manager = new LinearLayoutManager(MovieXiangqingActivity.this, LinearLayoutManager.VERTICAL, false);
                yingyuanRecyView.setLayoutManager(manager);
                YingyuanPaiqiAdapter adapter = new YingyuanPaiqiAdapter(MovieXiangqingActivity.this, result);
                yingyuanRecyView.setAdapter(adapter);
            }
        });

        result = yingyuanPaiqi.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        yingyuanRecyView.setLayoutManager(manager);
        YingyuanPaiqiAdapter adapter = new YingyuanPaiqiAdapter(this, result);
        yingyuanRecyView.setAdapter(adapter);
        adapter.setOnitemclick(new YingyuanPaiqiAdapter.onitemclick() {
            @Override
            public void onclick(int position) {
                int id1 = MovieXiangqingActivity.this.result.get(position).getId();
                //影院地址
                 Intent intent = new Intent(MovieXiangqingActivity.this, BuyTicketActivity.class);
                 intent.putExtra("address",addressresult.getAddress());
                 intent.putExtra("name",addressresult.getName());
                 intent.putExtra("logo",addressresult.getLogo());
                 intent.putExtra("id",result.get(position).getId());

                //影片名称
                intent.putExtra("moviename",cengdieName);
                startActivity(intent);
            }
        });

    }

    //Popwindow----详情
    @Override
    public void PaiXiangqinigData(PaiXiangNews paiXiangNews) {
        xiangResult = paiXiangNews.getResult();
    }

    //根据影院Id进入详情页面
    @Override
    public void MovieXiangData(MovieXiangNews movieXiangNews) {
        List<MovieXiangNews.ResultBean> result = movieXiangNews.getResult();
        ArrayList<String> list = new ArrayList<>();
        //层叠
        for (MovieXiangNews.ResultBean resultBean : result) {
            String imageUrl = resultBean.getImageUrl();
            list.add(imageUrl);
            cengdieName = resultBean.getName();
        }
        XiangcengdieAdapter adapter = new XiangcengdieAdapter(MovieXiangqingActivity.this, result);
        yingyuanRecyFlow.setAdapter(adapter);
        adapter.setOnitemclick(new XiangcengdieAdapter.onitemclick() {
            @Override
            public void onclick(int position) {
                XiangAndPingPopwindow();
            }
        });





    }

    //popwindow---评论
    @Override
    public void YingyuanPinglunData(YingYuanPinglunNews yingYuanPinglunNews) {
        pinglunResult = yingYuanPinglunNews.getResult();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.yingyuan_recyView, R.id.img_back,R.id.lin_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yingyuan_recyView:
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.lin_address:
                Intent intent = new Intent(MovieXiangqingActivity.this, MapActivity.class);
                startActivity(intent);
                break;

        }
    }


    //详情+评论Popwindow
    private void XiangAndPingPopwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.xiangandpinglun_popwindow, null);
        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(this.findViewById(R.id.yingyuan_recyFlow), Gravity.BOTTOM, 0, 0);
        Button btn_1 = view.findViewById(R.id.btn_1);
        Button btn_2 = view.findViewById(R.id.btn_2);
        TextView address = view.findViewById(R.id.xq_address);
        TextView phone = view.findViewById(R.id.xq_phone);
        TextView line = view.findViewById(R.id.xq_line);
        RecyclerView xq_recyView = view.findViewById(R.id.xq_recyView);
        LinearLayout lin_xq = view.findViewById(R.id.lin_xq);
        lin_xq.setVisibility(view.VISIBLE);
        xq_recyView.setVisibility(view.GONE);
        address.setText(addressresult.getAddress());
        phone.setText(addressresult.getPhone());
        line.setText("   乘车路线："+addressresult.getVehicleRoute());
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin_xq.setVisibility(view.VISIBLE);
                xq_recyView.setVisibility(view.GONE);
                address.setText(addressresult.getAddress());
                phone.setText(xiangResult.getPhone());
                line.setText("   乘车路线："+xiangResult.getVehicleRoute());

            }
        });


        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xq_recyView.setVisibility(view.VISIBLE);
                lin_xq.setVisibility(view.GONE);
                LinearLayoutManager manager = new LinearLayoutManager(MovieXiangqingActivity.this, LinearLayoutManager.VERTICAL, false);
                xq_recyView.setLayoutManager(manager);
                XiangPinglunAdapter adapter = new XiangPinglunAdapter(MovieXiangqingActivity.this,pinglunResult);
                xq_recyView.setAdapter(adapter);
            }
        });
    }


}
