package com.example.android.userslist.List;


import com.example.android.userslist.Entities.User;

import java.util.ArrayList;

public interface UserListContract {

    interface View{

        void showErrorMessage();

        void displayList(ArrayList<User> list);
    }

    interface Presenter{

        void downloadList();
       ArrayList<User> getMyList();
    }

}
