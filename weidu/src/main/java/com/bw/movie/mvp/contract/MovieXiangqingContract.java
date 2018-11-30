package com.bw.movie.mvp.contract;

import com.bw.movie.bean.MovieXiangNews;
import com.bw.movie.bean.PaiXiangNews;
import com.bw.movie.bean.XiaDanNews;
import com.bw.movie.bean.YingYuanPinglunNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.YingyuanXiangNews;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;


public interface MovieXiangqingContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void data(YingyuanXiangNews yingyuanXiangNews);
        void YingyuanPaiqidata(YingyuanPaiqi yingyuanPaiqi);
        void PaiXiangqinigData(PaiXiangNews paiXiangNews);//popwindow---详情
        void MovieXiangData(MovieXiangNews movieXiangNews);//根据影院Id点击进入详情
        void YingyuanPinglunData(YingYuanPinglunNews yingYuanPinglunNews);//Popwindow----评论
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<YingyuanXiangNews> requestYingyuanXiang(int cinemaId);//影院地址等信息
        Observable<YingyuanPaiqi> requestYingyuanPaiqi(Map<String,String>map);
        Observable<PaiXiangNews> requestXiangqing(Map<String,String>map);
        Observable<MovieXiangNews> reuqestMovieXiang(Map<String,String>map);
        Observable<YingYuanPinglunNews> requestYingyuanPinglun(Map<String, String> map);
    }
}
