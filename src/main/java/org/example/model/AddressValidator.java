package org.example.model;

import org.example.exceptions.InvalidCivicAddressException;
import org.example.exceptions.InvalidPostalCodeException;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Validates the syntax and structure of an {@link Address} object based on a
 * set of simplified business rules.
 *
 * This validator provides checks for civic address structure (street number and street type)
 * and postal code validity.
 */
public class AddressValidator {
    // Regex rules for postal code validation
    // L_ALL: Letters allowed in the first position (A, B, C, E, G, H, J, K, L, M, N, P, R, S, T, V, W, X, Y, Z)
    private static final String L_ALL = "[ABCEGHJKLMNPRSTVWXYZ]";
    // L_RESTRICTED: Letters allowed in the third and sixth positions (LNL_NLN) - excludes W and Z
    private static final String L_RESTRICTED = "[ABCEGHJKLMNPRSTVXY]";

    // N: Digit [0-9]
    private static final String N = "\\d";

    // Combined Regex Pattern: L_ALL N L_RESTRICTED \s? N L_RESTRICTED N
    // (?i) makes the pattern case-insensitive.
    private static final String POSTAL_CODE_REGEX =
            "(?i)^" + L_ALL + N + L_RESTRICTED + "\\s?" + N + L_RESTRICTED + N + "$";

    private static final Pattern PATTERN = Pattern.compile(POSTAL_CODE_REGEX);

    /**
     * Validates the structure of a single civic address line (e.g., "6050 University Ave.").
     *
     * <p>The address is considered valid if, and only if, it contains at least
     * two spaces (separating the street number, street name, and street type)
     * AND the first word is a valid number AND the last word is a known street type.</p>
     *
     * @param address The address string (e.g., AddressLine1) to validate.
     * @return {@code true} if the address conforms to the civic address structure rules;
     * {@code false} otherwise.
     */
    public boolean isCivicAddressValid(String address) {
        int firstIndex = address.indexOf(" ");
        int lastIndex = address.lastIndexOf(" ");
        if (firstIndex == lastIndex || firstIndex == -1) {
            return false;
        }
        String streetNbr = address.substring(0, firstIndex);
        boolean isStNbrValid = this.isNumberValid(streetNbr);

        String streetType = address.substring(lastIndex + 1, address.length());
        boolean validStreetType = this.isStreetTypeValid(streetType);

        boolean finalResult = isStNbrValid && validStreetType;

        return finalResult;
    }

    /**
     * Checks if the provided postal code string is valid.
     * Canadian postal code rules:
     * 1. Format is LNL NLN (Letter, Number, Letter, Space, Number, Letter, Number).
     * 2. Letters D, F, I, O, Q, U are never used.
     * 3. Letters W and Z are only used in the first position (the Forward Sortation Area).
     * 4. The space is optional for validation purposes, allowing A1A1A1 and A1A 1A1.
     *
     * @param postalCode The postal code string to validate.
     * @return {@code false} (always, in this current implementation).
     */
    public boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null) {
            return false;
        }
        // Ensure input is trimmed before checking against the strict LNL NLN regex pattern
        return PATTERN.matcher(postalCode.trim()).matches();
    }

    /**
     * Helper method to determine if a string can be successfully parsed as an integer.
     *
     * @param number The string presumed to be a numeric house number.
     * @return {@code true} if the string is a valid integer; {@code false} otherwise.
     */
    private boolean isNumberValid(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Helper method to determine if a string matches one of the known, abbreviated
     * street types (e.g., "St.", "Ave.").
     *
     * @param streetType The string presumed to be a street type abbreviation.
     * @return {@code true} if the type is in the predefined set; {@code false} otherwise.
     */
    private boolean isStreetTypeValid(String streetType) {
        Set<String> types = Set.of(
                "St.", "Ave.", "Blvd.", "Dr.", "Rd."
        );

        return types.contains(streetType);
    }

    public boolean isValidAddress(Address address) {
        if (address == null) {
            return false;
        }
        boolean isCivicAddressValid = this.isCivicAddressValid(address.getAddressLine1());
        if (!isCivicAddressValid) {
            throw new InvalidCivicAddressException(address.getAddressLine1());
        }
        boolean isPostalCodeValid = this.isPostalCodeValid(address.getPostalCode());
        if (!isPostalCodeValid) {
            throw new InvalidPostalCodeException(address.getPostalCode());
        }
        return isCivicAddressValid && isPostalCodeValid;
    }
}
