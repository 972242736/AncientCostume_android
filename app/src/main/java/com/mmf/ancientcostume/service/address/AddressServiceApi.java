package com.mmf.ancientcostume.service.address;

import com.mmf.ancientcostume.common.utils.service.Response;
import com.mmf.ancientcostume.model.Address;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MMF
 * date 2017/9/29
 * Description:
 */
public interface AddressServiceApi {
    /**
     * 获取所有省份的信息
     */
    Observable<Response<List<Address>>> getProvinceList();

    /**
     * 获取所有城市的信息
     */
    @GET("address/getCityList")
    Observable<Response<List<Address>>> getCityList(@Query("provinceId") int provinceId);

    /**
     * 获取所有地区的信息
     */
    @GET("address/getDistrictList")
    Observable<Response<List<Address>>> getDistrictList(@Query("cityId") int cityId);


}
