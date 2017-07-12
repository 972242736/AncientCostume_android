package com.mmf.ancientcostume.service.user;

import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.common.utils.service.Response;
import com.mmf.ancientcostume.model.LawyerInfo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MMF
 * date 2016/9/26.
 * Description:
 */
public interface UserServiceApi {
    @Multipart
    @POST("file/uploadPhoto")
    Observable<Response<List<String>>> uploadPhoto(@Part("data") String data, @PartMap Map<String, RequestBody> params);
    @Multipart
    @POST("file/upload1")
    Observable<Response<List<String>>> upload1(@PartMap Map<String, RequestBody> params);
    @Multipart
    @POST("file/upload")
    Observable<Response<List<String>>> upload(@Part List<MultipartBody.Part> files);
   }
