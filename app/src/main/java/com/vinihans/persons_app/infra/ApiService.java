package com.vinihans.persons_app.infra;

import com.vinihans.persons_app.model.user.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/v1/auth/login")
    Call<ResponseBody> login(@Body User user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/v1/people")
    Call<ResponseBody> getAll(@Header("Authorization") String auth);

}
