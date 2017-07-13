package com.mmf.ancientcostume.service.user;

import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.common.utils.service.RetrofitUtil;
import com.mmf.ancientcostume.common.utils.service.SecretConstant;
import com.mmf.ancientcostume.model.LawyerInfo;
import com.mmf.ancientcostume.presenter.IPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public class UserService extends RetrofitUtil {
    private static UserServiceApi userService;

    public UserService(IPresenter presenter) {
        this.presenter = presenter;
    }

    public static UserServiceApi getService() {
        if (userService == null) {
            userService = getRetrofit(SecretConstant.API_HOST).create(UserServiceApi.class);
        }
        return userService;
    }

    public  Observable<List<String>> uploadPhoto(String data,Map<String, RequestBody> bodyMap) {
       return getService().uploadPhoto(data,bodyMap).compose(this.<List<String>>applySchedulers());
    }
    public  Observable<List<String>> uploadPhoto(Map<String,MultipartBody.Part> bodyMap) {
       return getService().upload(bodyMap).compose(this.<List<String>>applySchedulers());
    }
    public  Observable<List<String>> uploadPhoto1(Map<String, RequestBody> bodyMap) {
        Map<String, Object> params = new HashMap<>() ;
        params.put("mao","mao");
        params.put("passwd","mao");
       return getService().upload1(params,bodyMap).compose(this.<List<String>>applySchedulers());
    }


}
