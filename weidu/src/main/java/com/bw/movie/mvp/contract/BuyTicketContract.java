package com.bw.movie.mvp.contract;

import com.bw.movie.bean.XiaDanNews;
import com.bw.movie.bean.YingyuanPaiqi;
import com.bw.movie.bean.wxBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


public interface BuyTicketContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void YingyuanPaiqidata(YingyuanPaiqi yingyuanPaiqi);
        void XiaDan(XiaDanNews xiaDanNews);
        void zhifubao(String s);
        void weixin(wxBean wxBean);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<YingyuanPaiqi> requestYingyuanPaiqi(Map<String,String> map);
        Observable<XiaDanNews> requestXiaDan(HashMap<String, String> map, HashMap<String, String> map1);
        Observable<ResponseBody> requestzhifubao(HashMap<String, String> map, HashMap<String, String> map1);
        Observable<wxBean> requestWinxin(HashMap<String, String> map, HashMap<String, String> map1);
    }
}
