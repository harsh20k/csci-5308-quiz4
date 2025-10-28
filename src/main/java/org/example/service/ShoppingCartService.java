package org.example.service;

import org.example.model.Customer;
import org.example.model.ShoppingCart;
import org.example.repository.ICustomerRepository;

import java.util.Optional;

public class ShoppingCartService {
    private static final double TAX = 0.15;
    private static final double MAX_POINTS = 20_000;
    private ICustomerRepository customerRepository;
    private IAuditService auditService;
    public ShoppingCartService(ICustomerRepository customerRepository, IAuditService auditService) {
        this.customerRepository = customerRepository;
        this.auditService = auditService;
    }
    public double calculateTotal(String customerID) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        Customer customerInstance = customer.get();
        ShoppingCart shoppingCart = customerInstance.getShoppingCart();
        double total = shoppingCart.getTotal();
        double discountPointsFlour = Math.min((double) customerInstance.getPoints(), MAX_POINTS);
        double discountFraction = ( discountPointsFlour / 1000) / 100;
        total = total - total * discountFraction;
        double totalAfterTax = total + TAX * total;
        auditService.logInfo("CALCULATION_SUCCESS", String.format("%.1f for customer %s", totalAfterTax, customerID));
        return totalAfterTax;
    }
}
