package com.example.hoomsun.testrxlife;

import android.view.View;
import android.view.ViewGroup;

import com.example.hoomsun.testrxlife.adapter.BaseHolder;
import com.example.hoomsun.testrxlife.adapter.MultiItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.internal.operators.maybe.MaybeObserveOn;

/**
 * Created by hoomsun on 2017/4/19.
 */

public class MultiAdapter extends MultiItemAdapter<Model> {
    public List<StringHolder> myViewHolderList = new ArrayList<>();

    public MultiAdapter(List<Model> data) {
        super(data);
        addItemType(0, R.layout.text_blank);
        addItemType(1, R.layout.text_blank2);
        addItemType(2, R.layout.text_blank2);

    }


    @Override
    public void onBindViewHolder(BaseHolder<Model> holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof StringHolder) {
            StringHolder holder2 = (StringHolder) holder;
            holder2.setDataPosition(position);
            if (!(myViewHolderList.contains(holder))) {
                myViewHolderList.add((StringHolder) holder);
            }
        }
    }

    @Override
    public BaseHolder<Model> getHolder(int viewType, View v) {
        BaseHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new StringHolder(v);
                if (!(myViewHolderList.contains(holder))) {
                    myViewHolderList.add((StringHolder) holder);
                }
                break;
            case 1:
                holder = new StringHolder2(v);
                break;
            case 2:
                holder = new StringHolder2(v);
                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        int value = mData.get(position).getType();

        return value;
    }

    public void notifyData() {
        for (int i = 0; i < myViewHolderList.size(); i++) {
            myViewHolderList.get(i).textView
                    .setText(mData.get(myViewHolderList.get(i).position).getTime());
        }
    }
}
