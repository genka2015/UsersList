package com.example.android.userslist;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class UsersPresenterTest {

    private UserListPresenter presenter;
    private List<User> users;

    @Mock
            UserListContract.View view;
    @Mock
            RetrofitService service;
    @Mock
    Call<RandomAPI> call;
    @Mock
    ResponseBody responseBody;

    @Captor
    ArgumentCaptor<retrofit2.Callback<RandomAPI>> argumentCaptor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new UserListPresenter(view, service);
        users = Collections.singletonList(new User());
    }


    @Test
    public void shouldFailGettingUsers(){
        // Mocking the network call
        when(service.getRandomUser()).thenReturn(call);
        Response<RandomAPI> response = Response.error(500, responseBody);

        // 2
        presenter.downloadList();

        // 3
        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, response);

        // 4
        verify(view).showErrorMessage();
    }

    @Test
    public void shouldFailRequest(){
        when(service.getRandomUser()).thenReturn(call);
        Throwable throwable = new Throwable(new RuntimeException());

        presenter.getMyList();

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(null,throwable);

       // verify(view).showNetworkError();
    }

}
