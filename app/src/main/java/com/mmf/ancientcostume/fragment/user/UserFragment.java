package com.mmf.ancientcostume.fragment.user;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmf.ancientcostume.R;
import com.mmf.ancientcostume.widget.CorrugateView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MMF
 * date 2016/12/1
 * Description:波浪的头像
 */
public class UserFragment extends Fragment {

    @BindView(R.id.cv_waves)
    CorrugateView cvWaves;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, null);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cvWaves.cancelTask();
    }
}
