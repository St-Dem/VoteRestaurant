package ru.restaurant.vote.util;

import java.time.LocalTime;

public class InvalidTimeUtil {
    public static final LocalTime INVALID_TIME = LocalTime.of(11, 00);

    public static boolean beforeInvalidTime() {
        return LocalTime.now().isBefore(INVALID_TIME);
    }
}
