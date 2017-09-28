package com.mmf.ancientcostume.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.adapter.BaseRecyclerAdapter;
import com.mmf.ancientcostume.common.utils.service.SecretConstant;
import com.mmf.ancientcostume.model.GoodsDetail;
import com.mmf.ancientcostume.widget.RecyclerTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MMF
 * date 2017/07/13
 * Description:发布信息适配器
 */
public class GoodsListAdapter extends BaseRecyclerAdapter<GoodsDetail> {
    Picasso picasso;

    public GoodsListAdapter(Context context) {
        super(context);
        picasso = Picasso.with(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_goods_list, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final GoodsDetail item = itemList.get(position);
        picasso.load(SecretConstant.API_HOST + "/upload/" + item.getImgName())
                .noFade()
                .placeholder(R.drawable.pictures_no)
                .transform(new RecyclerTransformation(context, 8, 2))
                .into(viewHolder.ivHome);
        viewHolder.tvTittle.setText(item.getDescribe());
        viewHolder.tvRental.setText("租金：" + item.getRental());
        viewHolder.tvDeposit.setText("押金：" + item.getDeposit());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home)
        ImageView ivHome;
        @BindView(R.id.tv_tittle)
        TextView tvTittle;
        @BindView(R.id.tv_rental)
        TextView tvRental;
        @BindView(R.id.tv_deposit)
        TextView tvDeposit;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
