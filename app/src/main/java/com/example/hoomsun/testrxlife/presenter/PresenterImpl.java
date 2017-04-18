package com.example.hoomsun.testrxlife.presenter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.hoomsun.testrxlife.BaseObserver;
import com.example.hoomsun.testrxlife.ProgressObserver;
import com.example.hoomsun.testrxlife.contract.Contract;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
* Created by hoomsun on 2017/04/18
*/

public class PresenterImpl implements Contract.Presenter{

    private  Context context;
    private Contract.View view;

    public PresenterImpl(Context context) {
         this.context = context;

    }

    @Override
    public void attachView(@NonNull Contract.View mvpView) {
        view =mvpView;
    }
    public void doBiz(){
//在主线程创建一个上游发送的
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.e("RxJava", "Observable thread is : " + Thread.currentThread().getName());
                e.onNext("--1--");
                e.onComplete();
            }
        });
        //创建订阅者  观察者 下游
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("RxJava", "observer+++onSubscribe");
            }

            @Override
            public void onNext(String value) {
                if (value == "4") {
                }
                Log.e("RxJava", "observer+++onNext" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxJava", "observer+++onError");
            }

            @Override
            public void onComplete() {
                Log.e("RxJava", "observer+++onComplete");
            }
        };

        stringObservable
                .delay(10, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("---", "dispose");
                    }
                })
                .compose(view.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new SoftReference(context)) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                    }
                    @Override
                    public void onNext(String value) {
                        super.onNext(value);
                    }
                });

    }
    @Override
    public void detachView() {

    }
}