public class Account {
    private String username;
    private String password;
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
}
