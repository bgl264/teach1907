package com.teach.frame;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Created by 任小龙 on 2020/5/28.
 */
public interface ICommonModel<T> {
    /**
     * 用于model层执行耗时任务，不处理刷新和加载逻辑
     * @param whichApi  区别接口的标识
     * @param params 泛型可变参数
     */
    void getData(ICommonPresenter pPresenter,int whichApi,T... params);

    /**
     * 用于model层执行耗时任务，不处理刷新和加载逻辑
     * @param whichApi  区别接口的标识
     * @param params 泛型可变参数
     */
//    void getData(ICommonPresenter pPresenter,int whichApi,int dataType,T... params);

    /**
     * 用于model层执行耗时任务，处理刷新和加载逻辑
     *等到8.0以下手机淘汰的那一天，我们可以将以下方法替代上边的方法，这样可以避免烦躁的强制重写
     */
//    default void getTrendsData(ICommonPresenter pPresenter,int whichApi,int dataType,T... params){}
}
