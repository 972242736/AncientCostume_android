package com.mmf.ancientcostume.presenter.imp.goods;

import android.util.Log;

import com.mmf.ancientcostume.activity.GoodsDetailActivity;
import com.mmf.ancientcostume.activity.GoodsListActivity;
import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.model.GoodsDetail;
import com.mmf.ancientcostume.model.GoodsDetailAndImg;
import com.mmf.ancientcostume.service.home.HomeService;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MMF on 2017-08-03.
 */

public class GoodsListPresenterImp extends BasePresenter<GoodsListActivity>  {
    private HomeService mHomeService;
    private List<GoodsDetail> list= new ArrayList<>();

    public GoodsListPresenterImp() {
        mHomeService = new HomeService();
    }


    public void getGoodsList(final int pageNo, int pageSize) {
        showLoadingDialog();
        Subscription subscription =
                mHomeService.getGoodsList(pageNo,pageSize).doOnNext(new Action1<List<GoodsDetail>>() {
                    @Override
                    public void call(List<GoodsDetail> remindDTOs) {
                        if(remindDTOs.size() == 0){
                            showToast("没有更多的数据！");
                            return;
                        }
                        if(pageNo == 1){
                            list = remindDTOs;
                        }else{
                            list.addAll(remindDTOs);
                        }
                        view.onSuccess(list);
                    }
                }).subscribe(newSubscriber(new Action1<List<GoodsDetail>>() {
                    @Override
                    public void call(List<GoodsDetail> remindDTOs) {
                        Log.e("********test","111111");
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

}
