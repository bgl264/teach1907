package com.teach.frame;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 任小龙 on 2020/5/29.
 */
public abstract class BaseObserver implements Observer {
    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(Object value) {
        onSuccess(value);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    public abstract void onSuccess(Object value);

    public abstract void onFailed(Throwable e);

    public void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
