import java.io.*;

/**
 * Handles file operations for saving and loading store items and customer accounts.
 * Uses text files with BufferedReader and BufferedWriter to support file handling in Phase 2.
 */
class FileManager {
    private static final String ITEM_FILE = "items.txt";
    private static final String USER_FILE = "users.txt";

    /**
     * Saves all store data to disk, including items and customer accounts.
     *
     * @param store the store to save
     * @return true if both item and user files were saved successfully, otherwise false
     */
    public static boolean saveAll(Store store) {
        boolean itemsSaved = saveItems(store);
        boolean usersSaved = saveUsers(store);
        return itemsSaved && usersSaved;
    }

    /**
     * Saves all items from the store to a text file.
     * Each item is converted into a formatted text line before saving.
     *
     * @param store the store containing items to save
     * @return true if items are saved successfully, otherwise false
     */
    public static boolean saveItems(Store store) {
        if (store == null) {
            System.out.println("Unable to save items: store reference is null.");
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_FILE))) {
            LinkedListNode<Item> current = store.getItemsHead();
            while (current != null) {
                Item item = current.getData();
                if (item != null) {
                    String line = formatItemLine(item);
                    writer.write(line);
                    writer.newLine();
                }
                current = current.getNext();
            }
            System.out.println("Items saved to " + ITEM_FILE);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
            return false;
        }
    }

    /**
     * Saves all customer accounts from the store to a text file.
     * Only Customer objects are saved because admin accounts are created by the program.
     *
     * @param store the store containing users to save
     * @return true if users are saved successfully, otherwise false
     */
    public static boolean saveUsers(Store store) {
        if (store == null) {
            System.out.println("Unable to save users: store reference is null.");
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            LinkedListNode<User> current = store.getUsersHead();
            while (current != null) {
                User user = current.getData();
                if (user instanceof Customer) {
                    String line = formatUserLine(user);
                    writer.write(line);
                    writer.newLine();
                }
                current = current.getNext();
            }
            System.out.println("Users saved to " + USER_FILE);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads customer accounts from a text file into the store.
     * Invalid or duplicate records are skipped to keep the store data valid.
     *
     * @param store the store to load users into
     * @return true if the user file is read successfully, otherwise false
     */
    public static boolean loadUsers(Store store) {
        if (store == null) {
            System.out.println("Unable to load users: store reference is null.");
            return false;
        }

        File file = new File(USER_FILE);
        if (!file.exists()) {
            System.out.println("No existing user file found. Starting with default users.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int loadedCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                User user = parseUserLine(line);
                if (user instanceof Customer) {
                    Customer customer = (Customer) user;
                    if (!store.usernameExists(customer.getUsername())) {
                        if (store.addUser(customer)) {
                            loadedCount++;
                        }
                    }
                }
            }
            System.out.println("Loaded " + loadedCount + " users from " + USER_FILE);
            return true;
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return false;
        }
    }

    /**
     * Formats a customer object into one line that can be saved in the users file.
     * Format: CUSTOMER|id|name|email|location|username|password
     *
     * @param user the customer user to format
     * @return a formatted string that represents the customer account
     */
    private static String formatUserLine(User user) {
        Customer customer = (Customer) user;
        StringBuilder sb = new StringBuilder();
        sb.append("CUSTOMER").append("|");
        sb.append(customer.getId()).append("|");
        sb.append(customer.getName()).append("|");
        sb.append(customer.getEmail()).append("|");
        sb.append(customer.getlocation()).append("|");
        sb.append(customer.getUsername()).append("|");
        sb.append(customer.getPassword());
        return sb.toString();
    }

    /**
     * Parses one line from the users file and converts it into a Customer object.
     * Invalid records return null so the loading process can skip them safely.
     *
     * @param line the user record line read from the file
     * @return a Customer object if the record is valid, otherwise null
     */
    private static User parseUserLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        try {
            String[] parts = line.split("\\|");
            if (parts.length != 7) {
                System.out.println("Invalid user record, skipping line: " + line);
                return null;
            }

            String type = parts[0].trim();
            if (!"CUSTOMER".equals(type)) {
                System.out.println("Unsupported user type, skipping line: " + line);
                return null;
            }

            int id = Integer.parseInt(parts[1].trim());
            String name = parts[2].trim();
            String email = parts[3].trim();
            String location = parts[4].trim();
            String username = parts[5].trim();
            String password = parts[6].trim();

            Customer customer = new Customer(id, name, email, location, new Account(username, password));
            return customer;
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric value in user record, skipping line: " + line);
            return null;
        }
    }

    /**
     * Loads items from a text file into the store.
     * Duplicate item IDs are handled using DuplicateItemException.
     *
     * @param store the store to load items into
     * @return true if the item file is read successfully, otherwise false
     */
    public static boolean loadItems(Store store) {
        if (store == null) {
            System.out.println("Unable to load items: store reference is null.");
            return false;
        }

        File file = new File(ITEM_FILE);
        if (!file.exists()) {
            System.out.println("No existing item file found. Starting fresh.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int loadedCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Item item = parseItemLine(line);
                if (item != null) {
                    try {
                        if (store.addItem(item)) {
                            loadedCount++;
                        }
                    } catch (DuplicateItemException e) {
                        System.out.println("Skipping duplicate item from file: " + e.getMessage());
                    }
                }
            }
            System.out.println("Loaded " + loadedCount + " items from " + ITEM_FILE);
            return true;
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
            return false;
        }
    }

    /**
     * Formats an item object into one line that can be saved in the items file.
     * Format: type|id|name|price|stock|extra.
     * The extra field stores warranty months for electronic items or expiry date for grocery items.
     *
     * @param item the item to format
     * @return a formatted string that represents the item
     */
    private static String formatItemLine(Item item) {
        StringBuilder sb = new StringBuilder();
        
        // Determine item type
        String type = "ITEM";
        if (item instanceof ElectronicItem) {
            type = "ELECTRONIC";
            sb.append(type).append("|");
            sb.append(item.getId()).append("|");
            sb.append(item.getName()).append("|");
            sb.append(item.getPrice()).append("|");
            sb.append(item.getStock()).append("|");
            sb.append(((ElectronicItem) item).getWarrantyMonths());
        } else if (item instanceof GroceryItem) {
            type = "GROCERY";
            sb.append(type).append("|");
            sb.append(item.getId()).append("|");
            sb.append(item.getName()).append("|");
            sb.append(item.getPrice()).append("|");
            sb.append(item.getStock()).append("|");
            sb.append(((GroceryItem) item).getExpiryDate());
        } else {
            sb.append(type).append("|");
            sb.append(item.getId()).append("|");
            sb.append(item.getName()).append("|");
            sb.append(item.getPrice()).append("|");
            sb.append(item.getStock());
        }
        
        return sb.toString();
    }

    /**
     * Parses one line from the items file and converts it into an Item object.
     * Invalid records return null so the loading process can skip them safely.
     *
     * @param line the item record line read from the file
     * @return an Item object if the record is valid, otherwise null
     */
    private static Item parseItemLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        try {
            String[] parts = line.split("\\|");
            if (parts.length < 5) {
                System.out.println("Invalid item record, skipping line: " + line);
                return null;
            }

            String type = parts[0].trim();
            int id = Integer.parseInt(parts[1].trim());
            String name = parts[2].trim();
            double price = Double.parseDouble(parts[3].trim());
            int stock = Integer.parseInt(parts[4].trim());

            switch (type) {
                case "ELECTRONIC":
                    if (parts.length < 6) {
                        System.out.println("Invalid ElectronicItem record, skipping line: " + line);
                        return null;
                    }
                    int warranty = Integer.parseInt(parts[5].trim());
                    return new ElectronicItem(id, name, price, stock, warranty);
                case "GROCERY":
                    if (parts.length < 6) {
                        System.out.println("Invalid GroceryItem record, skipping line: " + line);
                        return null;
                    }
                    String expiry = parts[5].trim();
                    return new GroceryItem(id, name, price, stock, expiry);
                default:
                    return new Item(id, name, price, stock) {
                        // Anonymous Item subclass for generic items
                    };
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric value in item record, skipping line: " + line);
            return null;
        }
    }
}
