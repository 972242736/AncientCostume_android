package com.mmf.ancientcostume.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.home.ReleaseInfoImageAdapter;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.other.zhy.imageloader.SelPhotoActivity;
import com.mmf.ancientcostume.widget.SpaceItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by MMF on 2017-07-13.
 * 添加发布信息界面
 */

public class ReleaseFragment extends Fragment {

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
    private View view;
    private List<String> pathList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_release_info, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_sel)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SelPhotoActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取选择的图片的真实路劲
        String imgUrls = data.getStringExtra("imgUrls");
        String[] tempArray = imgUrls.substring(1, imgUrls.length() - 1).split(",");
        pathList = Arrays.asList(tempArray);
        //设置显示的适配器
        final ReleaseInfoImageAdapter adapter = new ReleaseInfoImageAdapter(getActivity());
        adapter.setItems(pathList);
        //将RecyclerView设置成gridview的样式 每行显示4个
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 4);
        rvPreviewImg.setLayoutManager(mgr);
        int space = DipUtil.dip2px(getActivity(), 2);
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
    }

}
