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
        Account adminAccount1 = new Account("Ahmed123", "092233");
        Account adminAccount2 = new Account("Abdulmalik123", "093344");    
        Account adminAccount3 = new Account("Bader123", "094455");
        admin1.setAccount(adminAccount1);
        admin2.setAccount(adminAccount2); 
        admin3.setAccount(adminAccount3);
        store.addUser(admin1);
        store.addUser(admin2);
        store.addUser(admin3);
System.out.println("Welcome to " + store.getName());
    do{
    System.out.println("1. create account"+"\n"+
                       "2. login"+"\n"+
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
                    User newCustomer = new Customer(i, username, username + "@example.com", "Unknown", account);
                    customers[i] = newCustomer;
                    newCustomer.setAccount(account);
                    store.addUser(newCustomer);
                    break;
                    
                }
            }
            System.out.println("Account created successfully!");
            break;
        case 2:
            System.out.print("Enter username: ");
            String loginUsername = sc.next();
            System.out.print("Enter password: ");
            String loginPassword = sc.next();
            for (Account acc : accounts) {
               
                    User user = store.login(loginUsername, loginPassword);
                    if (user != null) {
                        if (user instanceof Admin) {
                            System.out.println("Admin access");
                            do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. View all items");
            System.out.println("2. Add item");
            System.out.println("3. Remove item");
            System.out.println("4. Search item by ID");
            System.out.println("5. Count items recursively");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    store.displayAllItems();
                    break;

                case 2:
                    System.out.println("1. Add Electronic Item\n2. Add grocery Item\n3. go back");
                    int itemType = sc.nextInt();
                    if (itemType == 3) {
                        break;
                    }
                    System.out.print("Enter item ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.println("enter item stock ");
                    int stock = sc.nextInt();
                    System.out.print("Enter item price: ");
                    double price = sc.nextDouble();
                        if (itemType == 1) {
                    System.out.print("Enter warranty years: ");
                    int warranty = sc.nextInt();
                    sc.nextLine();

                    Item item = new ElectronicItem(id, name, price, stock, warranty);

                    if (store.addItem(item)) {
                        System.out.println("Item added successfully.");
                    } else {
                        System.out.println("Failed to add item.");
                    }} else if (itemType == 2) {
                        System.out.print("Enter expiration date: ");
                        String expDate = sc.next();
                        Item item = new GroceryItem(id, name, price, stock, expDate);
                        if (store.addItem(item)) {
                            System.out.println("Item added successfully.");
                        } else {
                            System.out.println("Failed to add item.");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter item ID to remove: ");
                    int removeId = sc.nextInt();
                    sc.nextLine();

                    if (store.removeItem(removeId)) {
                        System.out.println("Item removed successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter item ID to search: ");
                    int searchId = sc.nextInt();
                    sc.nextLine();

                    Item found = store.searchItem(searchId);
                    if (found != null) {
                        System.out.println("Item found: " + found);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 5:
                    System.out.println("Total items = " + cart.countItemRecursive(0));
                    break;

                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (sc.nextInt() != 0);
                            break;
                        } else if (user instanceof Customer) {
                            System.out.println("Customer access");
                            System.out.println("Welcome, " + user.getUsername() + "!");
                            System.out.println("Enter your email: " );
                            String email = sc.next();
                            System.out.println("Enter your location: " );
                            String location = sc.next();
                            ((Customer) user).setLocation(location);
                            ((Customer) user).setEmail(email);
                                    do {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. View all items");
            System.out.println("2. Search item by name");
            System.out.println("3. Add item to cart");
            System.out.println("4. View cart");
            System.out.println("5. Checkout");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
           int  choice = sc.nextInt();
         
            switch (choice) {
                case 1:
                    store.displayAllItems();
                    break;

                case 2:
                    System.out.print("Enter item ID to search: ");
                    int searchId = sc.nextInt();
                  

                    Item found = store.searchItem(searchId);
                    if (found != null) {
                        System.out.println("Item found: " + found);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter item ID to add to cart: ");
                    int itemId = sc.nextInt();
                    

                    Item item = store.searchItem(itemId);
                    if (item != null) {
                        if (cart.addItem(item)) {
                            System.out.println("Item added to cart.");
                        } else {
                            System.out.println("Cart is full.");
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 4:
                    cart.displayCartItems();
                    break;

                case 5:
                    if (cart.countItemRecursive(0) > 0) {
                        Order order = new Order(user, cart);
                        System.out.println("Order placed successfully.");
                        System.out.println(order);
                        cart = new ShoppingCart(); // empty cart after checkout
                    } else {
                        System.out.println("Your cart is empty.");
                    }
                    break;

                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (sc.nextInt() != 0);
break;
                        }
                    }
                        else {
                            System.out.println("Invalid username or password!");
                            break;
                        }
   
            }
            break;
        case 0:
            System.out.println("Goodbye!");
            return;
        default:
            System.out.println("Invalid choice");
    }
    } while (true);
    }
}