package com.mmf.ancientcostume.presenter.imp.user;

import android.content.Context;
import android.util.Log;

import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.presenter.IPresenter;
import com.mmf.ancientcostume.service.user.UserService;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;

/**
 * Created by MMF on 2017/7/7.
 */

public class UserPresenterImp extends BasePresenter implements IPresenter<Objects> {
    private UserService mUserService;
    private IHomeView view;
    public UserPresenterImp(IHomeView view, Context context) {
        this.context = context;
        mUserService = new UserService(this);
        this.view = view;
    }

    public void uploadPhoto(String data,Map<String, RequestBody> bodyMap) {
        showLoadingDialog();
        subscription = mUserService.uploadPhoto(data,bodyMap).doOnNext(new Action1<List<String>>() {
            @Override
            public void call(List<String> remindDTOs) {
                success(remindDTOs);
            }
        }).subscribe(newSubscriber(new Action1<List<String>>() {
            @Override
            public void call(List<String> remindDTOs) {
                Log.i(TAG, "getNotification---" + remindDTOs.toString());
            }
        }));
        mCompositeSubscription.add(subscription);
    }

    public void uploadPhoto(Map<String, RequestBody> bodyMap) {
        showLoadingDialog();
        subscription = mUserService.uploadPhoto(bodyMap).doOnNext(new Action1<List<String>>() {
            @Override
            public void call(List<String> remindDTOs) {
                success(remindDTOs);
            }
        }).subscribe(newSubscriber(new Action1<List<String>>() {
            @Override
            public void call(List<String> remindDTOs) {
                Log.i(TAG, "getNotification---" + remindDTOs.toString());
            }
        }));
        mCompositeSubscription.add(subscription);
    }


    @Override
    public void success(Object out) {

    }
}

