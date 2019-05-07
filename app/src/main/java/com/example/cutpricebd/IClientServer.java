package com.example.cutpricebd;

import okhttp3.Response;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IClientServer {
    @Headers("api-key: " + "Cutprice@987")
    @POST("api/registration")
    Call<User> getApi( @Body User user);

}
