package com.mmf.ancientcostume.presenter.imp.home;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.model.User;
import com.mmf.ancientcostume.presenter.IPresenter;
import com.mmf.ancientcostume.service.home.HomeService;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.List;
import java.util.Objects;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class HomePresenterImp extends BasePresenter implements IPresenter<Objects>{
    private HomeService mHomeService;
    private IHomeView view;
    public HomePresenterImp(IHomeView view, Context context) {
        this.context = context;
        mHomeService = new HomeService(this);
        this.view = view;
    }

    public void getLawyer() {
        showLoadingDialog();
        mHomeService.getLawyer();
//        mCompositeSubscription.add(subscription);
    }
    public void list() {
        showLoadingDialog();
//        mHomeService.getInfo(loc);
        Subscription subscription =
                mHomeService.list().doOnNext(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> remindDTOs) {
                        List<User> remindDTOs_ = remindDTOs;
                        showToast(remindDTOs_.get(0).getName());
//                        view.setList(remindDTOs);
                    }
                })
                .subscribe(newSubscriber(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> remindDTOs) {
                        Log.i(TAG, "getNotification---" + remindDTOs.toString());
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void success(final Object out) {
        System.out.println("*********"+((List<User>)out).get(0).getName());
        view.setList(((List<User>)out));
    }
}
