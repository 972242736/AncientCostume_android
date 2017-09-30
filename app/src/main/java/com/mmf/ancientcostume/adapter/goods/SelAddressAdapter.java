package com.mmf.ancientcostume.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.adapter.BaseClickRecyclerAdapter;
import com.mmf.ancientcostume.model.Address;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MMF
 * date 2017/09/28
 * Description:选择地址适配器
 */
public class SelAddressAdapter extends BaseClickRecyclerAdapter<Address> implements View.OnClickListener {


    public SelAddressAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_sel_address, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Address item = itemList.get(position);
        viewHolder.tvAddress.setText(item.getName());
        viewHolder.tvAddress.setOnClickListener(this);
        viewHolder.tvAddress.setTag(position);
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取position
            onItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lyt_address)
        LinearLayout lytAddress;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
