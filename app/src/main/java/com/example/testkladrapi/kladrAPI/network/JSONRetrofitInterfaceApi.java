package com.example.testkladrapi.kladrAPI.network;

import com.example.testkladrapi.kladrAPI.Response;

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

}
