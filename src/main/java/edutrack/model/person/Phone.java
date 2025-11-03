package edutrack.model.person;

import static edutrack.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain digits, spaces, hyphens, and an optional '+' prefix. "
            + "Must have at least 3 digits.";
    public static final String VALIDATION_REGEX = "^(\\+)?[\\d\\s-]{2,}$";
    public static final int MAX_PHONE_LENGTH = 20;
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        value = (phone == null || phone.isEmpty()) ? "" : phone;
        checkArgument(isValidPhone(value), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        if (test == null || test.isEmpty()) {
            return true; // Allow null/empty for optional field
        }
        int digitCount = (int) test.chars().filter(Character::isDigit).count();
        return test.matches(VALIDATION_REGEX) && digitCount >= 3;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
