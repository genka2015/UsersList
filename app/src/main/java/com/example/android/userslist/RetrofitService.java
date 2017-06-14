package com.example.android.userslist;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("api/?results=20")
    Call<RandomAPI> getRandomUser();
}
