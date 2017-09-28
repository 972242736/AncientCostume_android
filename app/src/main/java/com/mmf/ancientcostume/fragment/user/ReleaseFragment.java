package com.mmf.ancientcostume.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.mmf.ancientcostume.MyApplication;
import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.activity.ImagePreviewActivity;
import com.mmf.ancientcostume.adapter.home.ReleaseInfoImageAdapter;
import com.mmf.ancientcostume.base.fragment.BaseFragment;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.other.zhy.imageloader.MyAdapter;
import com.mmf.ancientcostume.other.zhy.imageloader.SelPhotoActivity;
import com.mmf.ancientcostume.presenter.imp.release.ReleasePresenterImp;
import com.mmf.ancientcostume.view.BaseView;
import com.mmf.ancientcostume.widget.SpaceItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by MMF on 2017-07-13.
 * 添加发布信息界面
 */

public class ReleaseFragment extends BaseFragment<ReleasePresenterImp, ReleaseFragment> implements BaseView {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lyt_address)
    LinearLayout lytAddress;
    @BindView(R.id.ed_detail_address)
    EditText edDetailAddress;
    @BindView(R.id.tv_sel)
    TextView tvSel;
    @BindView(R.id.rv_preview_img)
    RecyclerView rvPreviewImg;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.ed_rental)
    EditText edRental;
    @BindView(R.id.ed_deposit)
    EditText edDeposit;
    @BindView(R.id.rv_introduce_img)
    RecyclerView rvIntroduceImg;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    Unbinder unbinder;
    private int TOP_TYPE = 1;
    private int BOTTOM_TYPE = 2;
    private List<String> imgUrls = new ArrayList<>();
    private ReleaseInfoImageAdapter adapter;

    private String province;    //省份
    private String city;         //城市
    private String district;    //地区
    private String street;       //街道

    public int getLayout() {
        return R.layout.fragment_release_info;
    }

    public void init() {
        setAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        MyApplication.getInstance().setReleaseFragment(this);
        return view;
    }

    public void setAddress() {
        if (MyApplication.getInstance().getCurrlocation() != null) {
            BDLocation bdLocation= MyApplication.getInstance().getCurrlocation();
            province = bdLocation.getProvince();
            city = bdLocation.getCity();
            district = bdLocation.getDistrict();
            street = bdLocation.getStreet();
            tvAddress.setText(province+city+district);
        }
    }

    @Override
    protected ReleasePresenterImp getPresenter() {
        return new ReleasePresenterImp();
    }

    @OnClick({R.id.tv_sel, R.id.tv_release, R.id.lyt_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sel:
                Intent intent = new Intent(getActivity(), SelPhotoActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_release:
                if (presenter.check(getData())) {
                    presenter.insertDetail(getImgRequestBody(imgUrls, TOP_TYPE), getData());
                }
                break;
            case R.id.lyt_address:
                if (presenter.check(getData())) {
                    presenter.insertDetail(getImgRequestBody(imgUrls, TOP_TYPE), getData());
                }
                break;
        }
    }

    /**
     * 获取发布的界面信息
     *
     * @return
     */
    private Map<String, Object> getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", etTitle.getText().toString());
        map.put("describe", etDescribe.getText().toString());
        map.put("rental", edRental.getText().toString());
        map.put("deposit", edDeposit.getText().toString());
        return map;
    }

    /**
     * 获取发布的图片信息
     *
     * @return
     */
    private Map<String, RequestBody> getImgRequestBody(List<String> path, int type) {
        Map<String, RequestBody> bodyMap = new HashMap<String, RequestBody>();
        if (path.size() > 0) {
            for (int i = 0; i < path.size(); i++) {
                File file = new File(path.get(i).trim());
                //如果是顶部的添加图片那么设置第一张为封面的图片
                if (type == TOP_TYPE && i == 0) {
                    bodyMap.put("0" + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                } else {
                    bodyMap.put(type + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                }
            }
        }
        return bodyMap;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取选择的图片的真实路劲
        imgUrls = data.getStringArrayListExtra("imgUrls");
        if (resultCode == 1) {
            adapter.setItems(imgUrls);
            adapter.notifyDataSetChanged();
        }
    }

    private void setAdapter() {
        //设置显示的适配器
        adapter = new ReleaseInfoImageAdapter(getActivity());
        adapter.setItems(imgUrls);
        //将RecyclerView设置成gridview的样式 每行显示4个
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 4);
        rvPreviewImg.setLayoutManager(mgr);
        int space = DipUtil.dip2px(getActivity(), 4);
        //设置RecyclerView每个item的间距
        rvPreviewImg.addItemDecoration(new SpaceItemDecoration(space, 4));
        //设置RecyclerView的滑动监听（感觉还是很卡，优化还不行）
        rvPreviewImg.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // 查看源码可知State有三种状态：SCROLL_STATE_IDLE（静止）、SCROLL_STATE_DRAGGING（上升）、SCROLL_STATE_SETTLING（下落）
                if (newState == SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源，极大提升流畅度
                    adapter.setScrolling(false);
                    adapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
                } else
                    adapter.setScrolling(true);
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        rvPreviewImg.setAdapter(adapter);
        adapter.setjPAListener(new MyAdapter.JumpPreviewActivityListener() {
            @Override
            public void jump(int position, List<String> itemList) {
                Intent intent = new Intent(getActivity(), ImagePreviewActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("selPosition", position);
                intent.putStringArrayListExtra("imgUrls", (ArrayList<String>) imgUrls);
                intent.putStringArrayListExtra("imgPath", (ArrayList<String>) itemList);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
