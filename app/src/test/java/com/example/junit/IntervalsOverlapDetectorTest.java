package com.example.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IntervalsOverlapDetectorTest {

    IntervalsOverlapDetector SUT;

    //    (-1,5),(8,12)/

    // Interval 1 end and should  be greater than intervel2 start
    //Interval 1 start should not greater than interval 2 end

    @Before
    public void setUp() throws Exception {
        SUT=new IntervalsOverlapDetector();
    }

    @Test
    public void isOverLap_Interval1EndGreaterInterval2Start_falseReturned() {
        Interval interval1=new Interval(-1,5);
        Interval interval2=new Interval(8,12);
        boolean result=SUT.isOverlap(interval1,interval2);
        assertThat(result,is(false));
    }

    @Test
    public void isOverLap_Interval1EndGreaterInterval2Start_trueReturned() {
        Interval interval1=new Interval(-1,15);
        Interval interval2=new Interval(8,12);
        boolean result=SUT.isOverlap(interval1,interval2);
        assertThat(result,is(true));
    }

    @Test
    public void isOverLap_Interval1StartGreaterInterval2End_falseeturned() {
        Interval interval1=new Interval(100,150);
        Interval interval2=new Interval(8,12);
        boolean result=SUT.isOverlap(interval1,interval2);
        assertThat(result,is(false));
    }

    @Test
    public void isOverLap_Interval1StartGreaterInterval2End_trueturned() {
        Interval interval1=new Interval(100,150);
        Interval interval2=new Interval(8,120);
        boolean result=SUT.isOverlap(interval1,interval2);
        assertThat(result,is(true));
    }


    @Test
    public void isOverLap_AllIntervalValuesAreEqual_falseRetruned() {
        Interval interval1=new Interval(100,100);
        Interval interval2=new Interval(100,100);
        boolean result=SUT.isOverlap(interval1,interval2);
        assertThat(result,is(true));
    }

}