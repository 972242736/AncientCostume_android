package com.mmf.ancientcostume.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.adapter.BaseRecyclerAdapter;
import com.mmf.ancientcostume.model.Address;
import com.mmf.ancientcostume.model.GoodsDetail;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MMF
 * date 2017/09/28
 * Description:选择地址适配器
 */
public class SelAddressAdapter extends BaseRecyclerAdapter<Address> {


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
//        final Address item = itemList.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
