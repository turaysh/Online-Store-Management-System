import java.util.Scanner;
public class test {
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        Store store = new Store("My Store");
        ShoppingCart cart = new ShoppingCart();
        User admin1 = new Admin(12345678,"Ahmed","ahmed@example.com",5000);
        User admin2 = new Admin(22345678,"Malik","abdulmalik@example.com",4000);
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
        Item item1 = new ElectronicItem(1, "Laptop", 999.99, 1, 2);
        Item item2 = new GroceryItem(2, "Milk", 2.99,   50, "2024-12-31");
        store.addItem(item1);
        store.addItem(item2);
System.out.println("Welcome to " + store.getName());
    int choice;
    do{


    System.out.println("1. create account"+"\n"+
                    "2. login"+"\n"+
                       "0. Exit");
    System.out.print("Enter choice: ");
        choice = sc.nextInt();
    
    switch (choice) {
        case 1:
            System.out.print("Enter username: ");
            String username = sc.next();
            System.out.print("Enter password: ");
            String password = sc.next();
            System.out.println("Enter your email: " );
            String email = sc.next();
            System.out.println("Enter your location: " );
            String location = sc.next();
            Account account = new Account(username, password);
            User newCustomer = new Customer(store.getNextUserId(), username, email, location, account);
            newCustomer.setAccount(account);
            store.addUser(newCustomer);
            System.out.println("Account created successfully!");
                    
                break;
        case 2:
            System.out.print("Enter username: ");
            String loginUsername = sc.next();
            System.out.print("Enter password: ");
            String loginPassword = sc.next();
            
               
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
            choice = sc.nextInt();

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
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.println("enter item stock ");
                    int stock = sc.nextInt();
                    System.out.print("Enter item price: ");
                    double price = sc.nextDouble();
                        if (itemType == 1) {
                    System.out.print("Enter warranty years: ");
                    int warranty = sc.nextInt();

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

                    if (store.removeItem(removeId)) {
                        System.out.println("Item removed successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter item name to search: ");
                    String searchName = sc.next();

                    Item found = store.searchItem(searchName);
                    if (found != null) {
                        System.out.println("Item found: " + found);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 5:
                    System.out.println("Total items = " + store.countAvalibleItems());
                    break;

                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
                            break;
                        } else if (user instanceof Customer) {
                            System.out.println("Customer access");
                            System.out.println("Welcome, " + user.getUsername() + "!");
                                    do {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. View all items");
            System.out.println("2. Search item by name");
            System.out.println("3. Add item to cart");
            System.out.println("4. View cart");
            System.out.println("5. Checkout");
            System.out.println("6. View orders");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
           choice = sc.nextInt();
         
            switch (choice) {
                case 1:
                    store.displayAllItems();
                    break;

                case 2:
   System.out.print("Enter item name to add to cart: ");
                    String itemName = sc.next();
                    

                    Item item = store.searchItem(itemName);
                    if (item != null) {
                        item.getDetails();
                        System.out.println("add to cart? (yes/no)");
                        String response = sc.next();
                        if (response.equalsIgnoreCase("yes")) {
                            if (cart.addItem(item)) {
                                System.out.println("Item added to cart.");
                            } else {
                                System.out.println("Cart is full.");
                            }
                        } else {
                            System.out.println("Item not added to cart.");
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                   store.displayAllItems();
                    System.out.print("Enter item name to add to cart: ");
                    String addItemName = sc.next();
                    Item additem = store.searchItem(addItemName);
                    if (additem != null) {
                        if (cart.addItem(additem)) {
                            if(additem.getStock() == 0) {
                                System.out.println("Sorry, this item is out of stock.");
                                cart.removeItem(additem.getId());
                            } else {
                                System.out.println("Item added to cart.");
                            }
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
                        System.out.println(order);
                        System.out.println("type 1 to confirm order, type 2 to cancel order");
                        int confirmChoice = sc.nextInt();
                        if (confirmChoice == 1) {
                            System.out.println("Order placed successfully.");
                            ((Customer) user).addOrder(order);
                            store.reduceStockFromCart(cart);
                            cart.clearCart();
                            
                        } else {
                            System.out.println("Order cancelled.");
                        }
                    } else {
                        System.out.println("Your cart is empty.");
                    }
                    break;
                    case 6:
                    System.out.println("Your Orders:");
                    
                    ((Customer) user).viewOrders(); 
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
break;
                        }
                    }
                        else {
                            System.out.println("Invalid username or password!");
                            break;
                        }
   
            
            break;
        case 0:
            System.out.println("Goodbye!");
            
            return;
        default:
            System.out.println("Invalid choice");
    }
    } while (choice != 0);
    }
}