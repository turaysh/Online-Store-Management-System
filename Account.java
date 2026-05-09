/**
 * Represents a login account for a user in the online store system.
 * It stores the username and password used for authentication.
 */
public class Account {
    private String username;
    private String password;

    /**
     * Creates a new account with a username and password.
     *
     * @param username the username of the account
     * @param password the password of the account
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Checks whether the entered username and password match this account.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if the login information is correct, otherwise false
     */
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns the username of this account.
     *
     * @return the account username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of this account.
     *
     * @return the account password
     */
    public String getPassword() {
        return password;
    }
}
