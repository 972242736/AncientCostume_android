package com.mmf.ancientcostume;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMF on 2017/6/19.
 */

public class WelcomeActivity extends FragmentActivity {
    @BindView(R.id.btn_enter)
    Button btnEnter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
