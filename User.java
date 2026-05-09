/**
 * Abstract base class that represents any user in the store system.
 * It stores common information such as id, name, email, and account.
 */

public abstract class User {
    private int id;
    private String name;
    private String email;
    private Account account;

    /**
     * Creates a new user with basic information.
     *
     * @param id the user ID
     * @param name the user name
     * @param email the user email
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the role of the user.
     * Must be implemented by subclasses.
     *
     * @return the role name
     */
    public abstract String getRole();

    /**
     * Returns the user ID.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the user name.
     *
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Assigns an account to this user.
     *
     * @param account the account to assign
     * @return the assigned account
     */
    public Account setAccount(Account account) {
        this.account = account;
        return account;
    }

    /**
     * Returns the account assigned to this user.
     *
     * @return the user account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Returns the user email.
     *
     * @return the user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the username from the user's account.
     *
     * @return the username if an account exists, otherwise null
     */
    public String getUsername() {
        return account != null ? account.getUsername() : null;
    }

    /**
     * Returns the password from the user's account.
     *
     * @return the password if an account exists, otherwise null
     */
    public String getPassword() {
        return account != null ? account.getPassword() : null;
    }

    /**
     * Updates the user's email address.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
