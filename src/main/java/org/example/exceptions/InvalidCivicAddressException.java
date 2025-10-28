package org.example.exceptions;

public class InvalidCivicAddressException extends RuntimeException {
    public InvalidCivicAddressException(String message) {
        super(message);
    }
}
