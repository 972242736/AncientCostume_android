package com.mmf.ancientcostume.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.activity.ImagePreviewActivity;
import com.mmf.ancientcostume.base.adapter.BaseRecyclerAdapter;
import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.other.zhy.imageloader.MyAdapter;
import com.mmf.ancientcostume.widget.IImagePreview;
import com.mmf.ancientcostume.widget.RecyclerTransformation;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MMF
 * date 2017/07/13
 * Description:发布信息适配器
 */
public class ReleaseInfoImageAdapter extends BaseRecyclerAdapter<String> {
    Picasso picasso;
    private int width;
    //    private int padding;
    protected boolean isScrolling = false;
    private MyAdapter.JumpPreviewActivityListener jPAListener;
    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    public ReleaseInfoImageAdapter(Context context) {
        super(context);
        picasso = Picasso.with(context);
        width = (DipUtil.getWidth(context) - DipUtil.dip2px(context, 12)) / 4;
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
        //设置图片显示的宽高
        ViewGroup.MarginLayoutParams margin9 = new ViewGroup.MarginLayoutParams(
                viewHolder.ivRelease.getLayoutParams());
//        margin9.setMargins(padding, padding, padding, padding);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(margin9);
        layoutParams9.height = width;//设置图片的高度
        layoutParams9.width = width; //设置图片的宽度
        viewHolder.ivRelease.setLayoutParams(layoutParams9);
        final String item = itemList.get(position);
        if (!TextUtils.isEmpty(item.trim()) && !isScrolling) {
            picasso.load(ClippingPicture.getImageContentUri(context, new File(item.trim())))
                    .noFade()
                    .placeholder(R.drawable.pictures_no)
                    .transform(new RecyclerTransformation(context, 12, 4))
                    .into(viewHolder.ivRelease);
        } else {
            viewHolder.ivRelease.setImageResource(R.drawable.pictures_no);
        }
        //设置删除的监听
        viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });
        //点击图片进入图片预览
        viewHolder.ivRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    jPAListener.jump(position,itemList);

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
    public interface JumpPreviewActivityListener{
        void jump(int position,List<String> dirAllPath);
    }

    public void setjPAListener(MyAdapter.JumpPreviewActivityListener jPAListener) {
        this.jPAListener = jPAListener;
    }

}
