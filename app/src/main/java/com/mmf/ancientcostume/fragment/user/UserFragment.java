package com.mmf.ancientcostume.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.other.zhy.imageloader.SelPhotoActivity;
import com.mmf.ancientcostume.presenter.imp.user.UserPresenterImp;
import com.mmf.ancientcostume.view.home.IHomeView;
import com.mmf.ancientcostume.widget.CorrugateView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by MMF
 * date 2016/12/1
 * Description:波浪的头像
 */
public class UserFragment extends Fragment implements IHomeView<String>{

//    @BindView(R.id.cv_waves)
//    CorrugateView cvWaves;
    @BindView(R.id.lyt_selPhoto)
    LinearLayout lytSelPhoto;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, null);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        cvWaves.cancelTask();
    }


    @OnClick(R.id.lyt_selPhoto)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(),SelPhotoActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imgUrls = data.getStringExtra("imgUrls");
        String[] tempArray =  imgUrls.split(",") ;
        List<String> pathList = Arrays.asList(tempArray);
        Map<String, RequestBody> bodyMap = new HashMap<>();
        if(pathList.size() > 0) {
            for (int i = 0; i < pathList.size(); i++) {
                File file = new File(pathList.get(i));
                bodyMap.put("file"+i+"\"; filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/png"),file));
            }
        }
        UserPresenterImp presenter = new UserPresenterImp(this,getContext());
        presenter.uploadPhoto("试试",bodyMap);
    }

    @Override
    public void setList(List<String> list) {

    }
}
