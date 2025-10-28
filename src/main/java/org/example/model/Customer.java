package org.example.model;

import java.time.LocalDate;

/**
 * Represents a customer entity in the system, holding details such as
 * identification, loyalty points, account status, and associated objects
 * like their shopping cart and address.
 */
public class Customer {

    /**
     * The unique identifier for the customer.
     */
    private String customerID;

    /**
     * The number of loyalty points the customer has accumulated.
     * Initialized to 0 point upon creation.
     */
    private int points = 0;

    /**
     * The date the customer account was created or joined the system.
     */
    private LocalDate joinedOn;

    /**
     * The customer's current {@link ShoppingCart} containing items for purchase.
     */
    private ShoppingCart shoppingCart;

    /**
     * The primary {@link Address} associated with the customer (e.g., billing or shipping).
     */
    private Address address;

    /**
     * The current monetary balance available to the customer for transactions.
     */
    private double balance;

    /**
     * Constructs a new Customer instance, initializing all required fields.
     *
     * @param customerID The unique identifier string for the customer.
     * @param joinedOn The date the customer joined.
     * @param shoppingCart The customer's associated shopping cart.
     * @param address The customer's primary address.
     * @param balance The initial monetary balance available to the customer.
     */
    public Customer(String customerID, LocalDate joinedOn, ShoppingCart shoppingCart, Address address, double balance) {
        this.customerID = customerID;
        this.joinedOn = joinedOn;
        this.shoppingCart = shoppingCart;
        this.address = address;
        this.balance = balance;
    }

    /**
     * Retrieves the unique identifier of the customer.
     *
     * @return The customer ID string.
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param customerID The new customer ID string.
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * Retrieves the current number of loyalty points the customer holds.
     *
     * @return The current point total.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the customer's total number of loyalty points.
     *
     * @param points The new point total.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retrieves the date the customer joined the system.
     *
     * @return The join date.
     */
    public LocalDate getJoinedOn() {
        return joinedOn;
    }

    /**
     * Sets the date the customer joined the system.
     *
     * @param joinedOn The new join date.
     */
    public void setJoinedOn(LocalDate joinedOn) {
        this.joinedOn = joinedOn;
    }

    /**
     * Retrieves the customer's current shopping cart.
     *
     * @return The associated {@link ShoppingCart} object.
     */
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Sets the customer's shopping cart.
     *
     * @param shoppingCart The new {@link ShoppingCart} object.
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Sets the customer's current monetary balance.
     *
     * @param balance The new balance amount.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Retrieves the customer's current monetary balance.
     *
     * @return The current balance amount.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Retrieves the customer's primary address.
     *
     * @return The associated {@link Address} object.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the customer's primary address.
     *
     * @param address The new {@link Address} object.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}
