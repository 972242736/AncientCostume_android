package com.mmf.ancientcostume.widget;

import android.content.Context;
import android.graphics.Bitmap;

import com.mmf.ancientcostume.common.utils.DipUtil;
import com.squareup.picasso.Transformation;

/**
 * Created by MMF on 2017-09-28.
 */

public class RecyclerTransformation implements Transformation {
    private Context context;
    private int space = 8;
    private int count = 2;

    /**
     *
     * @param context
     * @param space    一行的总间距
     * @param count    一行显示的数量
     */
    public RecyclerTransformation(Context context, int space, int count) {
        this.context = context;
        this.count = count;
        this.space = space;
    }

    public RecyclerTransformation(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap transform(Bitmap source) {

        int targetWidth = (DipUtil.getWidth(context) - DipUtil.dip2px(context, space)) / count;
        if (source.getWidth() == 0) {
            return source;
        }
        //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
        double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
        int targetHeight = (int) (targetWidth * aspectRatio);
        if (targetHeight != 0 && targetWidth != 0) {
            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
                source.recycle();
            }
            return result;
        } else {
            return source;
        }
    }

    @Override
    public String key() {
        return "transformation" + " desiredWidth";
    }
}
