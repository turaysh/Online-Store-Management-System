public abstract class User {
    private int id;
    private String name;
    protected String email;
    private Account account;
    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getRole(){
        return "User";
    }
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
