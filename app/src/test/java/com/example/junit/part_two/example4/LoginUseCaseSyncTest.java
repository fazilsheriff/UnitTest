package com.example.junit.part_two.example4;

import com.example.junit.part_two.example4.authtoken.AuthTokenCache;
import com.example.junit.part_two.example4.eventbus.EventBusPoster;
import com.example.junit.part_two.example4.eventbus.LoggedInEvent;
import com.example.junit.part_two.example4.networking.LoginHttpEndpointSync;
import com.example.junit.part_two.example4.networking.NetworkErrorException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.example.junit.part_two.example4.LoginUseCaseSync.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LoginUseCaseSyncTest {
    //Constants
    private static final String USERNAME = "username";
    private static final String PASSWORD = "Password";
    private static final String AUTHTOKEN = "authToken";
    //Class objects
    LoginUseCaseSync SUT;
    LoginHttpEndPointSyncTd loginHttpEndPointSyncTd;
    AuthTokenCacheTd authTokenCacheTd;
    EventBusPosterTd eventBusPosterTd;
    @Before
    public void setUp() throws Exception {
        loginHttpEndPointSyncTd=new LoginHttpEndPointSyncTd();
        authTokenCacheTd=new AuthTokenCacheTd();
        eventBusPosterTd=new EventBusPosterTd();
        SUT=new LoginUseCaseSync(loginHttpEndPointSyncTd,authTokenCacheTd,eventBusPosterTd);
    }
    @Test
    public void loginSysnc_userNameAndPasswordPassedEndPoint_trueReturned() throws  Exception{
        SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(loginHttpEndPointSyncTd.muserName, is(USERNAME));
        Assert.assertThat(loginHttpEndPointSyncTd.mPassword, is(PASSWORD));
    }
    @Test
    public void loginSync_AuthTokenCached_returnedTrue() {
        SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(authTokenCacheTd.getAuthToken(), is(AUTHTOKEN));
    }

    @Test
    public void loginSync_AuthTokenCached_gendralErrorReturned() {
        loginHttpEndPointSyncTd.mIsGendralError=true;
        SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(authTokenCacheTd.getAuthToken(), is(""));
    }
    @Test
    public void loginSync_AuthTokenCached_authErrorReturned(){
        loginHttpEndPointSyncTd.mIsAuthError=true;
        SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(authTokenCacheTd.getAuthToken(), is(""));
    }
    @Test
    public void loginSync_AuthTokenCached_serverErrorReturned(){
        loginHttpEndPointSyncTd.mIsAuthError=true;
        SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(authTokenCacheTd.getAuthToken(), is(""));
    }
    @Test
    public void loginSync_EventBusLoggedInposted_retrunedTrue() {
        SUT.loginSync(USERNAME,PASSWORD);
    assertThat(eventBusPosterTd.mEvent,is(instanceOf(LoggedInEvent.class)));
    }

    @Test
    public void loginSync_AuthError_noInteractionWithEventBusPoster() throws Exception {
        loginHttpEndPointSyncTd.mIsAuthError=true;
        SUT.loginSync(USERNAME,PASSWORD);
        assertThat(eventBusPosterTd.mInteractionCount,is(0));
    }

    @Test
    public void loginSync_serverError_noInteractionWithEventBusPoster() throws Exception {
        loginHttpEndPointSyncTd.mIsServerError=true;
        SUT.loginSync(USERNAME,PASSWORD);
        assertThat(eventBusPosterTd.mInteractionCount,is(0));
    }

    @Test
    public void loginSync_GendralError_noInteractionWithEventBusPoster() throws Exception {
        loginHttpEndPointSyncTd.mIsGendralError=true;
        SUT.loginSync(USERNAME,PASSWORD);
        assertThat(eventBusPosterTd.mInteractionCount,is(0));
    }
    @Test
    public void loginSync_Suceess_SuccessReturned() {
        UseCaseResult result= SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(result,is(UseCaseResult.SUCCESS));
    }

    @Test
    public void loginSync_serverError_failureReturned() throws Exception {
        loginHttpEndPointSyncTd.mIsServerError=true;
        UseCaseResult result= SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(result,is(UseCaseResult.FAILURE));
    }

    @Test
    public void loginSync_gendralrError_failureReturned() throws Exception {
        loginHttpEndPointSyncTd.mIsGendralError=true;
        UseCaseResult result= SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(result,is(UseCaseResult.FAILURE));
    }

    @Test
    public void loginSync_netWorkError_failureReturned() throws Exception {
        loginHttpEndPointSyncTd.misNetworkError=true;
        UseCaseResult result= SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(result,is(UseCaseResult.NETWORK_ERROR));
    }

    @Test
    public void loginSync_authrError_successReturned() throws Exception {
        loginHttpEndPointSyncTd.mIsAuthError=true;
        UseCaseResult result= SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(result,is(UseCaseResult.FAILURE));
    }


    //Test Double Classes
    private static  class LoginHttpEndPointSyncTd implements LoginHttpEndpointSync {
        public String muserName;
        public String mPassword;
        public boolean mIsGendralError;
        public boolean mIsAuthError;
        public boolean mIsServerError;
        public boolean misNetworkError;

        @Override
        public EndpointResult loginSync(String username, String password) throws NetworkErrorException {
            muserName=username;
            mPassword=password;
            if(mIsGendralError){
                return new EndpointResult(EndpointResultStatus.GENERAL_ERROR, "");
            }
            else if(mIsServerError){
                return new EndpointResult(EndpointResultStatus.SERVER_ERROR, "");
            }
            else if(mIsAuthError){
                return new EndpointResult(EndpointResultStatus.AUTH_ERROR, "");
            }
            else if(misNetworkError){
                throw  new NetworkErrorException();
            }
            else
            {
                return new EndpointResult(EndpointResultStatus.SUCCESS, AUTHTOKEN);
            }

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
        public Object mEvent;
        public int mInteractionCount=0;

        @Override
        public void postEvent(Object event) {
            mEvent=event;
            mInteractionCount++;
        }
    }
}