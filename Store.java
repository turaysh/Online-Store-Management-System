/**
 * Represents the store system.
 * It manages users, items, login, validation, searching, and stock updates.
 */

class Store implements searchable {
    private String name;
    private LinkedListNode<Item> itemsHead;
    private LinkedListNode<Item> itemsTail;
    private int nofItem;
    private int userCount;
    private LinkedListNode<User> usersHead;
    private LinkedListNode<User> usersTail;

    public Store(String name) {
        this.name = name;
        this.itemsHead = null;
        this.itemsTail = null;
        this.nofItem = 0;
        this.userCount = 0;
        this.usersHead = null;
        this.usersTail = null;
    }

    /**
     * Adds a user to the store system.
     *
     * @param user the user to add
     * @return true if added successfully, otherwise false
     */
    public boolean addUser(User user) {
        if (user == null) return false;

        LinkedListNode<User> newNode = new LinkedListNode<>(user);
        if (usersHead == null) {
            usersHead = newNode;
            usersTail = newNode;
        } else {
            usersTail.setNext(newNode);
            usersTail = newNode;
        }
        userCount++;
        return true;
    }

    /**
     * Checks if a username already exists in the system.
     *
     * @param username the username to check
     * @return true if it exists, otherwise false
     */
    public boolean usernameExists(String username) {
        LinkedListNode<User> current = usersHead;
        while (current != null) {
            if (current.getData() != null &&
                current.getData().getUsername() != null &&
                current.getData().getUsername().equalsIgnoreCase(username)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Validates username rules.
     *
     * @param username the username to validate
     * @return true if valid, otherwise false
     */
    public boolean isValidUsername(String username) {
        return username != null &&
            username.length() >= 4 &&
            !username.contains(" ") &&
            username.matches("[A-Za-z0-9_]+");
    }

    /**
     * Validates password rules.
     *
     * @param password the password to validate
     * @return true if valid, otherwise false
     */
    public boolean isValidPassword(String password) {
        return password != null &&
            password.length() >= 6 &&
            password.matches(".*\\d.*");
    }

    public int getNextUserId() {
        return userCount + 1;
    }

    public boolean addItem(Item item) {
        if (item == null) return false;

        LinkedListNode<Item> newNode = new LinkedListNode<>(item);
        if (itemsHead == null) {
            itemsHead = newNode;
            itemsTail = newNode;
        } else {
            itemsTail.setNext(newNode);
            itemsTail = newNode;
        }
        nofItem++;
        return true;
    }

    public boolean removeItem(int itemId) {
        LinkedListNode<Item> current = itemsHead;
        LinkedListNode<Item> prev = null;

        while (current != null) {
            if (current.getData() != null && current.getData().getId() == itemId) {
                if (prev == null) {
                    itemsHead = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                if (current == itemsTail) {
                    itemsTail = prev;
                }
                nofItem--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Checks if an item id already exists.
     *
     * @param id the item id
     * @return true if the id exists, otherwise false
     */
    public boolean itemIdExists(int id) {
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null && current.getData().getId() == id) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Updates the stock of a given item.
     *
     * @param itemId the item id
     * @param newStock the new stock amount
     * @return true if updated successfully, otherwise false
     */
    public boolean updateStock(int itemId, int newStock) {
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData().getId() == itemId) {
                current.getData().setStock(newStock);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void displayAllItems() {
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            System.out.println(current.getData().toString());
            current = current.getNext();
        }
    }

    /**
     * Recursively counts items that are still available in stock.
     *
     * @param node the current node
     * @return number of available items
     */
    public int countAvailableItemsRecursive(LinkedListNode<Item> node) {
        if (node == null) return 0;
        int count = (node.getData().getStock() > 0) ? 1 : 0;
        return count + countAvailableItemsRecursive(node.getNext());
    }

    /**
     * Counts items available in stock (overloaded for backward compatibility).
     *
     * @param index the starting index (ignored, kept for compatibility)
     * @return number of available items
     */
    public int countAvailableItemsRecursive(int index) {
        return countAvailableItemsRecursive(itemsHead);
    }

    public String getName() {
        return name;
    }

    /**
     * Logs in a user using username and password.
     *
     * @param username entered username
     * @param password entered password
     * @return the matching user if login succeeds, otherwise null
     */
    public User login(String username, String password) {
        LinkedListNode<User> current = usersHead;
        while (current != null) {
            if (current.getData() != null &&
                current.getData().getUsername() != null &&
                current.getData().getPassword() != null &&
                current.getData().getUsername().equals(username) &&
                current.getData().getPassword().equals(password)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public Item searchItemById(int itemId) {
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData().getId() == itemId) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public Item searchByName(String name) {
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData().getName().equalsIgnoreCase(name)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Reduces store stock based on the items found in a shopping cart.
     *
     * @param cart the shopping cart used for checkout
     */
    public void reduceStockFromCart(ShoppingCart cart) {
        LinkedListNode<Item> cartHead = cart.getItemsHead();

        while (cartHead != null) {
            if (cartHead.getData() != null) {
                Item cartItem = cartHead.getData();
                Item storeItem = searchItemById(cartItem.getId());
                if (storeItem != null) {
                    int newStock = storeItem.getStock() - 1;
                    storeItem.setStock(Math.max(0, newStock));
                }
            }
            cartHead = cartHead.getNext();
        }
    }

    public int getNofItem() {
        return nofItem;
    }

    /**
     * Returns the head of the items linked list (for FileManager to iterate).
     *
     * @return the head node of the items list
     */
    public LinkedListNode<Item> getItemsHead() {
        return itemsHead;
    }
}