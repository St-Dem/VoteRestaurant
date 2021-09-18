package ru.restaurant.vote.util.validation;

public class InvalidTime extends RuntimeException {
    public InvalidTime(String message) {
        super(message);
    }
}
