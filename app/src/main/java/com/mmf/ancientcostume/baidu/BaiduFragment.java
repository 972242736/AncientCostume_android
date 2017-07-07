package com.mmf.ancientcostume.baidu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.mmf.ancientcostume.MyApplication;
import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.presenter.imp.home.HomePresenterImp;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.List;

/**
 * Created by MMF
 * date 2016/9/24
 * Description:
 */
public class BaiduFragment extends Fragment implements IHomeView {
    private View view;
    MapView mMapView = null;
    TextView tvCity = null;
    PoiSearch mKSearch;
    private BaiduMap mBaiduMap;
    private int REQUEST_CONTACTS = 1;
    private LatLng latLng;
    private List<PositionInfo> listPos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.baidu_fragment, null);
        MyApplication.getInstance().setBaiduFragment(this);
        initView();
        return view;
    }

    private void initView() {
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        tvCity = (TextView) view.findViewById(R.id.tv_city);
        mBaiduMap = mMapView.getMap();
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            if (MyApplication.getInstance().getCurrlocation() != null) {
                latLng = new LatLng(MyApplication.getInstance().getCurrlocation().getLatitude(), MyApplication.getInstance().getCurrlocation().getLongitude());
                setCity(latLng);
            }
        }
        mKSearch = PoiSearch.newInstance();
        PoiSearchResultListener listener = new PoiSearchResultListener();
        listener.setFragment(this);
        mKSearch.setOnGetPoiSearchResultListener(listener);
        view.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption()
//                        .city("厦门")
//                        .keyword("厦门");
//                mKSearch.searchInCity(poiCitySearchOption);
                HomePresenterImp presenter = new HomePresenterImp(BaiduFragment.this, getActivity());
//                presenter.getInfo(latLng.longitude + "," + latLng.latitude);
            }
        });
    }


    public void setCity(LatLng point) {
        latLng = point;
        if (MyApplication.getInstance().getCurrlocation() != null) {
            tvCity.setText(MyApplication.getInstance().getCurrlocation().getCity());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(point, 16);
            //设置地图中心点以及缩放级别
            //MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(u);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_marka);
            //构建MarkerOption，用于在地图上添加Marker
            MarkerOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }

    }


    /**
     * 6.0以上版本的权限问题相关方法
     */
    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestContactsPermissions();

        } else {
            LBSLocation.getInstance(MyApplication.getInstance()).startLocation();
        }
    }

    private void requestContactsPermissions() {
        String[] strings = {Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE};
        ActivityCompat.requestPermissions(getActivity(), strings, REQUEST_CONTACTS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CONTACTS) {
            if (verifyPermissions(grantResults)) {
                LBSLocation.getInstance(MyApplication.getInstance()).startLocation();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 6.0以上版本的权限问题相关方法--结束
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void setList(List list) {
        listPos = list;
        for (PositionInfo item : listPos) {
            LatLng latLng = new LatLng(item.getLocation().get(1), item.getLocation().get(0));
            setCity(latLng);
        }
    }
}
