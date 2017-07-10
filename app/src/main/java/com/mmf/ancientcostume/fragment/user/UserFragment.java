package com.mmf.ancientcostume.fragment.user;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.other.zhy.imageloader.SelPhotoActivity;
import com.mmf.ancientcostume.presenter.imp.user.UserPresenterImp;
import com.mmf.ancientcostume.view.home.IHomeView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

/**
 * Created by MMF
 * date 2016/12/1
 * Description:波浪的头像
 */
public class UserFragment extends Fragment implements IHomeView<String> {

    //    @BindView(R.id.cv_waves)
//    CorrugateView cvWaves;
    @BindView(R.id.lyt_selPhoto)
    LinearLayout lytSelPhoto;
    @BindView(R.id.iv_test)
    ImageView ivTest;
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


    @OnClick(R.id.lyt_selPhoto)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SelPhotoActivity.class);
        startActivityForResult(intent, 1);
//        Intent intent = new Intent(Intent.ACTION_PICK, null);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        startActivityForResult(Intent.createChooser(intent,"选择照片"),1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imgUrls = data.getStringExtra("imgUrls");
        String[] tempArray = imgUrls.substring(1, imgUrls.length() - 1).split(",");
        List<String> pathList = Arrays.asList(tempArray);
//        Uri uri = data.getData();
        Picasso.with(getActivity()).load(getImageContentUri(getActivity(), new File(pathList.get(0)))).into(ivTest);
        List<Uri> listUri = new ArrayList<>();
//        for (String tempPath : pathList) {
//            listUri.add(getImageContentUri(getActivity(), new File(tempPath)));
//        }
//        Map<String, MultipartBody.Part> bodyMap = new HashMap<>();
        Map<String, RequestBody> bodyMap = new HashMap<>();
        if (pathList.size() > 0) {
            for (int i = 0; i < pathList.size(); i++) {
                File file = new File(pathList.get(i));
//                RequestBody requestFile =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part is used to send also the actual file name
//                MultipartBody.Part body =
//                        MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//                bodyMap.put("file" + i + "\"; filename=\"" + file.getName(), body);
                bodyMap.put("file" + i + ":filename=" + file.getName(), RequestBody.create(MediaType.parse("image/png"),file));
            }
        }
        UserPresenterImp presenter = new UserPresenterImp(this, getContext());
        presenter.uploadPhoto("试试", bodyMap);
    }

    @Override
    public void setList(List<String> list) {

    }

    public static Uri getImageContentUri(Context context, java.io.File imageFile) {
//        String filePath = imageFile;
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
//            if (imageFile.exists()) {
//                ContentValues values = new ContentValues();
//                values.put(MediaStore.Images.Media.DATA, filePath);
//                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//            } else {
            return null;
//            }
        }
    }

}
