package com.prog5121.poe;

import java.util.regex.Pattern;

/**
 * Login.java
 *
 * PROG5121 Portfolio of Evidence - Part 1: Registration and Login feature.
 *
 * This class handles user registration and login for a chat application.
 * It validates a username, password, and South African cell phone number
 * against the rules supplied in the PoE brief, stores the registered
 * user's details in memory, and verifies login attempts against those
 * stored details.
 *
 * @author  Andile Ndlovu ST10452270
 */
public class Login {

    // Fields captured at registration
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Tracks whether a user has successfully registered, so login can
    // check against the stored details.
    private boolean isRegistered = false;

    // ---- Regular expressions -------------------------------------------------

    // Username: must contain an underscore and be no more than five
    // characters long in total.
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*_).{1,5}$");

    // Password: at least eight characters, at least one capital letter,
    // at least one number, and at least one special character.
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$");

    // South African cell phone number: must start with the international
    // country code "+27" followed by the subscriber number, which must be
    // no more than ten digits long.
    //
    // Reference: Baeldung (2023) 'Validate Phone Numbers with Regex in
    // Java', Baeldung. Available at:
    // https://www.baeldung.com/java-regex-validate-phone-numbers
    // (Accessed: 15 September 2026). The general pattern structure
    // (escaped "+" followed by a bounded digit quantifier) was adapted
    // from this source to fit the +27 South African country code and the
    // ten-digit subscriber-number limit specified in the PoE brief.
    private static final Pattern CELLPHONE_PATTERN = Pattern.compile("^\\+27\\d{1,10}$");

    // ---- No-argument constructor ----------------------------------------------

    public Login() {
    }

    // ---- Setters used to load candidate registration data before validation ---

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // ---- Getters ----------------------------------------------------------

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // ---- Validation methods -------------------------------------------------

    /**
     * Checks that the username contains an underscore and is no more than
     * five characters long.
     *
     * @return true if the username is correctly formatted, false otherwise.
     */
    public boolean checkUserName() {
        if (username == null) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username).matches();
    }

    /**
     * Checks that the password is at least eight characters long and
     * contains a capital letter, a number, and a special character.
     *
     * @return true if the password meets the complexity rules, false otherwise.
     */
    public boolean checkPasswordComplexity() {
        if (password == null) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Checks that the cell phone number contains the South African
     * international country code (+27) followed by a subscriber number
     * that is no more than ten characters long.
     *
     * @return true if the cell phone number is correctly formatted, false otherwise.
     */
    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null) {
            return false;
        }
        return CELLPHONE_PATTERN.matcher(cellPhoneNumber).matches();
    }

    // ---- Registration ---------------------------------------------------------

    /**
     * Validates the username, password, and cell phone number captured via
     * the setters, and registers the user if all three are correctly
     * formatted.
     *
     * @return the appropriate registration outcome message.
     */
    public String registerUser() {
        boolean userNameOk = checkUserName();
        boolean passwordOk = checkPasswordComplexity();
        boolean cellOk = checkCellPhoneNumber();

        if (!userNameOk) {
            return "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.";
        }

        if (!passwordOk) {
            return "Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number, and a "
                    + "special character.";
        }

        if (!cellOk) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // All conditions met - register the user.
        isRegistered = true;
        return "User successfully registered.";
    }

    // ---- Login ------------------------------------------------------------

    /**
     * Verifies that the given login details match the details captured
     * during registration.
     *
     * @param loginUsername the username entered at login.
     * @param loginPassword the password entered at login.
     * @return true if the details match a registered user, false otherwise.
     */
    public boolean loginUser(String loginUsername, String loginPassword) {
        if (!isRegistered) {
            return false;
        }
        return this.username != null
                && this.password != null
                && this.username.equals(loginUsername)
                && this.password.equals(loginPassword);
    }

    /**
     * Returns the appropriate message for a login attempt.
     *
     * @param loginSuccessful the result of loginUser().
     * @return the welcome message on success, or an error message on failure.
     */
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
