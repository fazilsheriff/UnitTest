package com.example.junit.part_two.example4;

import com.example.junit.part_two.example4.authtoken.AuthTokenCache;
import com.example.junit.part_two.example4.eventbus.EventBusPoster;
import com.example.junit.part_two.example4.networking.LoginHttpEndpointSync;
import com.example.junit.part_two.example4.networking.NetworkErrorException;

import org.junit.Before;

import static org.junit.Assert.*;

public class LoginUseCaseSyncTest {
LoginUseCaseSync SUT;
    @Before
    public void setUp() throws Exception {
        SUT=new LoginUseCaseSync(new LoginHttpEndPointSyncTd(),new AuthTokenCacheTd(),new EventBusPosterTd());
    }

    private static  class LoginHttpEndPointSyncTd implements LoginHttpEndpointSync {

        @Override
        public EndpointResult loginSync(String username, String password) throws NetworkErrorException {
            return null;
        }
    }

    private  static class  AuthTokenCacheTd implements AuthTokenCache{

        @Override
        public void cacheAuthToken(String authToken) {

        }

        @Override
        public String getAuthToken() {
            return null;
        }
    }

    private static class EventBusPosterTd implements EventBusPoster
    {

        @Override
        public void postEvent(Object event) {

        }
    }
}