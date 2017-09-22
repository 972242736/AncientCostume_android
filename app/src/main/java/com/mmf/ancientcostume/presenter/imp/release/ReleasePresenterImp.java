package com.mmf.ancientcostume.presenter.imp.release;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.service.home.HomeService;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MMF on 2017-07-24.
 */

public class ReleasePresenterImp extends BasePresenter {
    private HomeService mHomeService;
//    private IHomeView view;

    public ReleasePresenterImp(Context context) {
        this.context = context;
        mHomeService = new HomeService();
//        this.view = view;
    }

    public boolean check(Map<String, Object> info) {
        if (TextUtils.isEmpty(info.get("title").toString())) {
            showToast("title");
            return false;
        }
        if (TextUtils.isEmpty(info.get("describe").toString())) {
            showToast("describe");
            return false;
        }
        if (TextUtils.isEmpty(info.get("rental").toString())) {
            showToast("rental");
            return false;
        }
        if (TextUtils.isEmpty(info.get("deposit").toString())) {
            showToast("deposit");
            return false;
        }
        return true;
    }

    public void insertDetail(Map<String, RequestBody> bodyMap, Map<String, Object> info) {
        showLoadingDialog();
        Subscription subscription =
                mHomeService.insertDetail(bodyMap, info).doOnNext(new Action1<String>() {
                    @Override
                    public void call(String remindDTOs) {
                        showToast(remindDTOs);
                    }
                }).subscribe(newSubscriber(new Action1<String>() {
                    @Override
                    public void call(String remindDTOs) {
                        Log.i(TAG, "getNotification---" + remindDTOs.toString());
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
