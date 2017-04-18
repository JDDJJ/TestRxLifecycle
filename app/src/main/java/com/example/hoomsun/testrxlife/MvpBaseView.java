package com.example.hoomsun.testrxlife;

import android.content.Context;

import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.ObservableTransformer;

/**
 * Created by HOME on 2016/8/9.
 */

public interface MvpBaseView {
    /**
     * RxFragment/RxActivity 中方法,声明在view中 便于在mvp中的presenter里调用
     * @param <T> T
     * @return
     */
    <T> ObservableTransformer<T, T> bindToLifecycle();

    /**
     * RxFragment/RxActivity 中方法,声明在view中 便于在mvp中的presenter里调用
     * @param <T> T
     * @return
     */
    <T> ObservableTransformer<T, T> bindUntilEvent(ActivityEvent event);
}
