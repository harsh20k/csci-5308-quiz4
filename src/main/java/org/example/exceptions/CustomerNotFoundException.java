package org.example.exceptions;

/**
 * Custom exception thrown when a data access operation attempts to retrieve
 * or modify a customer that does not exist in the repository.
 */
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomerNotFoundException with a detail message
     * indicating which ID was not found.
     *
     * @param customerId The ID that was requested but not found.
     */
    public CustomerNotFoundException(String customerId) {
        super("Customer not found with ID: " + customerId);
    }
}
