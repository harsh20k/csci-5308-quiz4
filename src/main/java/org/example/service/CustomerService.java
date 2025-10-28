package org.example.service;

import org.example.repository.ICustomerRepository;

public class CustomerService {
    private ICustomerRepository customerRepository;
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
