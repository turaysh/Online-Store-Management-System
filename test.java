import java.util.Scanner;

/**
 * Main driver class for the online store system.
 * Contains the console-based menu for account creation, login,
 * admin actions, and customer actions.
 */

public class test {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Store store = new Store("My Store");
        
        // Load items from file if available
        FileManager.loadItems(store);
        
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
            System.out.println("===== Account Creation Rules =====");
System.out.println("Username:");
System.out.println("- At least 4 characters");
System.out.println("- No spaces");
System.out.println("- Only letters, numbers, and underscore (_)");

System.out.println("\nPassword:");
System.out.println("- At least 6 characters");
System.out.println("- Must contain at least one number");
System.out.println("==================================\n");
    System.out.print("Enter username: ");
    String username = sc.next();

    if (!store.isValidUsername(username)) {
        System.out.println("Invalid username. It must be at least 4 characters, contain no spaces, and only use letters, numbers, or underscore.");
        break;
    }

    if (store.usernameExists(username)) {
        System.out.println("This username already exists. Please choose another one.");
        break;
    }

    System.out.print("Enter password: ");
    String password = sc.next();

    if (!store.isValidPassword(password)) {
        System.out.println("Invalid password. It must be at least 6 characters and contain at least one number.");
        break;
    }

    sc.nextLine();

    System.out.print("Enter your name: ");
    String name = sc.nextLine();

    System.out.print("Enter your email: ");
    String email = sc.next();

    sc.nextLine();

    System.out.print("Enter your location: ");
    String location = sc.nextLine();

    Account account = new Account(username, password);
    Customer newCustomer = new Customer(store.getNextUserId(), name, email, location, account);

    if (store.addUser(newCustomer)) {
        System.out.println("Account created successfully!");
    } else {
        System.out.println("Failed to create account.");
    }
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
                    sc.nextLine();
                    if (store.itemIdExists(id)) {
                    System.out.println("Item ID already exists.");
                   break;
                    }
                    System.out.print("Enter item name: ");
                    String itemName = sc.nextLine();
                    System.out.println("enter item stock ");
                    int stock = sc.nextInt();
                    System.out.print("Enter item price: ");
                    double price = sc.nextDouble();
                        if (itemType == 1) {
                    System.out.print("Enter warranty years: ");
                    int warranty = sc.nextInt();
                    Item item = new ElectronicItem(id, itemName, price, stock, warranty);

                    if (store.addItem(item)) {
                        System.out.println("Item added successfully.");
                    } else {
                        System.out.println("Failed to add item.");
                    }} else if (itemType == 2) {
                        System.out.print("Enter expiration date: ");
                        String expDate = sc.next();
                        Item item = new GroceryItem(id, itemName, price, stock, expDate);
                        if (store.addItem(item)) {
                            System.out.println("Item added successfully.");
                        } else {
                            System.out.println("Failed to add item.");
                        }
                    }
                    break;

                case 3:
                    store.displayAllItems();
                    System.out.print("Enter item ID to remove: ");
                    int removeId = sc.nextInt();

                    if (store.removeItem(removeId)) {
                        System.out.println("Item removed successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter item Id to search: ");
                    int searchId = sc.nextInt();

                    Item found = store.searchItemById(searchId);
                    if (found != null) {
                        System.out.println("Item found: " + found);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 5:
                    System.out.println("Total items = " + store.countAvailableItemsRecursive(0));
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
            ShoppingCart cart = ((Customer) user).getCart();
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. View all items");
            System.out.println("2. Search item by name");
            System.out.println("3. Add item to cart");
            System.out.println("4. Remove item from cart");
            System.out.println("5. View cart");
            System.out.println("6. Checkout");
            System.out.println("7. View orders");
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

    Item item = store.searchByName(itemName);

    if (item != null) {
        System.out.println(item.toString());
        System.out.println("add to cart? (yes/no)");
        String response = sc.next();

        if (response.equalsIgnoreCase("yes")) {
            if (item.getStock() > 0) {
                if (cart.addItem(item)) {
                    System.out.println("Item added to cart.");
                } else {
                    System.out.println("Cart is full.");
                }
            } else {
                System.out.println("Sorry, this item is out of stock.");
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
                System.out.print("Enter item ID to add to cart: ");
                int itemId = sc.nextInt();
                Item additem = store.searchItemById(itemId);
                    if (additem != null) {
                        if (additem.getStock() > 0) {
                         cart.addItem(additem); 
                         System.out.println("Item added to cart.");
                         additem.setStock(additem.getStock() - 1);
                         } else {
                            System.out.println("Sorry, this item is out of stock.");
                      }
                    } else {
                        System.out.println("Item not found.");
                    }

                    break;
                case 4:
                 cart.displayCartItems();
                System.out.print("Enter item ID to remove from cart: ");
                int removeId = sc.nextInt();

                if (cart.removeItem(removeId)) {
                        Item removedItem = store.searchItemById(removeId);
                        if (removedItem != null) {
                            removedItem.setStock(removedItem.getStock() + 1);
                        }
               System.out.println("Item removed from cart.");
               } else {
                System.out.println("Item not found in cart.");
            }
             break;
                case 5:
                    cart.displayCartItems();
                    break;

                case 6:
                    if (cart.countItemRecursive(0) > 0) {
                        Order order = new Order(user, cart);
                        System.out.println(cart);
                        System.out.println("type 1 to confirm order, type 2 to cancel order");
                        int confirmChoice = sc.nextInt();
                        if (confirmChoice == 1) {
                            System.out.println("Order placed successfully.");
                            ((Customer) user).addOrder(order);
                            store.reduceStockFromCart(cart);
                            cart.clearCart();
                            order.confirmOrder();
                            
                        } else {
                            System.out.println("Order cancelled.");
                        }
                    } else {
                        System.out.println("Your cart is empty.");
                    }
                    break;
                    case 7:
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
            // Save items to file before exiting
            FileManager.saveItems(store);
            System.out.println("Goodbye!");
            
            return;
        default:
            System.out.println("Invalid choice");
    }
    } while (choice != 0);
    }
}