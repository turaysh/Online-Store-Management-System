/**
 * Represents an admin user.
 * Admins can manage store items such as adding, removing, and updating stock.
 */

public class Admin extends Employee {
    public Admin(int id, String name, String email, double salary) {
        super(id, name, email, salary);
        
    }
    /**
     * Returns the role of this user.
     *
     * return "Admin"
     */
    @Override
    public String getRole() {
        return "Admin";
    }
     /**
     * Adds a new item to the store.
     *
     * param store the store where the item will be added
     * param item the item to add
     * return true if the item was added successfully, otherwise false
     */

    public boolean addItem(Store store, Item item) {
        return store.addItem(item);
    }
      /**
     * Removes an item from the store using its id.
     *
     * param store the store to remove the item from
     * param itemId the id of the item to remove
     * return true if the item was removed, otherwise false
     */
    public boolean removeItem(Store store, int itemId) {
        return store.removeItem(itemId);
    }
    /**
     * Updates the stock of a specific item in the store.
     *
     * param store the store that contains the item
     * param itemId the id of the item
     * param newStock the new stock value
     * return true if the stock was updated, otherwise false
     */
    public boolean updateStock(Store store, int itemId, int newStock) {
        return store.updateStock(itemId, newStock);
    }

}
