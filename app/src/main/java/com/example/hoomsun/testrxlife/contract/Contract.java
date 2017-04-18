package com.example.hoomsun.testrxlife.contract;

import com.example.hoomsun.testrxlife.MvpBasePresenter;
import com.example.hoomsun.testrxlife.MvpBaseView;

/**
 * Created by hoomsun on 2017/4/18.
 */

public interface Contract {

    public interface View extends MvpBaseView {
    }

    public interface Presenter extends MvpBasePresenter<View> {
    }

}