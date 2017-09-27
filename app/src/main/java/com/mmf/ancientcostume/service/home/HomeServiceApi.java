package com.mmf.ancientcostume.service.home;

import com.mmf.ancientcostume.baidu.bean.PositionInfo;
import com.mmf.ancientcostume.common.utils.service.Response;
import com.mmf.ancientcostume.model.GoodsDetail;
import com.mmf.ancientcostume.model.GoodsDetailAndImg;
import com.mmf.ancientcostume.model.LawyerInfo;
import com.mmf.ancientcostume.model.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by MMF
 * date 2016/9/26
 * Description:
 */
public interface HomeServiceApi {
    @GET("lawyers/city")
    Observable<Response<List<LawyerInfo>>> getLawyer(@Query("dtype") String dtype, @Query("st") int st, @Query("count") int count, @Query("city") String pro, @Query("key") String key);

    @GET("geosearch/v3/nearby")
    Observable<Response<List<PositionInfo>>> getInfo(@Query("ak") String s2, @Query("geotable_id") String s, @Query("mcode") String s1, @Query("location") String loc);

    @GET("user/list")
    Observable<Response<List<User>>> list(@Query("name") String name);

    @GET("user/list1")
    Observable<Response<List<User>>> list(@QueryMap Map<String, Object> params);

    @Multipart
    @POST("goods/insertDetail")
    Observable<Response<String>> insertDetail(@QueryMap Map<String, Object> info, @PartMap Map<String, RequestBody> bodyMap);

    @GET("goods/getGoodsDetail")
    Observable<Response<GoodsDetailAndImg>> getGoodsDetail(@Query("id") int id,@Query("type") int type);

    @GET("goods/getGoodsList")
    Observable<Response<List<GoodsDetail>>> getGoodsList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

}
