package com.mmf.ancientcostume.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.common.utils.service.ViewUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMF on 2017-07-19.
 */

public class ImagePreviewActivity extends Activity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_sel_num)
    TextView tvSelNum;
    @BindView(R.id.tv_del)
    TextView tvDel;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.lyt_bar)
    LinearLayout lytBar;
    @BindView(R.id.pv_image_preview)
    ViewPager pvImagePreview;
    private List<String> pathList;
    private List<Boolean> imgSel = new ArrayList<>();
    private String type;            // 1：已选好的照片预览 2：相册进来预览
    private int selPosition;      //
    private List<Uri> listUri = new ArrayList<>();
    private boolean isShowBar = true;
    private int showPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);
        //获取数据
        getInitData();
    }

    private void getInitData() {
        type = getIntent().getStringExtra("type");
        selPosition = getIntent().getIntExtra("selPosition", 0);
        if (!TextUtils.isEmpty(type) && type.equals("1")) {
            pathList = getIntent().getStringArrayListExtra("imgPath");
            listUri = new ArrayList<>();
            for (String item : pathList) {
                listUri.add(ClippingPicture.getImageContentUri(this, new File(item.trim())));
            }
            //已选择的图片预览，可以执行删除操作
            tvDel.setVisibility(View.VISIBLE);
            tvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        for (String temp : pathList) {
            imgSel.add(false);
        }
        pvImagePreview.setAdapter(new SamplePagerAdapter());
        pvImagePreview.setCurrentItem(selPosition);
    }

    @OnClick({R.id.iv_back, R.id.tv_del, R.id.tv_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_del:
                break;
            case R.id.tv_complete:
                break;
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listUri.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View photoView = View.inflate(ImagePreviewActivity.this, R.layout.adapter_image_preview, null);
            ImageView ivImagePreview = (ImageView) photoView.findViewById(R.id.iv_image_preview);
            final LinearLayout lytBottom = (LinearLayout) photoView.findViewById(R.id.lyt_bottom);
            CheckBox cbSelImage = (CheckBox) photoView.findViewById(R.id.cb_sel_image);
            Picasso.with(ImagePreviewActivity.this).load(listUri.get(position)).into(ivImagePreview);
            if (imgSel.get(position)) {
                cbSelImage.setChecked(imgSel.get(position));
            }
            cbSelImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    imgSel.set(position, b);
                }
            });
            ivImagePreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isShowBar = !isShowBar;
                    if (isShowBar) {
                        lytBar.startAnimation(ViewUtil.viewShowActionTop());
                        lytBar.setVisibility(View.VISIBLE);
                        if (!TextUtils.isEmpty(type) && type.equals("2")) {
                            lytBottom.startAnimation(ViewUtil.viewShowActionBottom());
                            lytBottom.setVisibility(View.VISIBLE);
                        } else {
                            lytBottom.startAnimation(ViewUtil.viewHiddenActionBottom());
                            lytBottom.setVisibility(View.GONE);
                        }
                    } else {
                        lytBar.startAnimation(ViewUtil.viewHiddenActionTop());
                        lytBar.setVisibility(View.GONE);
                    }
                }
            });
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
