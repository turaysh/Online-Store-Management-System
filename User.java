/**
 * Abstract base class that represents any user in the store system.
 * It stores common information such as id, name, email, and account.
 */

public abstract class User {
    private int id;
    private String name;
    private String email;
    private Account account;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    /**
     * Returns the role of the user.
     * Must be implemented by subclasses.
     *
     * return the role name
     */
    public abstract String getRole();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
      /**
     * Assigns an account to this user.
     *
     * param account the account to assign
     * return the assigned account
     */
    public Account setAccount(Account account) {
        this.account = account;
        return account;
    }

    public Account getAccount() {
        return account;
    }

    public String getUsername() {
        return account != null ? account.getUsername() : null;
    }

    public String getPassword() {
        return account != null ? account.getPassword() : null;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
