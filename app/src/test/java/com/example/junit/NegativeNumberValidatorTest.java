package com.example.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NegativeNumberValidatorTest {

    NegativeNumberValidator SUT;

    @Before
    public void setUp() throws Exception {
        SUT=new NegativeNumberValidator();
    }

    @Test
    public void testOne()
    {
        boolean result=SUT.isNegattiveNumber(100);
        Assert.assertThat(result,is(false));
    }

    @Test
    public void testTwo()
    {

        boolean result=SUT.isNegattiveNumber(-1002);
        Assert.assertThat(result,is(true));
    }

    @Test
    public void testThree()
    {
        boolean result=SUT.isNegattiveNumber(0);
        Assert.assertThat(result,is(false));
    }
}