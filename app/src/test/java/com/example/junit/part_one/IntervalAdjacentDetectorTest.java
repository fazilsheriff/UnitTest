package com.example.junit.part_one;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IntervalAdjacentDetectorTest {
IntervalAdjacentDetector SUT;
    @Before
    public void setUp() throws Exception {
        SUT=new IntervalAdjacentDetector();
    }
    
    //Check both interval star and end are same


    @Test
    public void isEqual_interval1StartPointsEqual_falseReturned() {
        Interval interval1=new Interval(18,150);
        Interval interval2=new Interval(18,100);
        boolean result=SUT.isAdjacent(interval1,interval2);
        assertThat(result,is(false));
    }

    @Test
    public void isEqual_intervalEndPointsAreEqual_falseReturned() {
        Interval interval1=new Interval(38,100);
        Interval interval2=new Interval(48,100);
        boolean result=SUT.isAdjacent(interval1,interval2);
        assertThat(result,is(false));
    }

    @Test
    public void isEqual_checkingTwoIntervlasSame_falseReturned() {
        Interval interval1=new Interval(38,100);
        Interval interval2=new Interval(38,100);
        boolean result=SUT.isAdjacent(interval1,interval2);
        assertThat(result,is(false));
    }

    @Test
    public void name() {
    }
}