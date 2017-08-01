package com.mmf.ancientcostume.base.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmf.ancientcostume.MainActivity;
import com.mmf.ancientcostume.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by MMF on 2017/6/19.
 */

public class BaseActivity extends FragmentActivity {

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
    protected Unbinder unbinder;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HomeFragment firstFragment = new HomeFragment();
//        firstFragment.setArguments(getIntent().getExtras());
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_container, firstFragment).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (getBaseContext() instanceof MainActivity && !isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
            System.exit(0);
        } else {
            if (getBaseContext() instanceof MainActivity) {
                isExit = true;
                finish();
            } else {
                isExit = true;
                System.exit(0);
            }
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}