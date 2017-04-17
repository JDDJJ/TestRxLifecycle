package com.example.hoomsun.testrxlife;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.SoftReference;

import io.reactivex.disposables.Disposable;

/**
 * Created by hoomsun on 2017/4/17.
 */

public class ProgressObserver<T> extends BaseObserver<T> {
    private SoftReference<Context> context;
    private DialogHelper dlg;
    private String msg;

    public ProgressObserver(SoftReference context) {
        super(context);
        this.context = context;
    }

    public ProgressObserver(SoftReference context, String msg) {
        super(context);
        this.context = context;
        this.msg = msg;
    }

    @Override
    public void onSubscribe(Disposable d) {
        dlg = new DialogHelper(context.get());
        dlg.showProgressDlg(msg);
    }

    @Override
    public void onNext(T value) {

    }


    @Override
    public void onComplete() {
        dlg.stopProgressDlg();

    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dlg.stopProgressDlg();
    }
}
