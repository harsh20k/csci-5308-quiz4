package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's shopping cart, managing a collection of {@link Product} items.
 * <p>It provides functionality to add and remove products, and calculate the total
 * cost and accumulated loyalty points for all items currently in the cart.</p>
 */
public class ShoppingCart {

    /**
     * The list of {@link Product} items currently in the shopping cart.
     */
    List<Product> items;

    /**
     * The {@link Customer} to whom this shopping cart belongs.
     */
    Customer customer;

    /**
     * Constructs a new ShoppingCart instance, initializing an empty list of items
     * and associating it with the given customer.
     *
     * @param customer The {@link Customer} object associated with this cart.
     */
    public ShoppingCart(Customer customer) {
        this.items = new ArrayList<>();
        this.customer = customer;
    }

    /**
     * Retrieves the list of products currently in the cart.
     *
     * @return A {@code List} of {@link Product} items.
     */
    public List<Product> getItems() {
        return items;
    }

    /**
     * Replaces the current list of items in the cart with a new list.
     *
     * @param items The new {@code List} of {@link Product} items.
     */
    public void setItems(List<Product> items) {
        this.items = items;
    }

    /**
     * Retrieves the customer associated with this shopping cart.
     *
     * @return The associated {@link Customer} object.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer associated with this shopping cart.
     *
     * @param customer The new {@link Customer} object.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Adds a single {@link Product} to the list of items in the cart.
     *
     * @param product The {@link Product} to be added.
     */
    public void addProduct(Product product) {
        this.items.add(product);
    }

    /**
     * Removes a specific instance of a {@link Product} from the cart's item list.
     *
     * @param product The {@link Product} instance to be removed.
     * @return {@code true} if the product was successfully removed, {@code false} otherwise.
     */
    public boolean removeProduct(Product product) {
        return this.items.remove(product);
    }

    /**
     * Calculates the total monetary cost of all items currently in the cart.
     * <p>The total is calculated as the sum of (quantity * price) for every product.</p>
     *
     * @return The total cost as a {@code double}.
     */
    public double getTotal() {
        double total = 0;
        for (Product product: this.items) {
            total += product.getQty() * product.getPrice();
        }

        return total;
    }

    /**
     * Calculates the total number of loyalty points that will be awarded
     * if all items in the cart are purchased.
     *
     * @return The total loyalty points as an {@code int}.
     */
    public int getPoints() {
        int totalPoints = 0;
        for (Product product: this.items) {
            totalPoints += product.getPoints();
        }

        return totalPoints;
    }
}
