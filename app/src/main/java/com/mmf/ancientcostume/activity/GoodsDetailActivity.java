package com.mmf.ancientcostume.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.ViewPImgAdapter;
import com.mmf.ancientcostume.base.activity.BaseTitleActivity;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.model.GoodsDetail;
import com.mmf.ancientcostume.model.GoodsDetailAndImg;
import com.mmf.ancientcostume.model.GoodsImg;
import com.mmf.ancientcostume.presenter.imp.goods.GoodsDetailPresenterImp;
import com.mmf.ancientcostume.view.home.IDetailView;
import com.mmf.ancientcostume.widget.PointView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MMF on 2017-07-31.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class GoodsDetailActivity extends BaseTitleActivity<GoodsDetailPresenterImp, GoodsDetailActivity> implements IDetailView{
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
//    @BindView(R.id.srl_refresh)
//    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.rly_top_img)
    RelativeLayout rlyTopImg;
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_rental)
    TextView tvRental;
    @BindView(R.id.tv_deposit)
    TextView tvDeposit;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rv_detail_img)
    RecyclerView rvDetailImg;
    @BindView(R.id.iv_change_back)
    ImageView ivChangeBack;
    @BindView(R.id.iv_change_right)
    ImageView ivChangeRight;
    @BindView(R.id.tv_change_goods)
    TextView tvChangeGoods;
    @BindView(R.id.tv_change_comment)
    TextView tvChangeComment;
    @BindView(R.id.tv_change_detail)
    TextView tvChangeDetail;


    private float SCROLL_HEIGHT;    //屏幕的高度
    private int id;


    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    public void init() {
        id = getIntent().getIntExtra("id", 1);
        SCROLL_HEIGHT = DipUtil.getWidth(this);
        DipUtil.setLinearLayout(DipUtil.getWidth(this), DipUtil.getWidth(this), rlyTopImg);    //设置viewpager的宽高
//        DipUtil.setLinearLayout(DipUtil.getWidth(this), DipUtil.getWidth(this), svDetail);    //设置viewpager的宽高
//        srlRefresh.setOnRefreshListener(this);
        setSvListener();    //设置ScrollView的滑动监听
        setVpListener();
    }

    @Override
    protected void getData() {
        presenter.getGoodsDetail(id);
    }

    /**
     * 刷新的监听
     */
//    @Override
//    public void onRefresh() {
//        srlRefresh.setRefreshing(true);
//        getData();
//    }

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
    protected GoodsDetailPresenterImp getPresenter() {
        return new GoodsDetailPresenterImp();
    }


    @Override
    public void onSuccess(Object object) {
//        srlRefresh.setRefreshing(false);
        GoodsDetailAndImg goodsDetailAndImg = (GoodsDetailAndImg) object;
        List<GoodsImg> goodsTopImgList = goodsDetailAndImg.getGoodsTopImgList();
        ViewPImgAdapter<GoodsImg> adapter = new ViewPImgAdapter(this, goodsTopImgList, 2);
        vpTopImg.setAdapter(adapter);
        //底下点点的图片
        PointView.setPoint(this, llyPoint, goodsTopImgList.size());
        setGoodsInfo(goodsDetailAndImg.getGoodsDetail());
    }

    private void setGoodsInfo(GoodsDetail goodsDetail) {
        if (goodsDetail != null) {
            tvGoodsTitle.setText(goodsDetail.getTitle());
            tvDescribe.setText(goodsDetail.getDescribe());
            tvRental.setText("租金："+goodsDetail.getRental());
            tvDeposit.setText("押金："+goodsDetail.getDeposit());
            tvCollect.setText("收藏："+goodsDetail.getCollectNum());
            tvAddress.setText(goodsDetail.getProvince().substring(0,goodsDetail.getProvince().indexOf("省"))+" "+goodsDetail.getCity().substring(0,goodsDetail.getCity().indexOf("市")));
        }
    }

    @Override
    public void onError(String object) {
//        srlRefresh.setRefreshing(false);
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
