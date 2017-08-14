package com.mmf.ancientcostume.presenter.imp.detail;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.model.GoodsDetailAndImg;
import com.mmf.ancientcostume.service.home.HomeService;
import com.mmf.ancientcostume.view.home.IDetailView;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MMF on 2017-08-03.
 */

public class DetailPresenterImp extends BasePresenter  {
    private HomeService mHomeService;
    private IDetailView view;

    public DetailPresenterImp(Context context,IDetailView view) {
        this.context = context;
        mHomeService = new HomeService();
        this.view = view;
    }


    public void getDetail(int id) {
        showLoadingDialog();
        Subscription subscription =
                mHomeService.getDetail(id).doOnNext(new Action1<GoodsDetailAndImg>() {
                    @Override
                    public void call(GoodsDetailAndImg remindDTOs) {
                        view.onSuccess(remindDTOs);
                    }
                }).subscribe(newSubscriber(new Action1<GoodsDetailAndImg>() {
                    @Override
                    public void call(GoodsDetailAndImg remindDTOs) {
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

}
