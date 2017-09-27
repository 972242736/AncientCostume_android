package com.mmf.ancientcostume.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.activity.BaseActivity;
import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.view.home.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by MMF on 2017-07-19.
 */

public abstract class BaseFragment<T extends BasePresenter, V extends BaseView> extends Fragment {
    protected T presenter;
    protected View view;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(), null);
        ButterKnife.bind(this, view);
        init();
        presenter = getPresenter();
        presenter.attach((V) this,getActivity());
        return view;
    }

    public abstract int getLayout();

    public abstract void init();

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.deAttach();
        unbinder.unbind();
    }

    protected abstract T getPresenter();

}
