package org.example.exceptions;

public class InvalidPostalCodeException extends RuntimeException {
    public InvalidPostalCodeException(String message) {
        super("Invalid Post Code: " + message);
    }
}
