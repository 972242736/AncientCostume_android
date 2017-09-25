package com.mmf.ancientcostume.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.ViewPImgAdapter;
import com.mmf.ancientcostume.base.activity.BaseActivity;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.model.GoodsDetail;
import com.mmf.ancientcostume.model.GoodsDetailAndImg;
import com.mmf.ancientcostume.model.GoodsImg;
import com.mmf.ancientcostume.presenter.imp.detail.GoodsDetailPresenterImp;
import com.mmf.ancientcostume.view.home.IDetailView;
import com.mmf.ancientcostume.widget.PointView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMF on 2017-07-31.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class GoodsDetailActivity extends BaseActivity implements IDetailView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.lly_point)
    LinearLayout llyPoint;
    @BindView(R.id.vp_top_img)
    ViewPager vpTopImg;
    @BindView(R.id.sv_detail)
    ScrollView svDetail;
    @BindView(R.id.lly_change_bar)
    LinearLayout llyChangeBar;
    @BindView(R.id.tv_change_title)
    TextView tvChangeTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;

    private GoodsDetailPresenterImp presenter;
    private float SCROLL_HEIGHT;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);
        init();             //初始化数据
        setSvListener();    //设置ScrollView的滑动监听
        setVpListener();
        presenter = new GoodsDetailPresenterImp(this, this);
        presenter.getGoodsDetail(id);
    }

    private void init() {
        id = getIntent().getIntExtra("id", 1);
        SCROLL_HEIGHT = DipUtil.getWidth(this);
        DipUtil.setLinearLayout(DipUtil.getWidth(this), DipUtil.getWidth(this), svDetail);    //设置viewpager的宽高
        srlRefresh.setOnRefreshListener(this);
    }

    /**
     * 刷新的监听
     */
    @Override
    public void onRefresh() {
        srlRefresh.setRefreshing(true);
        presenter.getGoodsDetail(id);
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

    @Override
    public void onSuccess(Object object) {
        srlRefresh.setRefreshing(false);
        GoodsDetailAndImg goodsDetailAndImg = (GoodsDetailAndImg) object;
        List<GoodsImg> goodsImgList = goodsDetailAndImg.getGoodsImgList();
        ViewPImgAdapter adapter = new ViewPImgAdapter(this, goodsImgList, 1);
        vpTopImg.setAdapter(adapter);
        //底下点点的图片
        PointView.setPoint(this, llyPoint, goodsImgList.size());
        GoodsDetail goodsDetail = goodsDetailAndImg.getGoodsDetail();
    }

    /**
     * 设置ViewPager的监听
     */
    private void setVpListener() {
        vpTopImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }
}
