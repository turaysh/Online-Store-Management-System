import java.io.*;

/**
 * Handles file operations for saving and loading store items.
 * Uses FileWriter, BufferedWriter, FileReader, and BufferedReader.
 */
class FileManager {
    private static final String ITEM_FILE = "items.txt";

    /**
     * Saves all items from the store to a text file.
     * Format: type|id|name|price|stock
     *
     * @param store the store containing items to save
     * @return true if saved successfully, otherwise false
     */
    public static boolean saveItems(Store store) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(ITEM_FILE));
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
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads items from a text file into the store.
     *
     * @param store the store to load items into
     * @return true if loaded successfully, otherwise false
     */
    public static boolean loadItems(Store store) {
        File file = new File(ITEM_FILE);
        if (!file.exists()) {
            System.out.println("No existing item file found. Starting fresh.");
            return false;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(ITEM_FILE));
            int loadedCount = 0;
            String line;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Item item = parseItemLine(line);
                if (item != null && store.addItem(item)) {
                    loadedCount++;
                }
            }
            System.out.println("Loaded " + loadedCount + " items from " + ITEM_FILE);
            return true;
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
            return false;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    /**
     * Formats an item into a string line for file storage.
     * Format: type|id|name|price|stock or type|id|name|price|stock|extra
     * Extra field: warrantyMonths for Electronic, expiryDate for Grocery
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
     * Parses a line from the file back into an Item object.
     */
    private static Item parseItemLine(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length < 5) {
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
                        System.out.println("Error: ElectronicItem requires warranty months");
                        return null;
                    }
                    int warranty = Integer.parseInt(parts[5].trim());
                    return new ElectronicItem(id, name, price, stock, warranty);
                case "GROCERY":
                    if (parts.length < 6) {
                        System.out.println("Error: GroceryItem requires expiry date");
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
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }
}