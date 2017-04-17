package com.example.hoomsun.testrxlife;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by hoomsun on 2017/4/7.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;

    /**
     * 是否允许全屏
     **/
    private boolean isFullScreen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isSetStatusBar) {
            steepStatusBar();
        }

        if (isFullScreen) {
            setAllowFullScreen(true);
        }
        if (mContextView == null) {
            mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        }
        setContentView(mContextView);
        ButterKnife.bind(this);
        initView(mContextView);
        doBusiness(this);
    }
    /**
     * [绑定布局]
     *
     * @return
     */
    protected abstract int bindLayout();
    /**
     * [初始化控件]
     *
     * @param view
     */
    protected abstract void initView(final View view);

    /**
     * [业务操作]
     *
     * @param mContext
     */
    protected abstract void doBusiness(Context mContext);


    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    protected void setAllowFullScreen(boolean allowFullScreen) {
        if (allowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
