package org.example.model;

/**
 * Represents a physical address entity within the system, typically associated
 * with a {@link Customer}.
 *
 * <p>This class encapsulates standard geographical data points required for
 * mailing, billing, or shipping purposes.</p>
 */
public class Address {

    /**
     * The unique identifier for this Address record in the database.
     */
    private int id;

    /**
     * The primary street address line (e.g., house number, street name, and apartment/suite number).
     */
    private String addressLine1;

    /**
     * The city or town where the address is located.
     */
    private String city;

    /**
     * The province, state, or region where the address is located.
     */
    private String province;

    /**
     * The postal code or ZIP code associated with the address.
     */
    private String postalCode;

    /**
     * The customer associated with this address.
     */
    private Customer customer;

    /**
     * Default no-argument constructor for creating an Address instance.
     */
    public Address() {
    }

    /**
     * Constructs a new Address instance with all required fields initialized.
     *
     * @param id The unique identifier for the address.
     * @param addressLine1 The primary street address line.
     * @param city The city.
     * @param province The province or state.
     * @param postalCode The postal code or ZIP code.
     * @param customer The customer object associated with this address.
     */
    public Address(int id, String addressLine1, String city, String province, String postalCode, Customer customer) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.customer = customer;
    }

    /**
     * Retrieves the unique identifier of the address.
     *
     * @return The address ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the address.
     *
     * @param id The new address ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the primary street address line.
     *
     * @return The address line 1 string.
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the primary street address line.
     *
     * @param addressLine1 The new address line 1 string.
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Retrieves the city where the address is located.
     *
     * @return The city string.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the address is located.
     *
     * @param city The new city string.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the province or state where the address is located.
     *
     * @return The province/state string.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the province or state where the address is located.
     *
     * @param province The new province/state string.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Retrieves the postal code or ZIP code.
     *
     * @return The postal code string.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code or ZIP code.
     *
     * @param postalCode The new postal code string.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Retrieves the {@link Customer} associated with this address.
     *
     * @return The associated Customer object.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the {@link Customer} associated with this address.
     *
     * @param customer The new Customer object.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
