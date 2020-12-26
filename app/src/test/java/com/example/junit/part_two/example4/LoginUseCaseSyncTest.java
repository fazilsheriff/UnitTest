package com.example.junit.part_two.example4;

import com.example.junit.part_two.example4.authtoken.AuthTokenCache;
import com.example.junit.part_two.example4.eventbus.EventBusPoster;
import com.example.junit.part_two.example4.networking.LoginHttpEndpointSync;
import com.example.junit.part_two.example4.networking.NetworkErrorException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginUseCaseSyncTest {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "Password";
    private static final String AUTHTOKEN = "authToken";


    LoginUseCaseSync SUT;
    LoginHttpEndPointSyncTd loginHttpEndPointSyncTd;
    AuthTokenCacheTd authTokenCacheTd;
    @Before
    public void setUp() throws Exception {
        loginHttpEndPointSyncTd=new LoginHttpEndPointSyncTd();
        authTokenCacheTd=new AuthTokenCacheTd();
        SUT=new LoginUseCaseSync(loginHttpEndPointSyncTd,authTokenCacheTd,new EventBusPosterTd());
    }

    @Test
    public void loginSysnc_userNameAndPasswordPassedEndPoint_trueReturned() {

        SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(loginHttpEndPointSyncTd.muserName, CoreMatchers.is(USERNAME));
        Assert.assertThat(loginHttpEndPointSyncTd.mPassword, CoreMatchers.is(PASSWORD));
    }

    private static  class LoginHttpEndPointSyncTd implements LoginHttpEndpointSync {
        public String muserName;
        public String mPassword;

        @Override
        public EndpointResult loginSync(String username, String password) throws NetworkErrorException {
            muserName=username;
            mPassword=password;
            return new EndpointResult(EndpointResultStatus.SUCCESS, AUTHTOKEN);
        }
    }
    private  static class  AuthTokenCacheTd implements AuthTokenCache{
        String mAuthTokenCache="";
        @Override
        public void cacheAuthToken(String authToken) {
            mAuthTokenCache=authToken;
        }
        @Override
        public String getAuthToken() {
            return mAuthTokenCache;
        }
    }
    private static class EventBusPosterTd implements EventBusPoster{
        @Override
        public void postEvent(Object event) {

        }
    }
}