package com.bw.movie.mvp.model.api.service;

import com.bw.movie.bean.AllYingyuanNews;
import com.bw.movie.bean.ChangePasswordBean;
import com.bw.movie.bean.HotNews;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.LookingNews;
import com.bw.movie.bean.MovieXiangNews;
import com.bw.movie.bean.PaiXiangNews;
import com.bw.movie.bean.PinglunNews;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.TouxiangNews;
import com.bw.movie.bean.TuiYingyuanNews;
import com.bw.movie.bean.WeiXinLoginBean;
import com.bw.movie.bean.WillNews;
import com.bw.movie.bean.XiaDanNews;
import com.bw.movie.bean.XiangqingNews;
import com.bw.movie.bean.YiJianFanKuiBean;
import com.bw.movie.bean.YingYuanPinglunNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.YingyuanXiangNews;
import com.bw.movie.bean.wxBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    //热门电影
    @GET("movieApi/movie/v1/findHotMovieList?page=1&count=10")
    Observable<HotNews> hot();
    //正在上映
    @GET("movieApi/movie/v1/findReleaseMovieList?page=1&count=10")
    Observable<LookingNews> looking();
    //即将上映
    @GET("movieApi/movie/v1/findComingSoonMovieList?page=1&count=10")
    Observable<WillNews> will();
    //详情页面
    @GET("movieApi/movie/v1/findMoviesDetail?")
    Observable<XiangqingNews> xiangqing(@Query("movieId")int moviedId);
    //影片评论
    @GET("movieApi/movie/v1/findAllMovieComment?")
    Observable<PinglunNews> pinglun(@QueryMap HashMap<String, String>map);
    //推荐影院
    @GET("movieApi/cinema/v1/findRecommendCinemas?page=1&count=10")
    Observable<TuiYingyuanNews> tuijian();
    //全部影院
    @GET("movieApi/cinema/v1/findAllCinemas?page=1&count=10")
    Observable<AllYingyuanNews> all();
    //影院详情页面
    @GET("movieApi/cinema/v1/findCinemaInfo?")
    Observable<YingyuanXiangNews> yingyuanxiangqing(@Query("cinemaId")int id);

    //注册
    @FormUrlEncoded
    @POST("movieApi/user/v1/registerUser")
    Observable<RegisterBean> postResponseRegister(@FieldMap Map<String, String> map);
    //登陆
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    Observable<LoginBean> postResponseLogin(@FieldMap Map<String,String> map);
    //根据影院Id获取当前排期的列表
    @GET("movieApi/movie/v1/findMovieListByCinemaId?")
    Observable<MovieXiangNews> movieXiang(@QueryMap Map<String,String> map);
    //影院排期列表
    @GET("movieApi/movie/v1/findMovieScheduleList?")
    Observable<YingyuanPaiqi> YingyuanPaiqi(@QueryMap Map<String,String>map);
    //影院排期详情
    @GET("movieApi/cinema/v1/findCinemaInfo?")
    Observable<PaiXiangNews> PaiXiangqing(@QueryMap Map<String,String> map);
    //影院评论
    @GET("movieApi/cinema/v1/findAllCinemaComment?")
    Observable<YingYuanPinglunNews> YPinglung(@QueryMap Map<String,String> map);
    //个人信息修改
    //修改密码
    @FormUrlEncoded
    @POST("movieApi/user/v1/verify/modifyUserPwd")
    Observable<ChangePasswordBean> ChangePassword(@HeaderMap HashMap<String,String> hashMap,@FieldMap HashMap<String,String> hashMap1);
    //微信登录
    @FormUrlEncoded
    @POST("movieApi/user/v1/weChatBindingLogin")
    Observable<WeiXinLoginBean> WeChat(@Field("code") String code);
    //上传头像
    @Multipart
    @POST("movieApi/user/v1/verify/uploadHeadPic")
    Observable<TouxiangNews> Touxiang(@HeaderMap HashMap<String,String> map, @Part MultipartBody.Part file);
    //购票下单
    @POST("movieApi/movie/v1/verify/buyMovieTicket")
    @FormUrlEncoded
    Observable<XiaDanNews> XiaDan(@HeaderMap HashMap<String,String> map,@FieldMap HashMap<String,String> map1);
    //支付---支付宝
    @POST("movieApi/movie/v1/verify/pay")
    @FormUrlEncoded
    Observable<ResponseBody> zhifubao(@HeaderMap HashMap<String,String> map, @FieldMap HashMap<String,String> map1);
    //支付---微信
    @POST("movieApi/movie/v1/verify/pay")
    @FormUrlEncoded
    Observable<wxBean> weixin(@HeaderMap HashMap<String,String> map, @FieldMap HashMap<String,String> map1);
    //意见反馈
    @POST("movieApi/tool/v1/verify/recordFeedBack")
    @FormUrlEncoded
    Observable<YiJianFanKuiBean> YiJianfankui(@HeaderMap HashMap<String,String> map,@Field("content") String content);

}