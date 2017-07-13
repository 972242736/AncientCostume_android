package com.mmf.ancientcostume.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.adapter.BaseRecyclerAdapter;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.model.LawyerInfo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.name;
import static android.support.v7.appcompat.R.id.image;

/**
 * Created by MMF
 * date 2017/07/13
 * Description:
 */
public class ReleaseInfoImageAdapter extends BaseRecyclerAdapter<Uri> {
    Picasso picasso;
    private int width;
    private int padding;

    public ReleaseInfoImageAdapter(Context context) {
        super(context);
        picasso = Picasso.with(context);
        width = (DipUtil.getWidth(context)-DipUtil.dip2px(context,6))/4;
//        padding = DipUtil.dip2px(context,2);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_release_info, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Uri item = itemList.get(position);
        ViewGroup.MarginLayoutParams margin9 = new ViewGroup.MarginLayoutParams(
                viewHolder.ivRelease.getLayoutParams());
//        margin9.setMargins(padding, padding, padding, padding);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(margin9);
        layoutParams9.height = width;//设置图片的高度
        layoutParams9.width = width; //设置图片的宽度
        viewHolder.ivRelease.setLayoutParams(layoutParams9);
        picasso.setLoggingEnabled(true);
        picasso.load(item)
                .noFade()
                .into(viewHolder.ivRelease);
        viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_release)
        ImageView ivRelease;
        @BindView(R.id.iv_del)
        ImageView ivDel;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
