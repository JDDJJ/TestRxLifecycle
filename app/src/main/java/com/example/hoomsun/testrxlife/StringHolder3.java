package com.example.hoomsun.testrxlife;

import android.view.View;
import android.widget.TextView;

import com.example.hoomsun.testrxlife.adapter.BaseHolder;

import java.util.Map;

import butterknife.BindView;

/**
 * Created by hoomsun on 2017/4/19.
 */

public class StringHolder3 extends BaseHolder<String> {
    @BindView(R.id.textView)
    TextView textView;

    public StringHolder3(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(final String data) {
        textView.setText(data + "text");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShortToast(itemView.getContext(), data);
            }
        });
    }


}
