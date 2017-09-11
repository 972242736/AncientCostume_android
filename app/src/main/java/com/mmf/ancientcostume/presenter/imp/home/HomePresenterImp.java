package com.mmf.ancientcostume.presenter.imp.home;

import android.content.Context;
import android.util.Log;

import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.model.User;
import com.mmf.ancientcostume.service.home.HomeService;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class HomePresenterImp extends BasePresenter {
    private HomeService mHomeService;
    private IHomeView view;
    public HomePresenterImp(IHomeView view, Context context) {
        this.context = context;
        mHomeService = new HomeService();
        this.view = view;
    }

    public void getLawyer() {
        showLoadingDialog();
        mHomeService.getLawyer();
    }
    public void list() {
        showLoadingDialog();
        Subscription subscription =
                mHomeService.list().doOnNext(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> remindDTOs) {
                    }
                })
                .subscribe(newSubscriber(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> remindDTOs) {
                        Log.i(TAG, "getNotification---11" + remindDTOs.toString());
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
