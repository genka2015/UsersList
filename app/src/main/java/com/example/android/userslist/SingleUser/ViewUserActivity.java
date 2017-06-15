package com.example.android.userslist.SingleUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.userslist.Entities.User;
import com.example.android.userslist.R;

public class ViewUserActivity extends AppCompatActivity implements UserContract.View {

    ImageView userImage;
    TextView userData;
    User myUser;
    UserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        userImage = (ImageView)findViewById(R.id.imageIV);
        userData = (TextView)findViewById(R.id.userTV);
        presenter = new UserPresenter(this);
        myUser = getIntent().getParcelableExtra("user");
        presenter.passUser(myUser);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void displayUser(String name, String address, String email, String gender, String phone, String cell, String dob, String nat, String reg, String img) {
        Glide.with(this)
                .load(img)
                .into(userImage);
        userData.setText("Name: " + name + "\nAddress: " + address + "\nEmail: " + email + "\nGender: " + gender + "\nPhone: " + phone + "\nCell: " + cell + "\nDate of birth: " + dob + "\nNationality: " + nat + "\nRegistered: " + reg);
    }
}
