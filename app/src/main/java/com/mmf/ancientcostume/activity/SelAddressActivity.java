package com.mmf.ancientcostume.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.activity.BaseTitleActivity;
import com.mmf.ancientcostume.presenter.imp.goods.SelAddressPresenterImp;
import com.mmf.ancientcostume.view.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MMF on 2017-09-28.
 */

public class SelAddressActivity extends BaseTitleActivity<SelAddressPresenterImp, SelAddressActivity> implements BaseView {
    @BindView(R.id.rv_sel_address)
    RecyclerView rvSelAddress;

    @Override
    public int getLayout() {
        return R.layout.activity_sel_address;
    }

    @Override
    public void init() {

    }

    @Override
    protected void getData() {

    }

    @Override
    protected SelAddressPresenterImp getPresenter() {
        return new SelAddressPresenterImp();
    }

    @Override
    public void onSuccess(Object object) {

    }
}
