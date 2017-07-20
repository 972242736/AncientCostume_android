package com.mmf.ancientcostume.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.mmf.ancientcostume.common.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> itemList = new ArrayList<T>();

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 在原有的数据上添加新数据
     *
     * @param itemList
     */
    public void addItems(List<T> itemList) {
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    /**
     * 设置为新的数据，旧数据会被清空
     *
     * @param itemList
     */
    public void setItems(List<T> itemList) {
        this.itemList.clear();
        if (!ListUtils.isEmpty(itemList)) {
            this.itemList.addAll(itemList);
        }
        notifyDataSetChanged();


    }

    /**
     * 清空数据
     */
    public void clearItems() {
        itemList.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public List<T> getItem() {
        return itemList;
    }

    public void removeItem(int pos) {
        if (itemList != null) {
            itemList.remove(pos);
            notifyDataSetChanged();
        }
    }
    public void addItem(int pos,T path) {
       itemList.add(pos,path);
        notifyDataSetChanged();
    }
}
