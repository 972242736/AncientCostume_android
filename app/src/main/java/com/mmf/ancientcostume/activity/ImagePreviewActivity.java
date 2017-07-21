package com.mmf.ancientcostume.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.common.utils.service.ViewUtil;
import com.mmf.ancientcostume.widget.IImagePreview;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMF on 2017-07-19.
 * 图片预览界面
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
    @BindView(R.id.rl_preview)
    RelativeLayout rlPreview;
    private List<String> pathList;              //预览的所有图片的真实路径
    private List<String> mSelImage = new ArrayList<>();    //已经选择的图片路径
    private List<Boolean> isSelImg = new ArrayList<>();    //是否选择图片，在选择图片时预览用到
    private String type;                        // 1：已选好的照片预览 2：相册进来预览
    private int selPosition;                  //点击进入预览界面的图片位置
    private List<Uri> listUri = new ArrayList<>();         //预览的所有图片的uri
    private boolean isShowBar = true;        //是否显示头部的bar
    private int showPosition;                 //当前显示的图片的位置
    private IImagePreview iPreview;            //对图片操作的监听

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);
        rlPreview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //获取数据
        getInitData();
    }

    private void getInitData() {
        type = getIntent().getStringExtra("type");
        selPosition = getIntent().getIntExtra("selPosition", 0);
        pathList = getIntent().getStringArrayListExtra("imgPath");
//         = getIntent().getStringArrayListExtra("mSelImage");
        String imgUrls = getIntent().getStringExtra("mSelImage");
        String[] tempArray = imgUrls.substring(1, imgUrls.length() - 1).split(",");
        List<String> tempSelImage = Arrays.asList(tempArray);
        for (String item : tempSelImage) {
            if (!TextUtils.isEmpty(item)) {
                mSelImage.add(item.trim());
            }
        }
        listUri = new ArrayList<>();
        for (String item : pathList) {
            listUri.add(ClippingPicture.getImageContentUri(this, new File(item.trim())));
        }
        //1：表示从已选择的图片进来的预览 否则便是从选择图片的进入的预览
        if (!TextUtils.isEmpty(type) && type.equals("1")) {
            //已选择的图片预览，可以执行删除操作
            tvDel.setVisibility(View.VISIBLE);
            tvComplete.setVisibility(View.GONE);
        } else {
            tvDel.setVisibility(View.GONE);
            tvComplete.setVisibility(View.VISIBLE);
        }
        for (String temp : pathList) {
            // 已经选择过该图片
            if (mSelImage.contains(temp)) {
                isSelImg.add(true);
            }
            // 未选择该图片
            else {
                isSelImg.add(false);
            }

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
//                iPreview.delete(showPosition,pathList.get(showPosition));
                mSelImage.remove(pathList.get(showPosition));
                break;
            case R.id.tv_complete:
//                iPreview.select(showPosition,pathList.get(showPosition));
                Intent mIntent = new Intent();
                mIntent.putStringArrayListExtra("mSelImage", (ArrayList<String>) mSelImage);
                // 设置结果，并进行传送
                this.setResult(1, mIntent);
                finish();
                break;
        }
    }

    /**
     * 设置预览图片的操作监听
     *
     * @param iPreview
     */
    public void setIPreview(IImagePreview iPreview) {
        this.iPreview = iPreview;
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listUri.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            showPosition = position;
            tvSelNum.setText(position + "/" + listUri.size());
            View photoView = View.inflate(ImagePreviewActivity.this, R.layout.adapter_image_preview, null);
            ImageView ivImagePreview = (ImageView) photoView.findViewById(R.id.iv_image_preview);
            final LinearLayout lytBottom = (LinearLayout) photoView.findViewById(R.id.lyt_bottom);
            CheckBox cbSelImage = (CheckBox) photoView.findViewById(R.id.cb_sel_image);
            //加载图片
            Picasso.with(ImagePreviewActivity.this).load(listUri.get(position)).into(ivImagePreview);

            cbSelImage.setChecked(isSelImg.get(position));
            if (isShowBar && !TextUtils.isEmpty(type) && type.equals("2")) {
                lytBottom.setVisibility(View.VISIBLE);
            }
            //是否选择图片
            cbSelImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    isSelImg.set(position, b);
                    if (b) {
//                        iPreview.select(showPosition,pathList.get(position));
                        mSelImage.add(pathList.get(position));
                    } else {
                        mSelImage.remove(pathList.get(position));
//                        iPreview.delete(showPosition,pathList.get(position));
                    }
                }
            });

            //点击图片显示或隐藏上下的bar
            ivImagePreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isShowBar = !isShowBar;
                    if (isShowBar) {
                        lytBar.startAnimation(ViewUtil.viewShowActionTop());
                        lytBar.setVisibility(View.VISIBLE);
//                        rlPreview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//                        rlPreview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                        if (!TextUtils.isEmpty(type) && type.equals("2")) {
                            lytBottom.startAnimation(ViewUtil.viewShowActionBottom());
                            lytBottom.setVisibility(View.VISIBLE);
                        }
                    } else {
                        lytBar.startAnimation(ViewUtil.viewHiddenActionTop());
                        lytBar.setVisibility(View.GONE);
//                        rlPreview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                        rlPreview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                        if (!TextUtils.isEmpty(type) && type.equals("2")) {
                            lytBottom.startAnimation(ViewUtil.viewHiddenActionBottom());
                            lytBottom.setVisibility(View.GONE);
                        }
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
