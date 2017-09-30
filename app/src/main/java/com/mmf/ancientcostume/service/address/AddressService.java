package com.mmf.ancientcostume.service.address;

import com.mmf.ancientcostume.common.utils.service.RetrofitUtil;
import com.mmf.ancientcostume.common.utils.service.SecretConstant;
import com.mmf.ancientcostume.model.Address;

import java.util.List;

import rx.Observable;

/**
 * Created by MMF
 * date 2017/9/29
 * Description:
 */
public class AddressService extends RetrofitUtil {
    private static AddressServiceApi addressService;

    public static AddressServiceApi getService() {
        if (addressService == null) {
            addressService = getRetrofit(SecretConstant.API_HOST).create(AddressServiceApi.class);
        }
        return addressService;
    }

    /**
     * 获取所有省份的信息
     */
    public Observable<List<Address>> getProvinceList() {
        return getService().getProvinceList()
                .compose(this.<List<Address>>applySchedulers());
    }

    /**
     * 获取所有城市的信息
     */
    public Observable<List<Address>> getCityList(int provinceId) {
        return getService().getCityList(provinceId)
                .compose(this.<List<Address>>applySchedulers());
    }

    /**
     * 获取所有地区的信息
     */
    public Observable<List<Address>> getDistrictList(int cityId) {
        return getService().getDistrictList(cityId)
                .compose(this.<List<Address>>applySchedulers());
    }
}
