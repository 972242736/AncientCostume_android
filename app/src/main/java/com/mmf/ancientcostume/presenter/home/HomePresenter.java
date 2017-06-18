package com.mmf.ancientcostume.presenter.home;

import android.content.Context;
import android.util.Log;

import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.model.LawyerInfo;
import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.service.home.HomeService;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class HomePresenter extends BasePresenter {
    private HomeService mHomeService;
    private IHomeView view;

    public HomePresenter(IHomeView view, Context context) {
        this.mCompositeSubscription = new CompositeSubscription();
        this.context = context;
        mHomeService = new HomeService();
        this.view = view;
    }

    public void getLawyer() {
        showLoadingDialog();
        Subscription subscription = mHomeService.getLawyer()
                .doOnNext(new Action1<List<LawyerInfo>>() {
                    @Override
                    public void call(List<LawyerInfo> remindDTOs) {
                        hideLoadingDialog();
                        view.setList(remindDTOs);
                    }
                })
                .subscribe(newSubscriber(new Action1<List<LawyerInfo>>() {
                    @Override
                    public void call(List<LawyerInfo> remindDTOs) {
                        Log.i(TAG, "getNotification---" + remindDTOs.toString());
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
    public void getInfo(String loc) {
        showLoadingDialog();
        Subscription subscription = mHomeService.getInfo(loc)
                .doOnNext(new Action1<List<PositionInfo>>() {
                    @Override
                    public void call(List<PositionInfo> remindDTOs) {
                        List<PositionInfo> remindDTOs_ = remindDTOs;
                        view.setList(remindDTOs);
                    }
                })
                .subscribe(newSubscriber(new Action1<List<PositionInfo>>() {
                    @Override
                    public void call(List<PositionInfo> remindDTOs) {
                        Log.i(TAG, "getNotification---" + remindDTOs.toString());
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
