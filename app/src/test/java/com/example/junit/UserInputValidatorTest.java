package com.example.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserInputValidatorTest {
    UserInputValidator SUT;

    @Before
    public void setUp() throws Exception {
        SUT=new UserInputValidator();
    }

    @Test
    public void isValidFullName_validFullName_isTrueReturned() {
        boolean result=SUT.isValidFullName("Fazil");
       assertThat(result,is(true));
    }

    @Test
    public void isValidFullName_InvalidNameGiven_isFalseReturned() {
        boolean result=SUT.isValidFullName("");
        assertThat(result,is(false));
    }

    @Test
    public void isUserName_ValidNameGiven_isTrueReturned()
    {
        boolean result=SUT.isValidUsername("232");
        assertThat(result,is(true));
    }


    @Test
    public void isUserName_inValidNameGiven_isFalseReturned()
    {
        boolean result=SUT.isValidUsername("");
        assertThat(result,is(false));
    }
}