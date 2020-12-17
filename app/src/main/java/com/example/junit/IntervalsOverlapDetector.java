package com.example.junit;

public class IntervalsOverlapDetector {
    public boolean isOverlap(Interval interval1, Interval interval2) {
        return interval1.getEnd() > interval2.getStart() && interval1.getStart() < interval2.getEnd();
    }

//    (-1,15),(8,12)/

    // First interval End should higher than Second interval  start
    //Second interval End should be higher than fist inter val start

    //For both interval End should be greater



}
