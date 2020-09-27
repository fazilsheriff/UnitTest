package com.example.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PostiveNumberValidatorTest {

    PostiveNumberValidator SUT;

    @Before
    public void setUp()
    {
        SUT=new PostiveNumberValidator();
    }

    @Test
    public void testOne()
    {
        boolean result=SUT.isPostive(-1);
        Assert.assertThat(result,is(false));
    }

    @Test
    public void tesTwo()
    {
        boolean result=SUT.isPostive(0);
        Assert.assertThat(result,is(false));
    }

    @Test
    public  void testThree()
    {
        boolean result=SUT.isPostive(2);
        Assert.assertThat(result,is(true));
    }

}