

/**
 * Represents a shopping cart for a customer.
 * It stores selected items and supports add, remove, search, and total calculation.
 */

class ShoppingCart implements searchable  {
    private Item[] items;
    private int nofItem;
    public ShoppingCart(){
        items = new Item[100];
        nofItem = 0;
    }

    /**
     * Adds an item to the cart.
     *
     * param item the item to add
     * return true if added successfully, otherwise false
     */

    public boolean addItem(Item item){
        if(nofItem >= items.length || item == null)
            return false;
        items[nofItem++] = item;
        return true;
    }

    /**
     * Removes an item from the cart using its id.
     *
     * param itemId the item id
     * return true if removed successfully, otherwise false
     */


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
    /**
         * Searches for an item in the cart using its id.
         *
         * param itemId the item id
         * return the matching item if found, otherwise null
         */
    public Item searchItem(int itemId) {
        for (int i = 0; i < nofItem; i++) {
            if (items[i] != null && items[i].getId() == itemId) {
                return items[i];
            }
        }
        return null;
    }
    /**
         * Recursively counts non-null items in the cart starting from a given index.
         *
         * param index the starting index
         * return the number of items from that index onward
         */
    public int countItemRecursive(int index) {
        if (index >= nofItem) {
            return 0;
        }
        if (items[index] == null) {
            return countItemRecursive(index + 1);
        }
        return 1 + countItemRecursive(index + 1);
    }

    /**
         * Calculates the total price of items in the cart.
         *
         * return the total price
         */

    public double calculateTotal(){
        double total = 0;
        for(int i = 0; i < nofItem; i++) {
            if(items[i] != null)
                total += items[i].getPrice();
        }
        return total;
    }

    /**
     * Removes all items from the cart and resets the count.
     */

    public void clearCart(){
        for(int i =0; i < nofItem; i++){
            items[i] = null;
        }
        nofItem = 0;
        System.out.println("the operation has been succesfuly done");
    }

    /**
     * Displays all items currently in the cart.
     */
    public void displayCartItems(){
        for(int i = 0; i < nofItem; i++){
            if(items[i] != null){
                System.out.println(items[i].getName()+" - Price: " + items[i].getPrice());
                System.out.println("Total: " + calculateTotal());
            }
        }
    }
    public Item[] getItems() {
        return items;

    }
    /**
     * Searches for an item in the cart by name.
     *
     * param name the name to search for
     * return the matching item if found, otherwise null
     */
    public Item searchByName(String name) {
        for (int i = 0; i < nofItem; i++) {
            if (items[i] != null && items[i].getName().equalsIgnoreCase(name)) {
                return items[i];
            }
        }
        return null;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopping Cart: ");
        for (int i = 0; i < nofItem; i++) {
            if (items[i] != null) {
                sb.append(items[i].toString()).append("\n");
                System.out.println(calculateTotal());
            }
        }
        return sb.toString();
    }
    /**
     * Returns the number of items in the cart.
     *
     * return item count
     */
    public int getNofItem() {
        return nofItem;
    }
    }