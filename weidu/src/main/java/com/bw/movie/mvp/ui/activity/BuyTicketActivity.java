package com.bw.movie.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.service.MyApp;
import com.bw.movie.bean.XiaDanNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.wxBean;
import com.bw.movie.di.component.DaggerBuyTicketComponent;
import com.bw.movie.di.module.BuyTicketModule;
import com.bw.movie.mvp.contract.BuyTicketContract;
import com.bw.movie.mvp.presenter.BuyTicketPresenter;
import com.bw.movie.mvp.ui.SeatTable;
import com.bw.movie.mvp.ui.adapter.TimeAdapter;
import com.bw.movie.zhifu.MyALipayUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.tencent.mm.sdk.modelpay.PayReq;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class BuyTicketActivity extends BaseActivity<BuyTicketPresenter> implements BuyTicketContract.View {

    @BindView(R.id.yingyuan_simp)
    SimpleDraweeView yingyuanSimp;
    @BindView(R.id.bigname)
    TextView bigname;
    @BindView(R.id.sname)
    TextView sname;
    @BindView(R.id.lin_address)
    LinearLayout linAddress;
    @BindView(R.id.movieName)
    TextView movieName;
    @BindView(R.id.SeatTable)
    com.bw.movie.mvp.ui.SeatTable SeatTable;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.recy_time)
    RecyclerView recyTime;

    private String address;
    private String name;
    private String logo;
    private String moviename;
    private int id;
    private RadioButton zhifubao;
    private RadioButton weixin;
    private SharedPreferences config;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBuyTicketComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .buyTicketModule(new BuyTicketModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_buy_ticket; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        //关注影院排期
        HashMap<String, String> map = new HashMap<>();
        map.put("cinemasId", "2");
        map.put("movieId", "3");
        mPresenter.yingyuanPaiqiObservable(map);


        Intent intent = getIntent();
        //地址信息
        address = intent.getStringExtra("address");
        name = intent.getStringExtra("name");
        logo = intent.getStringExtra("logo");
        bigname.setText(name);
        sname.setText(address);
        yingyuanSimp.setImageURI(Uri.parse(logo));
        //影片名称
        moviename = intent.getStringExtra("moviename");
        movieName.setText(moviename + "(  国语3D  )");
        id = intent.getIntExtra("id",1);

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


        //选座
        SeatTable.setMaxSelected(5);//设置最多选中
        SeatTable.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        SeatTable.setData(7, 9);
        BuyTicketPopwindow();
    }


    //结算---popwindow
    private void BuyTicketPopwindow() {
        btnBuy.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.buy_ticket_popwindow, null);
                PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setFocusable(true);
                ColorDrawable dw = new ColorDrawable(0xffffffff);
                window.setBackgroundDrawable(dw);
                window.setAnimationStyle(R.style.PopupAnimation);
                window.showAtLocation(BuyTicketActivity.this.findViewById(R.id.btn_buy), Gravity.BOTTOM, 0, 0);
                window.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                weixin = view.findViewById(R.id.radio_weixin);
                zhifubao = view.findViewById(R.id.radio_zhifubao);
                Button zhifu = view.findViewById(R.id.btn_zhifu);
                config = getSharedPreferences("config", Context.MODE_PRIVATE);
                zhifu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap<>();
                        int userId = config.getInt("userId", 1);
                        map.put("userId",config.getInt("userId",1)+"");
                        map.put("sessionId",config.getString("sessionId",""));
                        HashMap<String, String> map1 = new HashMap<>();
                        String s = MD5( userId+"" + id + "" + 1 + "" + "movie");
                        map1.put("scheduleId",id+"");
                        map1.put("amount","1");
                        map1.put("sign",s);
                        mPresenter.PaiQi(map,map1);
                    }
                });
            }

        });

    }

    @Override
    public void YingyuanPaiqidata(YingyuanPaiqi yingyuanPaiqi) {
        List<YingyuanPaiqi.ResultBean> result = yingyuanPaiqi.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(BuyTicketActivity.this, LinearLayoutManager.VERTICAL, false);
        recyTime.setLayoutManager(manager);
        TimeAdapter adapter = new TimeAdapter(BuyTicketActivity.this,result);
        recyTime.setAdapter(adapter);
    }


    //支付
    @Override
    public void XiaDan(XiaDanNews xiaDanNews) {
        //订单号
        String orderId = xiaDanNews.getOrderId();
        HashMap<String, String> map = new HashMap<>();
        if (weixin.isChecked()){
            map.put("userId",config.getInt("userId",1)+"");
            map.put("sessionId",config.getString("sessionId",""));
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("orderId",orderId);
            map1.put("payType","1");
            mPresenter.weixin(map,map1);

        }else {
            //支付宝
            map.put("userId",config.getInt("userId",1)+"");
            map.put("sessionId",config.getString("sessionId",""));
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("orderId",orderId);
            map1.put("payType","2");
            mPresenter.zhifubao(map,map1);
        }
    }

    //支付宝****
    @Override
    public void zhifubao(String s) {
        MyALipayUtils.ALiPayBuilder aLiPayBuilder = new MyALipayUtils.ALiPayBuilder();
        aLiPayBuilder.build().toALiPay(this,s.toString());
    }
    //微信****
    @Override
    public void weixin(wxBean wxBean) {
        PayReq req = new PayReq();
        req.appId = wxBean.getAppId();
        req.partnerId = wxBean.getPartnerId();
        req.prepayId = wxBean.getPrepayId();
        req.nonceStr = wxBean.getNonceStr();
        req.timeStamp = wxBean.getTimeStamp();
        req.packageValue = wxBean.getPackageValue();
        req.sign = wxBean.getSign();
// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//3.调用微信支付sdk支付方法
// 将该app注册到微信
        MyApp.mWxApi.registerApp("wxb3852e6a6b7d9516");
        MyApp.mWxApi.sendReq(req);

    }

    /**
     *  MD5加密
     * @param sourceStr
     * @return
     */
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

}
