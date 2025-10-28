package org.example.repository;

import org.example.exceptions.CustomerNotFoundException;
import org.example.model.Customer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
        * A working, but simplified (in-memory) implementation of CustomerRepository.
        * This is the FAKE used in testing to simulate database/file persistence
 * without requiring actual I/O.
 */
public class InMemoryCustomerRepository implements ICustomerRepository {
    // Internal state that simulates the database table
    private final Map<String, Customer> customers = new HashMap<>();

    /**
     * Initializes the repository with some default data.
     */
    public InMemoryCustomerRepository() {
        // Pre-load a few fake customers
        customers.put("C1001", new Customer("C1001", LocalDate.now(), null, null, 100.00));
        customers.put("C1002", new Customer("C1002", LocalDate.now(), null, null, 500.00));
    }

    @Override
    public Optional<Customer> findById(String customerId) throws CustomerNotFoundException {
        if (!customers.containsKey(customerId)) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }
        // Returns a copy to simulate reading from storage
        Customer original = customers.get(customerId);
        return Optional.of(new Customer(original.getCustomerID(), original.getJoinedOn(), original.getShoppingCart(), original.getAddress(), original.getBalance()));
    }

    @Override
    public void save(Customer customer) {
        // Simulates writing a record to the database
        customers.put(customer.getCustomerID(), customer);
    }

    @Override
    public void delete(String customerId) throws CustomerNotFoundException {
        if (customers.remove(customerId) == null) {
            throw new CustomerNotFoundException("Customer not found for deletion with ID: " + customerId);
        }
    }
}