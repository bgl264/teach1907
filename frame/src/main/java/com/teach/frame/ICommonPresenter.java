package com.teach.frame;

import io.reactivex.disposables.Disposable;

/**
 * Created by 任小龙 on 2020/5/28.
 */
public interface ICommonPresenter<P> extends ICommonView {
    //1，由他作为中间层来发起网络请求；2，将请求的结果回调到view层

    /**
     * @param whichApi  1，当一个页面中有多个网络请求，用于区分哪一个任务执行完成 2，望文生义，方便后期维护时了解接口的作用
     * @param pPS  1，一般用于网络请求时，如果参数是从view层动态获取的，需要通过这个可变长度的数组进行传递
     *             2，另外如果有其他参数传递的需求，也可以加入到该数组中，但切记，在取出参数的时候一定要确保存放的时候的顺序
     */
    void getData(int whichApi,P... pPS);
//    void getData(int whichApi,int loadType,P... pPS);
//    default void getTrendsData(int whichApi,int loadType,P... pPS){}

    void addObserver(Disposable pDisposable);
}
