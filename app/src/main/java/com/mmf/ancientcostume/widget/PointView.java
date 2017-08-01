package com.mmf.ancientcostume.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.common.utils.DipUtil;

/**
 * Created by MMF on 2017-08-01.
 */

public class PointView {
    /**
     * 添加小圆点
     */
    public static void setPoint(Context context, LinearLayout layout, int count) {
        layout.removeAllViews();
        if (count > 1) {
            for (int i = 0; i < count; i++) {
                ImageView imageView = new ImageView(context);
                LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                        DipUtil.dip2px(context, 8),
                        DipUtil.dip2px(context, 8)
                );
                // 需要 dp 转成像素单位
                linearParams.setMargins(DipUtil.dip2px(context, 8), 0, DipUtil.dip2px(context, 8), 0);
                imageView.setLayoutParams(linearParams);
                if(i == 0){
                    imageView.setImageResource(R.drawable.sel_point);
                }else{
                    imageView.setImageResource(R.drawable.point);
                }
                layout.addView(imageView);
            }
        }
    }

    /**
     * 重置小圆点图片
     */
    public static void resetPoint(LinearLayout layout) {
        if (layout != null) {
            int count = layout.getChildCount();
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    ImageView imageView = (ImageView) layout.getChildAt(i);
                    imageView.setImageResource(R.drawable.point);
                }
            }
        }

    }
}
