package com.example.hoomsun.testrxlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.example.hoomsun.testrxlife.adapter.BaseHolder;
import com.example.hoomsun.testrxlife.adapter.QiuckAdapter;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends RxAppCompatActivity {

    @BindView(R.id.activity_main2)
    RelativeLayout activityMain2;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private MultiAdapter adapter;
    private List<Model> maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            strings.add("text" + i);
        }
        maps = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Model map = new Model();
            map.setTime("00");
            if (i > 30)
                map.setType(2);
            else
                map.setType(0);
            map.setCountTime(i * 6000);
            maps.add(map);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MultiAdapter(maps);
        mRecyclerView.setAdapter(adapter);

//        startActivity(new Intent(this, RxJavaTestActivity.class));
//        DoCountDown.CountDown(maps).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            adapter.notifyData();
//                        }
//                    }
//                });
//        mRecyclerView.setAdapter(new StringAdapter(strings));

    }

    @Override
    protected void onResume() {
        super.onResume();
        DoCountDown.countDown().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<Long, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(Long aLong) throws Exception {
                        return DoCountDown.doBiz(maps);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Boolean>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(new Observer<Boolean>() {
                    Disposable d;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.d = d;
                    }

                    @Override
                    public void onNext(Boolean value) {

                        adapter.notifyData();
                        if (!value) {
                            d.dispose();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    void gan() {

    }
}
