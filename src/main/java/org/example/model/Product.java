package org.example.model;

/**
 * Represents a product or item available for sale.
 *
 * <p>This class encapsulates core product attributes such as name, pricing,
 * inventory quantity, and associated loyalty points.</p>
 */
public class Product {
    /**
     * The descriptive name of the product.
     */
    private String name;

    /**
     * The unique identifier or serial number for inventory tracking.
     */
    private String serialNumber;

    /**
     * The monetary price of the product per unit.
     */
    private double price;

    /**
     * The quantity of this product currently being tracked or held (defaults to 1).
     */
    private int qty = 1;

    /**
     * The number of loyalty points a customer earns when purchasing this product (per point).
     */
    private int points;

    /**
     * Constructs a new Product instance with essential details.
     *
     * @param name The descriptive name of the product.
     * @param serialNumber The unique serial number for the product.
     * @param price The price of the product per unit.
     * @param points The number of loyalty points awarded upon purchase.
     * @param qty the quantity of the item to be purchased
     */
    public Product(String name, String serialNumber, double price, int points, int qty) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.price = price;
        this.points = points;
        this.qty = qty;
    }

    /**
     * Retrieves the descriptive name of the product.
     *
     * @return The product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the descriptive name of the product.
     *
     * @param name The new product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unique serial number of the product.
     *
     * @return The serial number string.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the unique serial number of the product.
     *
     * @param serialNumber The new serial number string.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Retrieves the monetary price of the product per unit.
     *
     * @return The product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the monetary price of the product per unit.
     *
     * @param price The new product price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of this product being tracked.
     *
     * @param qty The new quantity.
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Retrieves the quantity of this product being tracked.
     *
     * @return The current quantity.
     */
    public int getQty() {
        return qty;
    }

    /**
     * Retrieves the number of loyalty points awarded for purchasing this product.
     *
     * @return The loyalty points value.
     */
    public int getPoints() {
        return this.qty * this.points;
    }

    /**
     * Sets the number of loyalty points awarded for purchasing this product.
     *
     * @param points The new loyalty points value.
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
