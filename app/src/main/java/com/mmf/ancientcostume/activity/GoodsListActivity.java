package com.mmf.ancientcostume.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.goods.GoodsListAdapter;
import com.mmf.ancientcostume.base.activity.BaseTitleActivity;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.model.GoodsDetail;
import com.mmf.ancientcostume.presenter.imp.goods.GoodsListPresenterImp;
import com.mmf.ancientcostume.view.BaseView;
import com.mmf.ancientcostume.widget.GridSpacingItemDecoration;
import com.mmf.ancientcostume.widget.LoadRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MMF on 2017-09-11.
 */

public class GoodsListActivity extends BaseTitleActivity<GoodsListPresenterImp, GoodsListActivity> implements BaseView<List<GoodsDetail>>, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.rv_goods_list)
    LoadRecyclerView rvGoodsList;

    private final int pageSize = 12;    //设置每页加载的数据条数
    private int pageNo = 1;              //记录加载的页数
    private List<GoodsDetail> list = new ArrayList<>();
    private GoodsListAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_goods_list;
    }

    @Override
    public void init() {
        //初始化列表适配器
        initAdapter();
        srlRefresh.setOnRefreshListener(this);
        //判断是否滑动到最后一条记录，是就加载数据
        rvGoodsList.setLoadingDataListener(new LoadRecyclerView.LoadingDataListener() {
            @Override
            public void onLoadMore() {
                pageNo++;
                getData();
            }
        });
    }

    @Override
    protected void getData() {
        presenter.getGoodsList(pageNo, pageSize);
    }

    /**
     * 初始化列表适配器
     */
    private void initAdapter() {
        //设置layoutmanager,设置为纵向一行两个item的列表
        rvGoodsList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvGoodsList.addItemDecoration(new GridSpacingItemDecoration(DipUtil.dip2px(this, 2)));
        adapter = new GoodsListAdapter(this);
        adapter.setItems(list);
        rvGoodsList.setAdapter(adapter);
    }

    @Override
    protected GoodsListPresenterImp getPresenter() {
        return new GoodsListPresenterImp();
    }


    @Override
    public void onRefresh() {
        pageNo = 1;
        srlRefresh.setRefreshing(true);
        getData();
    }

    @Override
    public void onSuccess(List<GoodsDetail> object) {
        srlRefresh.setRefreshing(false);
        adapter.setItems(object);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
