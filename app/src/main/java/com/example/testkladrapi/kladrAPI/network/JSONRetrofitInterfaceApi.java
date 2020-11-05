package com.example.testkladrapi.kladrAPI.network;

import com.example.testkladrapi.kladrAPI.cityJsonModel.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONRetrofitInterfaceApi {

    @GET("api.php")
    Call<Response> listCities(@Query("query") String city,
                              @Query("contentType") String contentTypeCity,
                              @Query("withParent") String withParent,
                              @Query("limit") String limit);

//    ?query={city}&contentType=city&withParent=2&limit=3



    @GET("api.php")
    Call<Response> listStreets(@Query("cityId") String cityId,
                               @Query("query") String street,
                               @Query("contentType") String contentTypeStreet,
                               @Query("withParent") String withParent,
                               @Query("limit") String limit);

//    ?cityId=7700000000000&query=пу&%20contentType=street&withParent=3&limit=5


    @GET("api.php")
    Call<Response> listBuildings(@Query("streetId") String streetId,
                               @Query("query") String building,
                               @Query("contentType") String contentTypeBuilding,
                               @Query("limit") String limitBuild);

//    ?streetId=29000001000025800&query=142&contentType=building&limit=1

}
