package com.mmf.ancientcostume.view;

/**
 * Created by MMF on 2017-08-14.
 */

public interface BaseView<T> {
    void onSuccess(T object);
    void onError(String object);
}
