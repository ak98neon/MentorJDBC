package com.ak98neon.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberPercentTest {

    @Test
    public void calculatePercent_valueAndTotalAndPlace_Equals() {
        assertEquals(33.33, NumberPercent.calculatePercent(1, 3, 2), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatePercent_NegativeValueAndTotalAndPlace_Exception() {
        NumberPercent.calculatePercent(-1, 3, -1);
    }

    @Test(timeout = 1000)
    public void calculatePercent_NullValueAndTotalAndPlace_Equals() {
        assertEquals(333333.333, NumberPercent.calculatePercent(10000, 3, 3), 0);
    }
}