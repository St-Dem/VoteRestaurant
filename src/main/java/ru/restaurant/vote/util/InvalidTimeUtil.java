package ru.restaurant.vote.util;

import java.time.LocalTime;

public class InvalidTimeUtil {
    public static final LocalTime INVALID_TIME = LocalTime.of(11, 00);
    private static boolean check = true;

    public static boolean beforeInvalidTime() {
        if (check) {
            return LocalTime.now().isBefore(INVALID_TIME);
        } else {
            check = true;
            return true;
        }
    }

    public static void alwaysValid() {
        check = false;
    }
}
