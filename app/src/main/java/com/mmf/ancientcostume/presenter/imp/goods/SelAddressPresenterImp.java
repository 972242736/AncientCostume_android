package com.mmf.ancientcostume.presenter.imp.goods;

import android.util.Log;

import com.mmf.ancientcostume.activity.SelAddressActivity;
import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.model.Address;
import com.mmf.ancientcostume.service.address.AddressService;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MMF on 2017-09-28.
 */

public class SelAddressPresenterImp extends BasePresenter<SelAddressActivity> {
    private AddressService mAddressService;

    public SelAddressPresenterImp() {
        mAddressService = new AddressService();
    }

    /**
     * 获取所有省份的信息
     */
    public void getProvinceList() {
        showLoadingDialog();
        Subscription subscription =
                mAddressService.getProvinceList().doOnNext(new Action1<List<Address>>() {
                    @Override
                    public void call(List<Address> remindDTOs) {
                        view.onSuccess(remindDTOs);
                    }
                }).subscribe(newSubscriber(new Action1<List<Address>>() {
                    @Override
                    public void call(List<Address> remindDTOs) {
                        Log.e("********test", "111111");
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    /**
     * 获取所有城市的信息
     *
     * @param provinceId 省份id
     */
    public void getCityList(int provinceId) {
        showLoadingDialog();
        Subscription subscription =
                mAddressService.getCityList(provinceId).doOnNext(new Action1<List<Address>>() {
                    @Override
                    public void call(List<Address> remindDTOs) {
                        view.onSuccess(remindDTOs);
                    }
                }).subscribe(newSubscriber(new Action1<List<Address>>() {
                    @Override
                    public void call(List<Address> remindDTOs) {
                        Log.e("********test", "111111");
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    /**
     * 获取所有地区的信息
     *
     * @param cityId 城市id
     */
    public void getDistrictList(int cityId) {
        showLoadingDialog();
        Subscription subscription =
                mAddressService.getDistrictList(cityId).doOnNext(new Action1<List<Address>>() {
                    @Override
                    public void call(List<Address> remindDTOs) {
                        view.onSuccess(remindDTOs);
                    }
                }).subscribe(newSubscriber(new Action1<List<Address>>() {
                    @Override
                    public void call(List<Address> remindDTOs) {
                        Log.e("********test", "111111");
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
