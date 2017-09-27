package com.mmf.ancientcostume.fragment.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.activity.GoodsListActivity;
import com.mmf.ancientcostume.common.utils.ClippingPicture;
import com.mmf.ancientcostume.common.utils.ListUtils;
import com.mmf.ancientcostume.other.zhy.imageloader.SelPhotoActivity;
import com.mmf.ancientcostume.presenter.imp.user.UserPresenterImp;
import com.mmf.ancientcostume.view.home.IHomeView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by MMF
 * date 2016/12/1
 * Description:波浪的头像
 */
public class UserFragment extends Fragment implements IHomeView<String> {

//    @BindView(R.id.cv_waves)
//    CorrugateView cvWaves;
    @BindView(R.id.iv_test)
    ImageView ivTest;
    @BindView(R.id.lyt_selPhoto)
    LinearLayout lytSelPhoto;
    @BindView(R.id.lyt_selPhoto1)
    LinearLayout lytSelPhoto1;
    @BindView(R.id.lyt_test)
    LinearLayout lytTest;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        cvWaves.cancelTask();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<String> imgUrls = data.getStringArrayListExtra("imgUrls");
        if (!ListUtils.isEmpty(imgUrls)) {
            Picasso.with(getActivity()).load(ClippingPicture.getImageContentUri(getActivity(), new File(imgUrls.get(0)))).into(ivTest);
            List<Uri> listUri = new ArrayList<>();
            switch (requestCode) {
                case 1:
                    Map<String, MultipartBody.Part> bodyMap = new HashMap<>();
                    if (imgUrls.size() > 0) {
                        for (int i = 0; i < imgUrls.size(); i++) {
                            File file = new File(imgUrls.get(i).trim());
                            RequestBody requestFile =
                                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            MultipartBody.Part body =
                                    MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                            bodyMap.put("1", body);
                        }
                    }
                    UserPresenterImp presenter = new UserPresenterImp(this, getContext());
                    presenter.uploadPhoto(bodyMap);
                    break;
                case 2:
                    Map<String, RequestBody> bodyMap1 = new HashMap<String, RequestBody>();
                    if (imgUrls.size() > 0) {
                        for (int i = 0; i < imgUrls.size(); i++) {
                            File file = new File(imgUrls.get(i).trim());
                            bodyMap1.put("file" + i + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                        }
                    }
                    UserPresenterImp presenter1 = new UserPresenterImp(this, getContext());
                    presenter1.uploadPhoto1(bodyMap1);
                    break;
                default:
                    break;
            }
        }


    }

    @Override
    public void setList(List<String> list) {

    }


    @OnClick({R.id.lyt_selPhoto, R.id.lyt_selPhoto1, R.id.lyt_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lyt_selPhoto:
                Intent intent = new Intent(getActivity(), SelPhotoActivity.class);
                startActivityForResult(intent, 1);
//                startActivityForResult(Intent.createChooser(intent,"选择照片"),1);
                break;
            case R.id.lyt_selPhoto1:
                Intent intent1 = new Intent(getActivity(), SelPhotoActivity.class);
                startActivityForResult(intent1, 2);
//                startActivityForResult(Intent.createChooser(intent1,"选择照片"),2);
                break;
            case R.id.lyt_test:
//                Intent intent2 = new Intent(getActivity(), ReleaseFragment.class);
//                startActivity(intent2);
                Intent intent2 = new Intent(getActivity(), GoodsListActivity.class);
                startActivity(intent2);
//                startActivityForResult(Intent.createChooser(intent1,"选择照片"),2);
                break;
        }
    }
}
