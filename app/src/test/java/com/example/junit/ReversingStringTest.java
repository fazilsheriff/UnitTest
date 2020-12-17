package com.example.junit;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReversingStringTest {
    ReversingString SUT;

    @Before
    public void setUp() throws Exception {

            SUT=new ReversingString();
    }

    @Test
    public void reverse_emptyString_emptyStringReturned() {
        String result=SUT.reverse("");
        assertThat(result,is(""));
    }

    @Test
    public void reverse_singleCharacter_equalSingleCharacterReturned() {
        String result=SUT.reverse("w");
        assertThat(result,is("w"));
    }

    @Test
    public void reverse_longReverseString_reverseStringReturned() {
        String result=SUT.reverse("Fazil");
        assertThat(result,is("lizaF"));
    }
//    @Test
//    public void reverse_checkNull_returnNull(){
//        String result=SUT.reverse("Fazil");
//        assertThat(myString).isNotEmpty();
//    }
}