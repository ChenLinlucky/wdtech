package com.bw.movie.mvp.contract;

import com.bw.movie.bean.PinglunNews;
import com.bw.movie.bean.XiangqingNews;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.HashMap;

import io.reactivex.Observable;


public interface xiangqingContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void data(XiangqingNews xiangqingNews);
        void datapinglun(PinglunNews pinglunNews);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<XiangqingNews> requestxiangqing(int movieId);
        Observable<PinglunNews> requestpingnlun(HashMap<String, String> map);
    }
}
