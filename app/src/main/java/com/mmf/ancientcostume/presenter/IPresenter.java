package com.mmf.ancientcostume.presenter;

/**
 * Created by MMF on 2017-07-04.
 */

public interface IPresenter<T> {
   <T> void success(T out);

    void showToast(String content);
}
