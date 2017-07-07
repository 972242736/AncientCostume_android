package com.mmf.ancientcostume.presenter.imp.user;

import android.content.Context;

import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.presenter.IPresenter;
import com.mmf.ancientcostume.service.user.UserService;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.Map;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    public void uploadPhoto(String data,Map<String, MultipartBody.Part> bodyMap) {
        showLoadingDialog();
        mUserService.uploadPhoto(data,bodyMap);
//        mCompositeSubscription.add(subscription);
    }


    @Override
    public void success(Object out) {

    }
}

