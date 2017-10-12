package com.mmf.ancientcostume.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.common.utils.service.SecretConstant;
import com.mmf.ancientcostume.model.GoodsImg;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by MMF on 2017-07-31.
 */

public class ViewPImgAdapter<T> extends PagerAdapter {
    private Picasso picasso;
    private List<T> list;
    private int type;   //1：表示加载的本地的资源文件 else ：表示加载别的类型的文件
    private Context context;

    public ViewPImgAdapter(Context context,List<T> views,int type) {
        this.context = context;
        this.list = views;
        this.type = type;
        picasso = Picasso.with(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        if(type == 1){
            DipUtil.setRelativeLayout(DipUtil.getWidth(context),DipUtil.getHeight(context),imageView);
            imageView.setImageResource((Integer) list.get(position));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }else{
            DipUtil.setRelativeLayout(DipUtil.getWidth(context),DipUtil.getWidth(context),imageView);
            picasso.load(SecretConstant.API_HOST+"/upload/"+((GoodsImg)list.get(position)).getImgName())
                    .noFade()
                    .into(imageView);
        }

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }
}
