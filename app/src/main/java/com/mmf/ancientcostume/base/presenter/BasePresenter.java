package com.mmf.ancientcostume.base.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mmf.ancientcostume.common.utils.service.RetrofitUtil;
import com.mmf.ancientcostume.widget.DialogLoading;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class BasePresenter<T> {

    private DialogLoading loading;
    protected Toast mToast = null;
    protected Context context;
    protected final String TAG = "RxJava";
    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    protected Subscription subscription;

    /**
     * 创建观察者
     *
     * @param onNext
     * @param <T>
     * @return
     */
    protected <T> Subscriber newSubscriber(final Action1<? super T> onNext) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                showToast("");
            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                if (e instanceof RetrofitUtil.APIException) {
                    RetrofitUtil.APIException exception = (RetrofitUtil.APIException) e;
                    showToast(exception.message);
                } else if (e instanceof SocketTimeoutException) {
                    showToast(e.getMessage());
                } else if (e instanceof ConnectException) {
                    showToast(e.getMessage());
                }
                Log.e(TAG, String.valueOf(e.getMessage()));
                e.printStackTrace();
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    hideLoadingDialog();
                    onNext.call(t);
                }
            }
        };
    }


    /**
     * 显示一个Toast信息
     *
     * @param content
     */
    public void showToast(String content) {
        hideLoadingDialog();
        if (!TextUtils.isEmpty(content)) {
            if (mToast == null) {
                mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(content);
            }
            mToast.show();
        }
    }

    protected void showLoadingDialog() {
        if (loading == null) {
            loading = new DialogLoading(context);
        }
        loading.show();
    }

    protected void hideLoadingDialog() {
        if (loading != null) {
            loading.dismiss();
        }

    }

}
