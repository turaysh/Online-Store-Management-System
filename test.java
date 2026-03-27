import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = new Store("My Store");
        ShoppingCart cart = new ShoppingCart();
        Account accounts[] = new Account[1000];
        User customers[] = new Customer[1000];
        User admin1 = new Admin(12345678,"Ahmed","ahmed@example.com",5000);
        User admin2 = new Admin(22345678,"Abdulmalik","abdulmalik@example.com",4000);
        User admin3 = new Admin(32345678,"Bader","bader@example.com",2000);
System.out.println("Welcome to " + store.getName());
    do{
    System.out.println("1. create account"+"/n"+
                       "2. login"+"/n"+
                       "0. Exit");
    System.out.print("Enter choice: ");
    switch (sc.nextInt()) {
        case 1:
            System.out.print("Enter username: ");
            String username = sc.next();
            System.out.print("Enter password: ");
            String password = sc.next();
            Account account = new Account(username, password);
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i] == null) {
                    accounts[i] = account;
                    break;
                    
                }
            }
            System.out.println("Account created successfully!");
            for (User cst : customers) {
                if (cst.getAccount() == null ) {
                    cst.setAccount(account);
                    
                    break;
                }
            }
            break;
        case 2:
            System.out.print("Enter username: ");
            String loginUsername = sc.next();
            System.out.print("Enter password: ");
            String loginPassword = sc.next();
            for (Account acc : accounts) {
                if (acc != null && acc.login(loginUsername, loginPassword)) {
                    System.out.println("Login successful!");
                    break;
                }
   
            }
            System.out.println("Login successful!");
            break;
        case 0:
            System.out.println("Goodbye!");
            return;
        default:
            System.out.println("Invalid choice");
            if()
    }while(sc.nextInt() !=0);
   
}
}
}