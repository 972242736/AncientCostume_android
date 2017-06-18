package com.mmf.ancientcostume.service.home;

import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.common.utils.service.RetrofitUtil;
import com.mmf.ancientcostume.common.utils.service.SecretConstant;
import com.mmf.ancientcostume.model.LawyerInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class HomeService extends RetrofitUtil {
    private static HomeServiceApi homeService;
    public static HomeServiceApi getService() {
        if (homeService == null) {
            homeService = getRetrofit(SecretConstant.API_HOST).create(HomeServiceApi.class);
        }
        return homeService;
    }
    public Observable<List<LawyerInfo>> getLawyer() {
        return getService().getLawyer("json", 0, 10, "福州", "6f940a4a81649f3b6d30e47cdd37a5ad")
                .compose(this.<List<LawyerInfo>>applySchedulers());
    }
    public Observable<List<PositionInfo>> getInfo(String loc){
        return getService().getInfo("nQS647KWkRaA8LkIXvCKwfuIcrbl9sHC", "157287", "3C:B5:09:43:AB:5C:FB:F1:A1:B8:DB:6A:CD:F2:6D:6D:76:74:70:26;com.mmf.framework", loc)
                .compose(this.<List<PositionInfo>>applySchedulers());
    }

}
