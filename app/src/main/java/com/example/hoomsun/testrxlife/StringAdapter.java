package com.example.hoomsun.testrxlife;

import android.view.View;
import android.widget.TextView;

import com.example.hoomsun.testrxlife.adapter.BaseHolder;
import com.example.hoomsun.testrxlife.adapter.QiuckAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hoomsun on 2017/4/19.
 */

public class StringAdapter extends QiuckAdapter<String> {

    public StringAdapter(List infos) {
        super(infos);
    }

    @Override
    public BaseHolder<String> getHolder(View v) {
        return new StringHolder3(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.text_blank;
    }
}
