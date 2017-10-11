package com.mmf.ancientcostume.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.goods.SelAddressAdapter;
import com.mmf.ancientcostume.base.activity.BaseTitleActivity;
import com.mmf.ancientcostume.base.adapter.BaseClickRecyclerAdapter;
import com.mmf.ancientcostume.model.Address;
import com.mmf.ancientcostume.model.StaticData;
import com.mmf.ancientcostume.presenter.imp.goods.SelAddressPresenterImp;
import com.mmf.ancientcostume.view.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MMF on 2017-09-28.
 */

public class SelAddressActivity extends BaseTitleActivity<SelAddressPresenterImp, SelAddressActivity> implements BaseView<List<Address>> {
    @BindView(R.id.rv_sel_address)
    RecyclerView rvSelAddress;
    private SelAddressAdapter adapter;

    private List<Address> provinceList = new ArrayList<>();
    private List<Address> cityList = new ArrayList<>();
    private List<Address> list = new ArrayList<>();
    private String province;    //省份
    private String city;         //城市
    private String district;    //地区

    @Override
    public int getLayout() {
        return R.layout.activity_sel_address;
    }

    @Override
    public void init() {
        initAdapter();
    }

    /**
     * 初始化列表适配器
     */
    private void initAdapter() {
        rvSelAddress.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SelAddressAdapter(this);
        rvSelAddress.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseClickRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (TextUtils.isEmpty(province)) {
                    province = list.get(position).getName();
                    presenter.getCityList(list.get(position).getId());
                    return;
                }
                if (TextUtils.isEmpty(city)) {
                    city = list.get(position).getName();
                    presenter.getDistrictList(list.get(position).getId());
                    return;
                }
                if (TextUtils.isEmpty(district)) {
                    district = list.get(position).getName();
                    Intent mIntent = new Intent();
                    mIntent.putExtra("province",province);
                    mIntent.putExtra("city",city);
                    mIntent.putExtra("district",district);
                    // 设置结果，并进行传送
                    setResult(StaticData.RELEASE_SEL_ADDRESS, mIntent);
                    finish();
                    return;
                }
            }
        });

    }

    @Override
    protected void getData() {
        presenter.getProvinceList();
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                back();
                break;
        }
    }

    @Override
    protected SelAddressPresenterImp getPresenter() {
        return new SelAddressPresenterImp();
    }

    @Override
    public void onSuccess(List<Address> object) {
        list = object;
        adapter.setItems(object);
        adapter.notifyDataSetChanged();
        if (TextUtils.isEmpty(province)) {
            provinceList = object;
            return;
        }
        if (TextUtils.isEmpty(city)) {
            cityList = object;
            return;
        }
    }

    @Override
    public void onError(String object) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return false;
        }
        return true;
    }

    /**
     * 设置返回的监听
     */
    private void back() {
        //如果在选择省时返回则退出界面
        if (TextUtils.isEmpty(province)) {
            finish();
            return;
        }
        //如果在选择城市时返回刷新适配器为选择省
        if (TextUtils.isEmpty(city)) {
            province = "";
            list = provinceList;
            adapter.setItems(list);
            adapter.notifyDataSetChanged();
            return;
        }
        //如果在选择地区时返回刷新适配器为选择城市
        if (TextUtils.isEmpty(district)) {
            city = "";
            list = cityList;
            adapter.setItems(list);
            adapter.notifyDataSetChanged();
            return;
        }
    }
}
