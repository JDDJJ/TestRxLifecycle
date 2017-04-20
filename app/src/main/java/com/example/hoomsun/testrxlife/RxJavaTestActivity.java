package com.example.hoomsun.testrxlife;

import butterknife.BindView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hoomsun.testrxlife.BaseActivity;
import com.example.hoomsun.testrxlife.R;
import com.example.hoomsun.testrxlife.contract.Contract;
import com.example.hoomsun.testrxlife.presenter.PresenterImpl;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestActivity extends BaseActivity implements Contract.View {


    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    Disposable d1;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    private int i;

    @Override
    protected int bindLayout() {
        return R.layout.activity_rx_java_test;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void doBusiness(Context mContext) {
        PresenterImpl presenter = new PresenterImpl(this);
        presenter.attachView(this);
        presenter.doBiz();
    }


    @OnClick({R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                //没出现背压 时会用这个
                initObservableToObserver();
                break;
            case R.id.button3:
                //正常 匹配模式
                initFlowableToSubscriber();
                break;
            case R.id.button4:
                //通过Flowable.create创建
                initFlowableToConsumer();
                break;
            case R.id.button5:
                //没搞懂
                initRange();
                break;
            case R.id.button6:
                initObservableToConsumer();
                break;
            case R.id.button7:
                init6();
                break;
            case R.id.button8:
                initJust();
                break;
        }
    }

    private void initRange() {
        Flowable.range(1, 10).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("RxJava", "----" + integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initFlowableToConsumer() {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {

            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

                for (int i = 0; i < 1000; i++) {
                    e.onNext(i + "");
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);//指定背压处理策略，抛出异常
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String string) throws Exception {
                Log.d("RxJava", string);
//                Thread.sleep(1000);
            }
        };
        Consumer<Throwable> consumer1 =
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("RxJava", throwable.toString());
                    }
                };
        flowable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, consumer1);

    }

    private void initFlowableToSubscriber() {

        //观察者  下游
        Subscriber<String> subscribe = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.e("RxJava", "FlowableToSubscriber+++onSubscribe");
                s.request(Long.MAX_VALUE);

            }

            @Override
            public void onNext(String value) {

                Log.e("RxJava", "FlowableToSubscriber+++onNext" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxJava", "FlowableToSubscriber+++onError");
            }

            @Override
            public void onComplete() {
                Log.e("RxJava", "FlowableToSubscriber+++onComplete");
            }
        };

        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {

            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

                for (int i = 0; i < 1000; i++) {
                    e.onNext(i + "");
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);//指定背压处理策略，抛出异常

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }

    private void initObservableToObserver() {
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
                d1 = d;
            }

            @Override
            public void onNext(String value) {
                if (value == "4") {
                    d1.dispose();
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<String>(new SoftReference(this)) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
//                       d1 = d;
                    }

                    @Override
                    public void onNext(String value) {
                        super.onNext(value);
                    }
                });
//        if (i == 2)
//            startActivity(new Intent(this, Main2Activity.class));
//        Observable.just("hello world!")
//                .flatMap(new Function<String, Observable<Long>>() {
//                    @Override
//                    public Observable<Long> apply(String s) throws Exception {
//                        return Observable.interval(10, TimeUnit.SECONDS);
//                    }
//
//                })
//                .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.i("aa", "....oh,oh,no!!..........." + aLong);
//                    }
//                });
    }

    private void initObservableToConsumer() {
        //在主线程创建一个上游发送的
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.e("RxJava", "Observable thread is : " + Thread.currentThread().getName());

                for (int i = 0; i < 100; i++) {
                    e.onNext(i + "");
                }
                e.onComplete();
            }
        });
        //主线程创建一个下游处理的
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("RxJava", "Consumer+++accept+++++" + s);
            }
        };
        stringObservable.subscribe(consumer);
    }

    private void init6() {
    }

    /**
     * 输出
     * Next: 1
     * Next: 2
     * Next: 3
     * Sequence complete.
     */
    private void initJust() {
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (d1 != null) {
            d1.dispose();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (d1 != null) {
            d1.dispose();
        }
    }

}
