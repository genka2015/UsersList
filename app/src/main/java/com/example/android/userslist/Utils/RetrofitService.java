package com.example.android.userslist.Utils;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("api/?results=20")
    Call<RandomAPI> getRandomUser();
}
