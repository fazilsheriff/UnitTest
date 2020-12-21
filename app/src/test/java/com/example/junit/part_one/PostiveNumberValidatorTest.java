package com.example.junit.part_one;

import com.example.junit.part_one.PostiveNumberValidator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PostiveNumberValidatorTest {

    PostiveNumberValidator SUT;

    @Before
    public void setUp()
    {
        SUT=new PostiveNumberValidator();
    }

    @Test
    public void positiveNumber_checkNegativeNumberIsPostive_returnFalse()
    {
        boolean result=SUT.isPostive(-1);
        Assert.assertThat(result,is(false));
    }

    @Test
    public void positiveNumber_checkZeroIsPostive_returnFalse()
    {
        boolean result=SUT.isPostive(0);
        Assert.assertThat(result,is(false));
    }

    @Test
    public  void postiveNumber_checkPostiveNumberIsPostive_returnTrue()
    {
        boolean result=SUT.isPostive(2);
        Assert.assertThat(result,is(true));
    }

}