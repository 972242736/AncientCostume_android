package com.mmf.ancientcostume.base.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mmf.ancientcostume.common.utils.service.RetrofitUtil;
import com.mmf.ancientcostume.widget.DialogLoading;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class BasePresenter {

    private DialogLoading loading;
    protected Toast mToast = null;
    protected Context context;

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
