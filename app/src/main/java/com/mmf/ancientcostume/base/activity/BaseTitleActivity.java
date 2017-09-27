package com.mmf.ancientcostume.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.presenter.BasePresenter;
import com.mmf.ancientcostume.view.home.BaseView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by MMF on 2017/6/19.
 */

public abstract class BaseTitleActivity<T extends BasePresenter,V extends BaseView> extends BaseActivity {

    @BindView(R.id.iv_back)
    protected ImageView ivBack;
    @BindView(R.id.tv_title)
    protected TextView tvTitle;
    @BindView(R.id.iv_right)
    protected ImageView ivRight;
    @BindView(R.id.lly_originally_bar)
    protected RelativeLayout llyOriginallyBar;
    @BindView(R.id.tv_right)
    protected TextView tvRight;

    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        presenter.attach((V)this,this);
        getData();
//        HomeFragment firstFragment = new HomeFragment();
//        firstFragment.setArguments(getIntent().getExtras());
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_container, firstFragment).commit();
    }


    @OnClick({R.id.iv_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.iv_right:
                break;
        }
    }

    protected abstract T getPresenter();
    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttach();
    }
}