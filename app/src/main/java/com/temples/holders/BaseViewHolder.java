package com.temples.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.temples.utils.PreferenceHelper;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T object, Context context, PreferenceHelper prefs,  int pos);
}