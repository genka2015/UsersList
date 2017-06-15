package com.example.android.userslist.SingleUser;

import com.example.android.userslist.Entities.User;

public class UserPresenter implements UserContract.Presenter{

    private UserContract.View view;

    public UserPresenter(UserContract.View v){
        this.view = v;
    }

    @Override
    public void passUser(User user) {
        if(user != null){
            view.displayUser(user.getName(),user.getAddress(),user.getEmail(),user.getGender(),user.getPhone(),user.getCell(),user.getDob(),user.getNat(),user.getRegistered(),user.getImage());
        }
        else{
            view.showErrorMessage();
        }
    }
}
