package com.example.android.userslist.List;


import java.util.ArrayList;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import android.util.Log;

import com.example.android.userslist.Entities.Result;
import com.example.android.userslist.Entities.User;
import com.example.android.userslist.Utils.RandomAPI;
import com.example.android.userslist.Utils.RetrofitService;

public class UserListPresenter implements UserListContract.Presenter{


    private UserListContract.View view;
    private RetrofitService service;
    private ArrayList<User> myList;


    public UserListPresenter(UserListContract.View view, RetrofitService service){
        this.view = view;
        this.service = service;
        this.myList = new ArrayList<User>();
    }

    @Override
    public ArrayList<User> getMyList() {
        return myList;
    }

    @Override
    public void downloadList() {
        retrofit2.Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new retrofit2.Callback<RandomAPI>() {
            @Override
            public void onResponse(retrofit2.Call<RandomAPI> call, retrofit2.Response<RandomAPI> response) {
                if(response.isSuccessful()){
                    //myList = new ArrayList<User>();
                    String currentName = "";
                    String currentAddress = "";
                    String currentEmail = "";
                    String currentGender = "";
                    String currentPhone = "";
                    String currentCell = "";
                    String currentDOB = "";
                    String currentNat = "";
                    String currentReg = "";
                    String currentImg = "";

                    RandomAPI randomAPI = response.body();

                    for(Result result:randomAPI.getResults()){
                        //Log.d(TAG, "onResponse: Name is " + result.getName());

                        //currentName = result.getName().toString();
                        currentName = result.getName().getTitle() + " " + result.getName().getFirst() + " " + result.getName().getLast();
                        //currentAddress = result.getLocation().toString();
                        currentAddress = result.getLocation().getStreet() + " " + result.getLocation().getCity() + " " + result.getLocation().getState() + " " + result.getLocation().getPostcode();
                        currentEmail = result.getEmail();
                        currentGender = result.getGender();
                        currentPhone = result.getPhone();
                        currentCell = result.getCell();
                        currentDOB = result.getDob();
                        currentNat = result.getNat();
                        currentReg = result.getRegistered();
                        currentImg = result.getPicture().getMedium();
                        User currentUser = new User(currentName,currentAddress,currentEmail, currentGender,currentPhone,currentCell,currentDOB,currentNat,currentReg,currentImg);
                        myList.add(currentUser);

                    }


                    view.displayList(myList);


                }
                else{
                    Log.d(TAG, "onResponse: ABANDON THE SHIP!");
                }
            }


            @Override
            public void onFailure(retrofit2.Call<RandomAPI> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
