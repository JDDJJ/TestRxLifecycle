package com.example.hoomsun.testrxlife;


import android.content.Context;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.lang.ref.SoftReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hoomsun on 2017/4/17.
 */
public class BaseObserver<T> implements Observer<T> {

    private SoftReference<Context> context;

    public BaseObserver(SoftReference context) {
        this.context =context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        
    }

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
