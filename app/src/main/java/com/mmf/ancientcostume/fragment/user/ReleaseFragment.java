package com.mmf.ancientcostume.fragment.user;

import android.content.Intent;
import android.net.Uri;
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
import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.common.utils.DipUtil;
import com.mmf.ancientcostume.other.zhy.imageloader.SelPhotoActivity;
import com.mmf.ancientcostume.widget.SpaceItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by MMF on 2017-07-13.
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
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_release_info, null);
        unbinder = ButterKnife.bind(this, view);
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
        String imgUrls = data.getStringExtra("imgUrls");
        String[] tempArray = imgUrls.substring(1, imgUrls.length() - 1).split(",");
        List<String> pathList = Arrays.asList(tempArray);
        List<Uri> listUri = new ArrayList<>();
        for (String item : pathList) {
            listUri.add(ClippingPicture.getImageContentUri(getActivity(), new File(item.trim())));
        }
        ReleaseInfoImageAdapter adapter = new ReleaseInfoImageAdapter(getActivity());
        adapter.setItems(listUri);
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 4);
        rvPreviewImg.setLayoutManager(mgr);
        int space = DipUtil.dip2px(getActivity(), 2);
        rvPreviewImg.addItemDecoration(new SpaceItemDecoration(space, 4));
        rvPreviewImg.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
