public abstract class User {
    private int id;
    private String name;
    private String email;
    private Account account;
    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getRole(){ // should be abstract!! without body
        return "User";
    }
    public Account setAccount(Account account) {
        this.account = account;
        return account;
    }
    public Account getAccount() {
        return account;
    }
}
