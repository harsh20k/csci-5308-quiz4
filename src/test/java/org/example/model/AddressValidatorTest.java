package org.example.model;

import org.example.exceptions.InvalidCivicAddressException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AddressValidatorTest {

    AddressValidator addressValidator;
    @BeforeEach
    void setUp() {
        this.addressValidator = new AddressValidator();
    }

    @Test
    void test_address_with_valid_civic_address() {
     // Arrange
     Address address = new Address(
             1, "6050 University Ave.", "Halifax", "NS", "B3H 1W5", null
     );
     // Act
        boolean outcome = this.addressValidator.isCivicAddressValid(address.getAddressLine1());
    // Assert
        assertTrue(outcome);
    }

    @Test
    void test_address_with_invalid_civic_address() {
        Address address = new Address(
                1, "X6050 University Ave.", "Halifax", "NS", "B3H 1W5", null
        );
        // Act
        boolean outcome = this.addressValidator.isCivicAddressValid(address.getAddressLine1());
        assertFalse(outcome);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ave.", "Blvd."})
    void test_address_with_valid_street_types(String streetType) {
        String civicAdress = String.format("123 Main %s", streetType);
        boolean outcome = this.addressValidator.isCivicAddressValid(civicAdress);
        assertTrue(outcome);
    }

    @Test
    void test_invalid_civic_address_exception_thrown() {
        Address address = new Address(
                1, "X6050 University Ave.", "Halifax", "NS", "B3H 1W5", null
        );
        Exception excetion = assertThrows(InvalidCivicAddressException.class, () -> this.addressValidator.isValidAddress(address));
    }


}