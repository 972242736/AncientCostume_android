package com.mmf.ancientcostume;

import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.mmf.ancientcostume.baidu.BaiduFragment;
import com.mmf.ancientcostume.baidu.LBSLocation;

/**
 * Created by MMF
 * date 2016/11/8
 * Description:
 */
public class MyApplication extends Application {
    private static MyApplication mInstance = null;
    // 定位结果
    public BDLocation currlocation = null;
    private BaiduFragment baiduFragment;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        mInstance = this;
        // 启动定位
        LBSLocation.getInstance(this).startLocation();
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public BaiduFragment getBaiduFragment() {
        return baiduFragment;
    }

    public void setBaiduFragment(BaiduFragment baiduFragment) {
        this.baiduFragment = baiduFragment;
    }

    public BDLocation getCurrlocation() {
        return currlocation;
    }

    public void setCurrlocation(BDLocation currlocation) {
        if (baiduFragment != null)
            baiduFragment.setCity(new LatLng(currlocation.getLatitude(), currlocation.getLongitude()));
        this.currlocation = currlocation;
    }

}
