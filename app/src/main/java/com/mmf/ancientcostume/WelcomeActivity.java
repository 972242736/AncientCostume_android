package com.mmf.ancientcostume;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmf.ancientcostume.adapter.ViewPImgAdapter;
import com.mmf.ancientcostume.widget.PointView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMF on 2017/6/19.
 */

public class WelcomeActivity extends FragmentActivity {
    @BindView(R.id.vp_welcome)
    ViewPager vpWelcome;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    @BindView(R.id.lly_point)
    LinearLayout llyPoint;
    List<Integer> dbList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        setPagerData();     //设置ViewPager的适配器
        setVpListener();    //设置ViewPager的监听
    }

    /**
     * 设置ViewPager的适配器
     */
    private void setPagerData() {
        dbList.add(R.drawable.img1);
        dbList.add(R.drawable.img2);
        dbList.add(R.drawable.img3);
        dbList.add(R.drawable.img4);
        ViewPImgAdapter adapter = new ViewPImgAdapter(this, dbList, 1);
        vpWelcome.setAdapter(adapter);
        //底下点点的图片
        PointView.setPoint(this, llyPoint, dbList.size());
    }

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * 设置ViewPager的监听
     */
    private void setVpListener() {
        vpWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setSelection(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 修改point的图片
     *
     * @param position
     */
    private void setSelection(int position) {
        PointView.resetPoint(llyPoint);
        ((ImageView) llyPoint.getChildAt(position)).setImageResource(R.drawable.sel_point);
        if (position == llyPoint.getChildCount() - 1) {
            btnEnter.setVisibility(View.VISIBLE);
        } else {
            btnEnter.setVisibility(View.GONE);
        }
    }
}
