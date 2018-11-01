package com.ak98neon.util;

/**
 * Class calculated percent of number
 */
public final class NumberPercent {
    private NumberPercent() {
    }

    /**
     * Calculate percent
     *
     * @param value  int value
     * @param total  int total
     * @param places int places
     * @return double percent
     */
    public static double calculatePercent(final int value, final int total, final int places) {
        return round((double) value / total * 100, places);
    }

    /**
     * Round double
     *
     * @param value  int value
     * @param places int places
     * @return double
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("A negative number!");

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}