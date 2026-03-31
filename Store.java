class Store implements searchable {
    private String name;
    private Item[] items;
    private int nofItem;
    private int userCount;
    private User[] users;

    public Store(String name) {
        this.name = name;
        this.items = new Item[100];
        this.nofItem = 0;
        this.userCount = 0;
        this.users = new User[200];
    }

    public boolean addUser(User user) {
        if (userCount < users.length && user != null) {
            users[userCount++] = user;
            return true;
        }
        return false;
    }
    public boolean usernameExists(String username) {
    for (int i = 0; i < userCount; i++) {
        if (users[i] != null &&
            users[i].getUsername() != null &&
            users[i].getUsername().equalsIgnoreCase(username)) {
            return true;
        }
    }
    return false;
}

public boolean isValidUsername(String username) {
    return username != null &&
           username.length() >= 4 &&
           !username.contains(" ") &&
           username.matches("[A-Za-z0-9_]+");
}

public boolean isValidPassword(String password) {
    return password != null &&
           password.length() >= 6 &&
           password.matches(".*\\d.*");
}

    public int getNextUserId() {
        return userCount + 1;
    }

    public boolean addItem(Item item) {
        if (nofItem >= items.length || item == null) return false;
        items[nofItem++] = item;
        return true;
    }

public boolean removeItem(int itemId) {
    for (int i = 0; i < nofItem; i++) {
        if (items[i] != null && items[i].getId() == itemId) {
            for (int j = i; j < nofItem - 1; j++) {
                items[j] = items[j + 1];
            }
            items[--nofItem] = null;
            return true;
        }
    }
    return false;
}
public boolean itemIdExists(int id) {
    for (int i = 0; i < nofItem; i++) {
        if (items[i] != null && items[i].getId() == id) {
            return true;
        }
    }
    return false;
}
    public boolean updateStock(int itemId, int newStock) {
        for (int i = 0; i < nofItem; i++) {
            if (items[i].getId() == itemId) {
                items[i].setStock(newStock);
                return true;
            }
        }
        return false;
    }

    public void displayAllItems() {
        for (int i = 0; i < nofItem; i++) {
            System.out.println(items[i].toString());
        }
    }

    public int countAvailableItemsRecursive(int index) {
        if (index >= nofItem) return 0;
        if (items[index].getStock() > 0) {
            return 1 + countAvailableItemsRecursive(index + 1);
        }
        return countAvailableItemsRecursive(index + 1);
    }

    public String getName() {
        return name;
    }

    public User login(String username, String password) {
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null &&
                users[i].getUsername() != null &&
                users[i].getPassword() != null &&
                users[i].getUsername().equals(username) &&
                users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }
public Item searchItemById(int itemId) {
    for (int i = 0; i < nofItem; i++) {
        if (items[i].getId() == itemId) {
            return items[i];
        }
    }
    return null;
}
    public Item searchByName(String name) {
                for (int i = 0; i < nofItem; i++) {
            if (items[i].getName().equalsIgnoreCase(name)) return items[i];
        }
        return null;
    }
    public void reduceStockFromCart(ShoppingCart cart) {
    Item[] cartItems = cart.getItems();

    for (int i = 0; i < cart.getNofItem(); i++) {
        if (cartItems[i] != null) {

            Item storeItem = searchItemById(cartItems[i].getId());

            if (storeItem != null) {
                int currentStock = storeItem.getStock();

                if (currentStock > 0) {
                    storeItem.setStock(currentStock - 1);
                } else {
                    System.out.println("Item out of stock: " + storeItem.getName());
                }
            }
        }
    }
}
}