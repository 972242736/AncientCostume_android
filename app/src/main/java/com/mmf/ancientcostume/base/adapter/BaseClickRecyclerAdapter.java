package com.mmf.ancientcostume.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmf.ancientcostume.R;

/**
 * Created by MMF on 2017-09-29.
 */

public abstract class BaseClickRecyclerAdapter<T> extends BaseRecyclerAdapter<T> {
    protected OnItemClickListener onItemClickListener = null;

    public BaseClickRecyclerAdapter(Context context) {
        super(context);
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
    }
}
