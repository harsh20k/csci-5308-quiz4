package org.example;

import org.example.model.Customer;
import org.example.repository.FileCustomerRepository;
import org.example.repository.ICustomerRepository;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String clientDataFile = "/Users/mootez/courses/f25/5308/labs/lab3_software_testing/ecommerce-no-tests/customer_data.txt";
        ICustomerRepository repository = new FileCustomerRepository(clientDataFile);
        Optional<Customer> customerOptional = repository.findById("CUST-1003");
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            System.out.println(customer);
        }
    }
}