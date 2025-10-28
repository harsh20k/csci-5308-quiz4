package org.example.service;

import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.ShoppingCart;
import org.example.repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//assign to team-meber
//update status
//isoverdue():

class TaskServiceTest {

    @Test
    void test_when_assigned_to_team_member()
    {
        addTaskToMeber(memberId);
    }
}


class ShoppingCartServiceTest {

    private ShoppingCartService shoppingCartService;
    private ICustomerRepository customerRepository;
    private IAuditService auditService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(ICustomerRepository.class);
        auditService = mock(IAuditService.class);
        shoppingCartService = new ShoppingCartService(customerRepository, auditService);
    }

    @Test
    void test_when_client_has_zero_points() {
        Product rtx4090 = new Product("RTX 4090", "1111", 2000.0, 0, 1);
        Customer customer = new Customer("CUST-1008", LocalDate.now(), null,null, 1000);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        shoppingCart.addProduct(rtx4090);
        customer.setShoppingCart(shoppingCart);

        when(this.customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));

        double expectedTotal = 2300.0;
        double actualTotal = this.shoppingCartService.calculateTotal(customer.getCustomerID());

        assertEquals(expectedTotal, actualTotal);

        verify(this.auditService, times(1)).logInfo(
                eq("CALCULATION_SUCCESS"),
                contains("2300.0 for customer CUST-1008")
        );


    }

    @Test
    void test_calculate_total_with_2500_points() {
        Product rtx4090 = new Product("RTX 4090", "111", 2000.0, 0, 1);
        Customer customer = new Customer("CUST-1008", LocalDate.now(), null,null, 1000);
        customer.setPoints(2500);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        shoppingCart.addProduct(rtx4090);
        customer.setShoppingCart(shoppingCart);

        double expectedTotal = 2242.5;
        when(this.customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));
        double actualTotal = this.shoppingCartService.calculateTotal(customer.getCustomerID());

        assertEquals(expectedTotal, actualTotal);
        verify(auditService, times(1)).logInfo(
                eq("CALCULATION_SUCCESS"),
                contains("2242.5 for customer CUST-1008")
        );

    }

    @Test
    void test_calculate_total_when_customer_exceeds_cap() {
        Product rtx4090 = new Product("RTX 4090", "111", 2000.0, 0, 1);
        Customer customer = new Customer("CUST-1008", LocalDate.now(), null,null, 1000);
        customer.setPoints(25000);
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        shoppingCart.addProduct(rtx4090);
        customer.setShoppingCart(shoppingCart);

        double expectedTotal = 1840.0;
        when(this.customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));
        double actualTotal = this.shoppingCartService.calculateTotal(customer.getCustomerID());

        assertEquals(expectedTotal, actualTotal);
    }

}