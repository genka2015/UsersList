package com.example.android.userslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewUserActivity extends AppCompatActivity {

    ImageView userImage;
    TextView userData;
    User myUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        userImage = (ImageView)findViewById(R.id.imageIV);
        userData = (TextView)findViewById(R.id.userTV);
        myUser = getIntent().getParcelableExtra("user");
        Glide.with(this)
                .load(myUser.getImage())
                .into(userImage);

        userData.setText("Name: " + myUser.getName() + "\nAddress: " + myUser.getAddress() + "\nEmail: " + myUser.getEmail() + "\nGender: " + myUser.getGender()+ "\nPhone: " + myUser.getPhone()+ "\nCell: " + myUser.getCell()+ "\nDate of birth: " + myUser.getDob()+ "\nNationality: " + myUser.getNat()+ "\nRegistered: " + myUser.getRegistered());
    }
}
