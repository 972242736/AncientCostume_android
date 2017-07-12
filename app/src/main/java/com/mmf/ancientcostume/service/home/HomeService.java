package com.mmf.ancientcostume.service.home;

import android.util.Log;

import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.common.utils.service.RetrofitUtil;
import com.mmf.ancientcostume.common.utils.service.SecretConstant;
import com.mmf.ancientcostume.model.LawyerInfo;
import com.mmf.ancientcostume.model.User;
import com.mmf.ancientcostume.presenter.IPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class HomeService extends RetrofitUtil {
    private static HomeServiceApi homeService;

    public HomeService(IPresenter presenter) {
        this.presenter = presenter;
    }

    public static HomeServiceApi getService() {
        if (homeService == null) {
            homeService = getRetrofit(SecretConstant.API_HOST).create(HomeServiceApi.class);
        }
        return homeService;
    }

    public Observable<List<LawyerInfo>>  getLawyer() {
       return getService().getLawyer("json", 0, 10, "福州", "6f940a4a81649f3b6d30e47cdd37a5ad")
                .compose(this.<List<LawyerInfo>>applySchedulers());
//        success(lawyerInfoList);
    }

    public Observable<List<User>>  list() {
        Map<String, Object> params = new HashMap<>() ;
        params.put("mao","mao");
        params.put("passwd","mao");
        return getService().list(params)
                .compose(this.<List<User>>applySchedulers());
//        success(lawyerInfoList);
//        return getService().getInfo("nQS647KWkRaA8LkIXvCKwfuIcrbl9sHC", "157287", "3C:B5:09:43:AB:5C:FB:F1:A1:B8:DB:6A:CD:F2:6D:6D:76:74:70:26;com.mmf.framework", loc)
//                .compose(this.<List<PositionInfo>>applySchedulers());
    }

}
