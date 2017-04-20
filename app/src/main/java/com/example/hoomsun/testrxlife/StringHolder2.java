package com.example.hoomsun.testrxlife;

import android.view.View;
import android.widget.TextView;

import com.example.hoomsun.testrxlife.adapter.BaseHolder;

import java.util.Map;

import butterknife.BindView;

/**
 * Created by hoomsun on 2017/4/19.
 */

public class StringHolder2 extends BaseHolder<Model> {
    @BindView(R.id.textView)
    TextView textView;

    public StringHolder2(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(final Model data) {
        textView.setText(data.getTime());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShortToast(itemView.getContext(), data.getTime());
            }
        });
    }

}
