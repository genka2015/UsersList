package com.example.android.userslist;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("api")
    Call<RandomAPI> getRandomUser();
}
