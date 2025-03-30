package com.zgsy.bj.net;

import com.zgsy.bj.Data.LoginBody;
import com.zgsy.bj.Data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BJApi {

    @POST("login")
    Call<User> getUser(@Body LoginBody loginBody);
}