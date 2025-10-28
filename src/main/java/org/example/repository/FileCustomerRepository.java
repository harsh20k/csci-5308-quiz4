package org.example.repository;

import org.example.exceptions.CustomerNotFoundException;
import org.example.model.Address;
import org.example.model.Customer;
import org.example.model.ShoppingCart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A concrete implementation of the {@link ICustomerRepository} that uses a
 * **simple text file on disk** for storage.
 * <p>This implementation handles file I/O and object serialization/deserialization.
 * Note: For simplicity, complex dependencies (ShoppingCart, Address) are omitted
 * from file serialization/deserialization.</p>
 */
public class FileCustomerRepository implements ICustomerRepository {

    // Define the path to the storage file.
    private Path path;
    private static final String DELIMITER = "|";

    public FileCustomerRepository(String repoFile) {
        // Ensure the file exists when the repository is created
        this.path = Paths.get(repoFile);
        if (!Files.exists(this.path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                // In a real application, you would log this critical error.
                throw new RuntimeException("Failed to create customer data file: " + this.path, e);
            }
        }
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        Map<String, Customer> customers = readAllCustomers();
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public void save(Customer customer) {
        Map<String, Customer> customers = readAllCustomers();
        // Overwrite existing or add new customer
        customers.put(customer.getCustomerID(), customer);
        writeAllCustomers(customers);
    }

    @Override
    public void delete(String customerId) throws CustomerNotFoundException {
        Map<String, Customer> customers = readAllCustomers();

        if (customers.remove(customerId) == null) {
            // Check the result of remove to see if the customer was actually present
            throw new CustomerNotFoundException(customerId);
        }

        writeAllCustomers(customers);
    }

    /**
     * Reads all customer data from the disk file and converts it into a Map.
     * This simulates loading the entire "database" into memory.
     *
     * @return A Map of customer ID to Customer objects.
     */
    private Map<String, Customer> readAllCustomers() {
        try (BufferedReader reader = Files.newBufferedReader(this.path)) {
            return reader.lines()
                    .map(this::deserialize)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toMap(Customer::getCustomerID, c -> c));
        } catch (IOException e) {
            // If reading fails, return an empty map and log the error
            System.err.println("Error reading customer data from file: " + e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * Writes all customer data from a Map back to the disk file, overwriting the contents.
     *
     * @param customers The map of customers to write.
     */
    private void writeAllCustomers(Map<String, Customer> customers) {
        try (BufferedWriter writer = Files.newBufferedWriter(this.path)) {
            String content = customers.values().stream()
                    .map(this::serialize)
                    .collect(Collectors.joining(System.lineSeparator()));

            writer.write(content);
        } catch (IOException e) {
            // In a real app, this should throw a checked exception or be robustly handled.
            throw new RuntimeException("Error writing customer data to file.", e);
        }
    }

    /**
     * Converts a Customer object into a delimited String for file storage (Serialization).
     * Format: customerID|points|joinedOn|balance
     */
    private String serialize(Customer customer) {
        return customer.getCustomerID() + DELIMITER +
                customer.getPoints() + DELIMITER +
                customer.getJoinedOn().toString() + DELIMITER +
                customer.getBalance();
    }

    /**
     * Converts a file line (String) back into a Customer object (Deserialization).
     */
    private Optional<Customer> deserialize(String line) {
        try {
            String[] parts = line.split("\\" + DELIMITER);
            if (parts.length != 4) return Optional.empty();

            String customerID = parts[0];
            int points = Integer.parseInt(parts[1]);
            LocalDate joinedOn = LocalDate.parse(parts[2]);
            double balance = Double.parseDouble(parts[3]);

            // Note: ShoppingCart and Address are null/empty, as they are not serialized here.
            Customer customer = new Customer(customerID, joinedOn, new ShoppingCart(null), new Address(), balance);
            customer.setPoints(points); // Set points after construction
            return Optional.of(customer);

        } catch (Exception e) {
            // Log the error and skip the bad line
            System.err.println("Skipping malformed data line: " + line + " Error: " + e.getMessage());
            return Optional.empty();
        }
    }
}
