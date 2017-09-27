package com.mmf.ancientcostume.adapter.goods;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.adapter.BaseRecyclerAdapter;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.common.utils.service.SecretConstant;
import com.mmf.ancientcostume.model.GoodsDetail;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
                .transform(transformation)
                .into(viewHolder.ivHome);
        viewHolder.tvTittle.setText(item.getDescribe());
        viewHolder.tvRental.setText("租金："+item.getRental());
        viewHolder.tvDeposit.setText("押金："+item.getDeposit());
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

    Transformation transformation = new Transformation() {

        @Override
        public Bitmap transform(Bitmap source) {

            int targetWidth = (DipUtil.getWidth(context) - DipUtil.dip2px(context, 8)) / 2;

            if (source.getWidth() == 0) {
                return source;
            }

//            //如果图片小于设置的宽度，则返回原图
//            if(source.getWidth()<targetWidth){
//                return source;
//            }else{
            //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
            double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            int targetHeight = (int) (targetWidth * aspectRatio);
            if (targetHeight != 0 && targetWidth != 0) {
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            } else {
                return source;
            }
        }

//        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    };
}
