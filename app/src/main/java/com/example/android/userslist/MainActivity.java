package com.example.android.userslist;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements UserRecyclerAdapter.ItemClickListener, UserListContract.View{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    private static final String RETROFIT_URL = "https://randomuser.me/";

    ArrayList<User> userList;
    RecyclerView recyclerView;
    UserRecyclerAdapter adapter;
    UserListContract.Presenter presenter;
    RetrofitService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();

        //doRetrofitNetworkCall();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);
        presenter = new UserListPresenter(this,service);
        presenter.downloadList();
        //userList = presenter.getMyList();
        //displayList(userList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayList(userList);
    }



    @Override
    public void onItemClick(View view, int position) {
        Intent myIntent = new Intent(MainActivity.this,ViewUserActivity.class);
        myIntent.putExtra("user",userList.get(position));
        startActivity(myIntent);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void displayList(ArrayList<User> list) {
        userList = list;
        //Log.d(TAG, "onCreate: My user List " + list.size());
        adapter = new UserRecyclerAdapter(userList,this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }
}
