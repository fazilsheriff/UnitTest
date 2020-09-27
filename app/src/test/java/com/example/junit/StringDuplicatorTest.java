package com.example.junit;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringDuplicatorTest {

    StringDuplicator SUT;

    @Before
    public void setUp() throws Exception {
        SUT=new StringDuplicator();

    }



    @Test
    public void duplicator_checkStringDuplication_duplicationOccured() {
        String result=SUT.duplicate("hi");
        assertThat(result,is("hihi"));
    }
}