package com.example.junit.part_one;

import com.example.junit.part_one.StringDuplicator;

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
    public void duplicator_checkStringDuplicationWithTwoCharactes_duplicationOccured() {
        String result=SUT.duplicate("hi");
        assertThat(result,is("hihi"));
    }
    @Test
    public void duplicator_checkStringDuplicationWithWords_duplicationOccured(){
        String result=SUT.duplicate("Four");
        assertThat(result,is("FourFour"));
    }
}