package com.teach.frame;

import android.app.Activity;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by 任小龙 on 2020/5/28.
 */
public class CommonPresenter<V extends ICommonView, M extends ICommonModel> implements ICommonPresenter {
    private SoftReference<V> mView;
    private SoftReference<M> mModel;
    private List<Disposable> mDisposableList;
    private WeakReference<Activity> mActivityWeakReference;
    private LoadView mInstance;

    /**
     * 构造中，接收view和model的对象
     *
     * @param pView
     * @param pModel
     */
    public CommonPresenter(V pView, M pModel) {
        mDisposableList = new ArrayList<>();
        mView = new SoftReference<>(pView);//软引用包裹，当内存不足的时候确保能够回收，避免内存溢出
        mModel = new SoftReference<>(pModel);
    }

    public void allowLoading(Activity pActivity) {
        mActivityWeakReference = new WeakReference<>(pActivity);
    }

    @Override
    public void onSuccess(int whichApi, Object... pD) {
        if (mView != null && mView.get() != null) mView.get().onSuccess(whichApi, pD);
        if (mInstance != null && mInstance.isShowing())mInstance.dismiss();
    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        if (mView != null && mView.get() != null) mView.get().onFailed(whichApi, pThrowable);
        if (mInstance != null && mInstance.isShowing())mInstance.dismiss();
    }

    /**
     * 发起普通的网络请求
     *
     * @param whichApi 接口标识
     * @param pObjects 作为公共封装的方法，并不知道未来传递的是什么类型的参数，也不知道会传递多少个，所以通过泛型可变参数来声明形参
     */
    @Override
    public void getData(int whichApi, Object... pObjects) {
        if (mActivityWeakReference != null && mActivityWeakReference.get() != null && mActivityWeakReference.get() instanceof Activity) {
            Activity activity = mActivityWeakReference.get();
            if (!activity.isFinishing() && mInstance == null){
                mInstance = new LoadView(activity,null);
            }
            int load = -1;
            if (pObjects != null && pObjects.length != 0 && pObjects[0] instanceof Integer){
                load = (int) pObjects[0];
            }
            if (load != LoadTypeConfig.MORE && load != LoadTypeConfig.REFRESH && mInstance != null && !mInstance.isShowing()){
               mInstance.show();
            }
        }
        if (mModel != null && mModel.get() != null) mModel.get().getData(this, whichApi, pObjects);
    }

    /**
     * 将所有网络请求的订阅关系，统一到中间层的集合中，用于view销毁时，统一处理
     *
     * @param pDisposable 订阅对象
     */
    @Override
    public void addObserver(Disposable pDisposable) {
        if (mDisposableList == null) return;
        mDisposableList.add(pDisposable);
    }

    /**
     * 当activity页面销毁执行ondestroy时，这个时候已经没有展示数据的需求了，所以首先要将在该页面进行的所有网络请求
     * 终止，以免gc回收时发现view仍被持有不能回收导致内存泄漏。当然这个即使不处理，这个泄漏的时间会很短暂，当gc线程
     * 下一次检测到该对象，网络任务如果已经完成，并不影响activity的回收
     * <p>
     * 在MVP使用中，为了实现视图和数据的解耦，我们通过中间层presenter来进行双向绑定和处理，但是当view销毁时，由于P层仍然
     * 持有view的引用，也可能会发生view不能被回收导致内存泄漏的情况，所以当页面销毁时，将presenter同view和model进行解绑。
     */
    public void clear() {
        for (Disposable dis : mDisposableList) {
            if (dis != null && !dis.isDisposed()) dis.dispose();
        }
        if (mView != null) {
            mView.clear();
            mView = null;
        }
        if (mModel != null) {
            mModel.clear();
            mModel = null;
        }
        if (mInstance != null && mInstance.isShowing())mInstance.dismiss();
        if (mActivityWeakReference != null){
            mActivityWeakReference.clear();
            mActivityWeakReference = null;
        }
    }

   /* @Override
    public void getData(int whichApi, int loadType, Object[] pObjects) {
        mModel.getData(this, whichApi, loadType, pObjects);
    }*/

    /**
     * 发起涉及刷新和加载的网络请求
     *
     * @param whichApi
     * @param loadType
     * @param pObjects
     */
   /* @Override
    public void getTrendsData(int whichApi, int loadType, Object... pObjects) {
        mModel.getTrendsData(this, whichApi, loadType, pObjects);
    }*/
}
