package org.example.repository;

import org.example.exceptions.CustomerNotFoundException;
import org.example.model.Customer;
import java.util.Optional;

/**
 * Defines the contract for accessing and managing customer data.
 * All persistence implementations (File, Database, In-Memory Fake) must
 * adhere to this interface.
 */
public interface ICustomerRepository {

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param customerId The ID of the customer to find.
     * @return An {@link Optional} containing the Customer if found, or an empty Optional otherwise.
     */
    Optional<Customer> findById(String customerId);

    /**
     * Saves a new customer or updates an existing customer.
     *
     * @param customer The {@link Customer} object to be persisted.
     */
    void save(Customer customer);

    /**
     * Deletes a customer by their unique ID.
     *
     * @param customerId The ID of the customer to delete.
     * @throws CustomerNotFoundException if the customer with the given ID does not exist.
     */
    void delete(String customerId) throws CustomerNotFoundException;
}
