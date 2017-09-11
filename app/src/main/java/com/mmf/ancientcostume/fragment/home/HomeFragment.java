package com.mmf.ancientcostume.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.home.LawyerAdapter;
import com.mmf.ancientcostume.presenter.imp.home.HomePresenterImp;
import com.mmf.ancientcostume.view.home.IHomeView;

import java.util.List;

/**
 * Created by MMF
 * date 2016/9/24
 * Description:
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IHomeView {
    private View view;
    private SwipeRefreshLayout swipeRefreshWidget;
    private RecyclerView rvLawyer;
    private LawyerAdapter adapter;
    private HomePresenterImp presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        initView();
        getLawyer();
        return view;
    }

    private void initView() {
        swipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        rvLawyer = (RecyclerView) view.findViewById(R.id.rv_lawyer);
        rvLawyer.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvLawyer.setLayoutManager(layoutManager);

        swipeRefreshWidget.setOnRefreshListener(this);
        adapter = new LawyerAdapter(getActivity());
        rvLawyer.setAdapter(adapter);
        presenter = new HomePresenterImp(this,getActivity());
    }

    private void getLawyer() {
//        presenter.getLawyer();
        presenter.list();
    }

    @Override
    public void onRefresh() {
        getLawyer();
        swipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void setList(List list) {
        Toast.makeText(getActivity(),list.toString(),Toast.LENGTH_SHORT).show();
//        adapter.setItems(list);
    }

}
