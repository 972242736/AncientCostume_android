package com.mmf.ancientcostume.common.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * @author 福建博森创想
 * @ClassName: DipUtil
 * @Description: dip和px之间转换工具类
 * @date 2015年5月26日 下午5:45:55
 */
public class DipUtil {


    /**
     * @param context 上下文
     * @param dpValue dp数值
     * @return px数值 .
     * @Title: dip2px
     * @Description: 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @author 博森
     * @date 2015年5月26日 下午5:46:06
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param context 上下文
     * @param pxValue px数值
     * @return dp数值 .
     * @Title: dip2px
     * @Description: 根据手机的分辨率从px 的单位 转成为 dp
     * @author 博森
     * @date 2015年5月26日 下午5:46:06
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @return 屏幕高度 .
     * @Title: getHeight
     * @Description: 获得屏幕高度
     * @author 博森
     * @date 2015年5月26日 下午5:47:22
     */
    public static int getHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * @return 屏幕宽度 .
     * @Title: getHeight
     * @Description: 获得屏幕宽度
     * @author 博森
     * @date 2015年5月26日 下午5:47:22
     */
    public static int getWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * @return 屏幕宽度 .
     * @Title: getHeight
     * @Description: 获得屏幕宽度
     * @author 博森
     * @date 2015年5月26日 下午5:47:22
     */
    public static int getWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取RelativeLayout的width和height设置一定值的view
     * @param width
     * @param height
     * @param view
     * @return
     */
    public static void setRelativeLayout(int width, int height, View view) {
        //设置图片显示的宽高
        RelativeLayout.LayoutParams linearParams = new RelativeLayout.LayoutParams(
                width,
                height
        );
        view.setLayoutParams(linearParams);
    }

    /**
     * 获取LinearLayout的width和height设置一定值的view
     * @param width
     * @param height
     * @param view
     * @return
     */
    public static void setLinearLayout(int width, int height, View view) {
        //设置图片显示的宽高
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                width,
                height
        );
        view.setLayoutParams(linearParams);
    }

}
