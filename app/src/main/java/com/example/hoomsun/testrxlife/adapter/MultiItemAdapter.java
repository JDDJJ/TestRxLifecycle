package com.example.hoomsun.testrxlife.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdd on 2015/11/27.
 */
public abstract class MultiItemAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {
    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private View mContentView;
    protected int mLayoutResId;
    protected List<T> mData;
    /**
     * layouts indexed with their types
     */
    private SparseArray<Integer> layouts;

    public MultiItemAdapter(List<T> data) {
        this(0, data);
    }

    public MultiItemAdapter(View contentView, List<T> data) {
        this(0, data);
        mContentView = contentView;
    }

    public MultiItemAdapter(int layoutResId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    /**
     * 创建Hodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        BaseHolder<T> mHolder = null;
        switch (viewType) {
            default:
                mHolder = onCreateDefViewHolder(parent, viewType);
                mHolder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
                    @Override
                    public void onViewClick(View view, int position) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(view, mData.get(position), position);
                        }
                    }
                });
        }
        return mHolder;
    }

    protected View getItemView(int layoutResId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
    }

    protected BaseHolder<T> createBaseViewHolder(int viewType, ViewGroup parent, int layoutResId) {
        if (mContentView == null) {
            return getHolder(viewType, getItemView(layoutResId, parent));
        }
        return getHolder(viewType, mContentView);
    }

    protected BaseHolder<T> onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(viewType, parent, getLayoutId(viewType));
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType);
    }

    /**
     *
     * @param viewType 根据viewtype 传入layout
     * @param layoutResId
     */
    protected  void addItemType(int viewType, int layoutResId) {
        if (layouts == null) {
            layouts = new SparseArray<>();
        }
        layouts.put(viewType, layoutResId);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseHolder<T> holder, int position) {
        holder.setData(mData.get(position));
    }

    /**
     * 数据的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public List<T> getInfos() {
        return mData;
    }

    /**
     * 获得item的数据
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    /**
     * 子类实现提供holder
     *
     * @param viewType
     * @param v
     * @return
     */
    public abstract BaseHolder<T> getHolder(int viewType, View v);

    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
