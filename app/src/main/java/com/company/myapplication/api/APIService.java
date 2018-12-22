package com.company.myapplication.api;

import com.company.myapplication.api.response.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/api/login")
    @FormUrlEncoded
    public Call<Response> login(@Field("account") String account,
                                @Field("password") String password);

    @POST("/api/register")
    @FormUrlEncoded
    public Call<Response> register(@Field("account") String account,
                                        @Field("password") String password);

}
