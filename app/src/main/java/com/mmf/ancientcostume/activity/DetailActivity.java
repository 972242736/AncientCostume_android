package com.mmf.ancientcostume.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.base.activity.BaseActivity;
import com.mmf.ancientcostume.common.utils.DipUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMF on 2017-07-31.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class DetailActivity extends BaseActivity {
    private float SCROLL_HEIGHT = 500f;
    @BindView(R.id.vp_top_img)
    ViewPager vpTopImg;
    @BindView(R.id.sv_detail)
    ScrollView svDetail;
    @BindView(R.id.lly_change_bar)
    LinearLayout llyChangeBar;
    @BindView(R.id.tv_change_title)
    TextView tvChangeTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);
        DipUtil.setLinearLayout(DipUtil.getWidth(this), DipUtil.getWidth(this), svDetail);    //设置viewpager的宽高
        setSvListener();    //设置ScrollView的滑动监听
    }

    /**
     * 设置ScrollView的滑动监听
     */
    private void setSvListener() {
        svDetail.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i > SCROLL_HEIGHT) {
                    llyChangeBar.setAlpha(1.0F);
                    llyOriginallyBar.setAlpha(0F);
                    llyChangeBar.setVisibility(View.VISIBLE);
                    llyOriginallyBar.setVisibility(View.GONE);
                } else {
                    if (i1 == 0) {
                        llyChangeBar.setAlpha(0F);
                        llyOriginallyBar.setAlpha(1.0F);
                        llyChangeBar.setVisibility(View.GONE);
                        llyOriginallyBar.setVisibility(View.VISIBLE);
                    } else {
                        llyChangeBar.setVisibility(View.VISIBLE);
                        llyOriginallyBar.setVisibility(View.VISIBLE);
                        float alpha = i1 / SCROLL_HEIGHT;
                        llyChangeBar.setAlpha(alpha);
                        llyOriginallyBar.setAlpha(1 - alpha);
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_change_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                Toast.makeText(this, "q123", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_change_title:
                Toast.makeText(this, "ERTT", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
