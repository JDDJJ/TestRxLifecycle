package com.example.hoomsun.testrxlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.leakcanary.LeakCanary;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        String[] s = {"a", "b", "c"};
//        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
//        final Observable<String[]> map = Observable.just(s).map(new Function<String[], String[]>() {
//            @Override
//            public String[] apply(String[] strings) throws Exception {
//                return strings;
//            }
//        });
//        Disposable disposable = interval.flatMap(new Function<Long, Observable<String[]>>() {
//            @Override
//            public Observable<String[]> apply(Long aLong) throws Exception {
//                return map;
//            }
//        }).compose(this.<String[]>bindToLifecycle())
//                .subscribe(new Consumer<String[]>() {
//                    @Override
//                    public void accept(String[] strings) throws Exception {
//                        for (String string : strings) {
//                            Log.e("***", string);
//                        }
//                    }
//                });
//        if ()
// .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String[]>() {
//                    @Override
//                    public void accept(String[] strings) throws Exception {
//                        for (String string : strings) {
//                            Log.e("***", string);
//                        }
//
//                    }
//                });
//        Observable.fromArray(s)
//                .subscribeOn(Schedulers.io())
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) throws Exception {
//                        return s;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.e("--", s);
//                    }
//                });
    }

    @OnClick(R.id.button)
    void on() {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }


}
