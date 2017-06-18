package com.mmf.ancientcostume.baidu;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;

/**
 * Created by MMF
 * date 2016/11/10
 * Description:
 */
public class PoiSearchResultListener implements OnGetPoiSearchResultListener {
    private BaiduFragment fragment;

    public void setFragment(BaiduFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        LatLng location = poiResult.getAllPoi().get(0).location;
        fragment.setCity(location);
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}
