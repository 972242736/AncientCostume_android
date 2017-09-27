package com.mmf.ancientcostume.base.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.mmf.ancientcostume.MainActivity;
import com.mmf.ancientcostume.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MMF on 2017-09-26.
 */

public abstract class BaseActivity extends FragmentActivity {
    protected Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        init();
    }
    public abstract int getLayout();

    public abstract void init();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}