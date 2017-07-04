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
    @BindView(R.id.iv_point1)
    ImageView ivPoint1;
    @BindView(R.id.iv_point2)
    ImageView ivPoint2;
    @BindView(R.id.iv_point3)
    ImageView ivPoint3;
    @BindView(R.id.iv_point4)
    ImageView ivPoint4;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    List<ImageView> ivList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        ImageView img1 = new ImageView(this);
        img1.setImageResource(R.drawable.img1);
        ImageView img2 = new ImageView(this);
        img2.setImageResource(R.drawable.img2);
        ImageView img3 = new ImageView(this);
        img3.setImageResource(R.drawable.img3);
        ImageView img4 = new ImageView(this);
        img4.setImageResource(R.drawable.img4);

        img1.setScaleType(ImageView.ScaleType.FIT_XY);
        img2.setScaleType(ImageView.ScaleType.FIT_XY);
        img3.setScaleType(ImageView.ScaleType.FIT_XY);
        img4.setScaleType(ImageView.ScaleType.FIT_XY);

        ivList = new ArrayList<>();
        ivList.add(img1);
        ivList.add(img2);
        ivList.add(img3);
        ivList.add(img4);
        ItemPagerAdapter adapter = new ItemPagerAdapter(ivList);
        vpWelcome.setAdapter(adapter);
        setVpListener();
    }

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

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
    private void setSelection(int position){
        clearPoint();
        switch (position){
            case 0:
                ivPoint1.setImageResource(R.drawable.sel_point);
                btnEnter.setVisibility(View.GONE);
                break;
            case 1:
                ivPoint2.setImageResource(R.drawable.sel_point);
                btnEnter.setVisibility(View.GONE);
                break;
            case 2:
                ivPoint3.setImageResource(R.drawable.sel_point);
                btnEnter.setVisibility(View.GONE);
                break;
            case 3:
                ivPoint4.setImageResource(R.drawable.sel_point);
                btnEnter.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void clearPoint(){
        ivPoint1.setImageResource(R.drawable.point);
        ivPoint2.setImageResource(R.drawable.point);
        ivPoint3.setImageResource(R.drawable.point);
        ivPoint4.setImageResource(R.drawable.point);
    }

    class ItemPagerAdapter extends PagerAdapter {
        List<ImageView> list;

        public ItemPagerAdapter(List<ImageView> views) {
            list = views;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position), 0);
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
