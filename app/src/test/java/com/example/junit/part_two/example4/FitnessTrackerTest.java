package com.example.junit.part_two.example4;

import com.example.junit.part_two.example4.FitnessTracker;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FitnessTrackerTest {
    FitnessTracker SUT;

    @Before
    public void setUp() throws Exception {

        SUT=new FitnessTracker();
    }

    @Test
    public void total_Incremented() {
        SUT.step();
        assertThat(SUT.getTotalSteps(),is(1));
    }

    @Test
    public void runStepTotalIncremented() {
        SUT.runStep();
        assertThat(SUT.getTotalSteps(),is(2));
    }
}