package com.elmostafa.ethicstask.login_page;

import android.app.Activity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class LoginPresenterTest {
    @Mock
    Activity activity;
    @Mock
    LoginView view;
    LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(activity);
        presenter.attachView(view);
    }

    @Test
    public void loginData() {
        presenter.loginData("karim@gmail.com", "kda28sa8");
        Mockito.verify(view);
    }


}