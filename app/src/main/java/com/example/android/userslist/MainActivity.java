package com.example.android.userslist;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://randomuser.me/api";
    private static final String RETROFIT_URL = "https://randomuser.me/";

    private static final String USER_NAME = "USER_NAME";
    private static final String USER_ADDRESS = "USER_ADDRESS";
    private static final String USER_EMAIL = "USER_EMAIL";
    private static final String USER_GENDER = "USER_GENDER";
    private static final String USER_PHONE = "USER_PHONE";
    private static final String USER_CELL = "USER_CELL";
    private static final String USER_DOB = "USER_DOB";
    private static final String USER_NAT = "USER_NAT";
    private static final String USER_REGISTERED = "USER_REGISTERED";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String name = msg.getData().getString(USER_NAME);
            String address = msg.getData().getString(USER_ADDRESS);
            String email = msg.getData().getString(USER_EMAIL);
            String gender = msg.getData().getString(USER_GENDER);
            String phone = msg.getData().getString(USER_EMAIL);
            String cell = msg.getData().getString(USER_EMAIL);
            String dob = msg.getData().getString(USER_EMAIL);
            String nat = msg.getData().getString(USER_EMAIL);
            String registered = msg.getData().getString(USER_EMAIL);
            postResult(name, address, email);
        }
    };

    private void postResult(String name, String address, String email) {

    }

    TextView responseName;
    TextView responseAddress;
    TextView responseEmail;
    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void doRetrofitNetworkCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        retrofit2.Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new retrofit2.Callback<RandomAPI>() {
            @Override
            public void onResponse(retrofit2.Call<RandomAPI> call, retrofit2.Response<RandomAPI> response) {
                if(response.isSuccessful()){
                    Message msg = handler.obtainMessage();
                    Bundle data = new Bundle();
                    String currentName = "";
                    String currentAddress = "";
                    String currentEmail = "";

                    RandomAPI randomAPI = response.body();
                    for(Result result:randomAPI.getResults()){
                        Log.d(TAG, "onResponse: Name is " + result.getName());

                        currentName = result.getName().getTitle() + " " + result.getName().getFirst() + " " + result.getName().getLast();
                        currentAddress = result.getLocation().getStreet() + " " + result.getLocation().getCity() + " " + result.getLocation().getState() + " " + Integer.toString(result.getLocation().getPostcode());
                        currentEmail = result.getEmail();
                        data.putString(USER_NAME, currentName);
                        data.putString(USER_ADDRESS, currentAddress);
                        data.putString(USER_EMAIL, currentEmail);
                        User currentUser = new User(currentName,currentAddress,currentEmail);
                        userList.add(currentUser);

                    }
                    msg.setData(data);
                    handler.sendMessage(msg);
                }
                else{}
            }

            @Override
            public void onFailure(retrofit2.Call<RandomAPI> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
