package com.example.android.userslist.SingleUser;


import com.example.android.userslist.Entities.User;

public interface UserContract {
    interface View{
        void showErrorMessage();
        void displayUser(String name, String address, String email, String gender, String phone, String cell, String dob, String nat, String reg, String img);
    }

    interface Presenter{
        void passUser(User user);
    }
}
