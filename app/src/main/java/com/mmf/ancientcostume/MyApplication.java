package com.mmf.ancientcostume;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.mmf.ancientcostume.baidu.BaiduFragment;
import com.mmf.ancientcostume.baidu.LBSLocation;
import com.mmf.ancientcostume.fragment.user.ReleaseFragment;

import java.util.ArrayList;
import java.util.List;

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
    private ReleaseFragment releaseFragment;

    //监控所有activity使用
    private List<Activity> activities = new ArrayList<>();
    private int numOfActivitys = 0;

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

    public void setReleaseFragment(ReleaseFragment releaseFragment) {
        this.releaseFragment = releaseFragment;
    }

    public void setCurrlocation(BDLocation currlocation) {
        this.currlocation = currlocation;
        if (baiduFragment != null) {
            baiduFragment.setCity(new LatLng(currlocation.getLatitude(), currlocation.getLongitude()));
        }
        if (releaseFragment != null) {
            releaseFragment.setAddress();
        }
    }

    /**
     * 监测所有Activity状态
     */
    private ActivityLifecycleCallbacks TYActivityLifecycleCallback = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            numOfActivitys++;
            activities.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            numOfActivitys--;
            activities.remove(activities);
        }
    };

}
