package com.mmf.ancientcostume.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.activity.ImagePreviewActivity;
import com.mmf.ancientcostume.base.adapter.BaseRecyclerAdapter;
import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.model.LawyerInfo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.name;
import static android.support.v7.appcompat.R.id.image;

/**
 * Created by MMF
 * date 2017/07/13
 * Description:
 */
public class ReleaseInfoImageAdapter extends BaseRecyclerAdapter<String> {
    Picasso picasso;
    private int width;
    private int padding;
    protected boolean isScrolling = false;
    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
    public ReleaseInfoImageAdapter(Context context) {
        super(context);
        picasso = Picasso.with(context);
        width = (DipUtil.getWidth(context) - DipUtil.dip2px(context, 6)) / 4;
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
        ViewGroup.MarginLayoutParams margin9 = new ViewGroup.MarginLayoutParams(
                viewHolder.ivRelease.getLayoutParams());
//        margin9.setMargins(padding, padding, padding, padding);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(margin9);
        layoutParams9.height = width;//设置图片的高度
        layoutParams9.width = width; //设置图片的宽度
        viewHolder.ivRelease.setLayoutParams(layoutParams9);
        picasso.setLoggingEnabled(true);
        final String item = itemList.get(position);
        if (!TextUtils.isEmpty(item.trim()) && !isScrolling) {
            picasso.load(ClippingPicture.getImageContentUri(context, new File(item.trim())))
                    .noFade()
                    .into(viewHolder.ivRelease);
        } else {
            viewHolder.ivRelease.setImageResource(R.drawable.pictures_no);
        }

        viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });
        viewHolder.ivRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImagePreviewActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("selPosition", position);
                intent.putStringArrayListExtra("imgPath", (ArrayList<String>) itemList);
                context.startActivity(intent);
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
