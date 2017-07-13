package com.mmf.ancientcostume.fragment.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.adapter.home.ReleaseInfoImageAdapter;
import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.other.zhy.imageloader.SelPhotoActivity;
import com.mmf.ancientcostume.widget.SpaceItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMF on 2017-07-13.
 */

public class ReleaseActivity extends Activity {
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.ed_rental)
    EditText edRental;
    @BindView(R.id.ed_deposit)
    EditText edDeposit;
    @BindView(R.id.tv_sel)
    TextView tvSel;
    @BindView(R.id.rv_preview_img)
    RecyclerView rvPreviewImg;
    @BindView(R.id.rv_introduce_img)
    RecyclerView rvIntroduceImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_info);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_sel)
    public void onViewClicked() {
        Intent intent = new Intent(this, SelPhotoActivity.class);
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
            listUri.add(ClippingPicture.getImageContentUri(this, new File(item.trim())));
        }
        ReleaseInfoImageAdapter adapter = new ReleaseInfoImageAdapter(this);
        adapter.setItems(listUri);
        GridLayoutManager mgr=new GridLayoutManager(this,4);
        rvPreviewImg.setLayoutManager(mgr);
        int space = getResources().getDimensionPixelSize(R.dimen.public_space_value_2);
        rvPreviewImg.addItemDecoration(new SpaceItemDecoration(space,4));
        rvPreviewImg.setAdapter(adapter);
    }


}
