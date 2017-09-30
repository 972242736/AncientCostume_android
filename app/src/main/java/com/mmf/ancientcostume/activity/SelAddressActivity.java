package com.mmf.ancientcostume.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.goods.SelAddressAdapter;
import com.mmf.ancientcostume.base.activity.BaseTitleActivity;
import com.mmf.ancientcostume.base.adapter.BaseClickRecyclerAdapter;
import com.mmf.ancientcostume.model.Address;
import com.mmf.ancientcostume.presenter.imp.goods.SelAddressPresenterImp;
import com.mmf.ancientcostume.view.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by MMF on 2017-09-28.
 */

public class SelAddressActivity extends BaseTitleActivity<SelAddressPresenterImp, SelAddressActivity> implements BaseView<List<Address>> {
    @BindView(R.id.rv_sel_address)
    RecyclerView rvSelAddress;
    private SelAddressAdapter adapter;

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
                if(TextUtils.isEmpty(province)){
                    province = list.get(position).getName();
                    return;
                }
                if(TextUtils.isEmpty(city)){
                    city = list.get(position).getName();
                    return;
                }
                if(TextUtils.isEmpty(district)){
                    district = list.get(position).getName();
                    return;
                }
            }
        });

    }

    @Override
    protected void getData() {
        presenter.getProvinceList();
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
    }
}
