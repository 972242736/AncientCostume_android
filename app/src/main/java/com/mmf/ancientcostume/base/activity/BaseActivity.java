package com.mmf.ancientcostume.base.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.fragment.home.HomeFragment;

/**
 * Created by MMF on 2017/6/19.
 */

public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        HomeFragment firstFragment = new HomeFragment();
        firstFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
    }
}