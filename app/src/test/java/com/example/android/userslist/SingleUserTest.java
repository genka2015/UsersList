package com.example.android.userslist;


import com.example.android.userslist.Entities.User;
import com.example.android.userslist.SingleUser.UserContract;
import com.example.android.userslist.SingleUser.UserPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class SingleUserTest {

    private UserPresenter presenter;

    @Mock
    User user;

    @Mock
    UserContract.View view;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new UserPresenter(view);
    }

    @Test
    public void failGetUser(){
        // get User
        presenter.passUser(null);
        // verify correct call
        verify(view).showErrorMessage();
    }

    @Test
    public void confirmGetUser(){
        // get User
        presenter.passUser(user);
        // verify correct call
        verify(view).displayUser(null,null,null,null,null,null,null,null,null,null);
    }
}
