package com.example.hoomsun.testrxlife;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoomsun.testrxlife.adapter.BaseHolder;

import java.util.Map;

import butterknife.BindView;

/**
 * Created by hoomsun on 2017/4/19.
 */

public class StringHolder extends BaseHolder<Model> {
    @BindView(R.id.textView)
    TextView textView;
    public int position;

    public void setDataPosition(int position) {
        this.position = position;
    }

    public StringHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(final Model data) {
        textView.setText(data.getTime());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().equals("可加入")) {
                    ToastUtil.showShortToast(itemView.getContext(), "可加入");
                } else
                    ToastUtil.showShortToast(itemView.getContext(), data.getTime());

            }
        });
    }


}
