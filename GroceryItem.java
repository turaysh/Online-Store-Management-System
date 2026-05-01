/**
 * Represents a grocery item in the store.
 * Adds expiry date information to the base Item class.
 */

public class GroceryItem extends Item {
    
    /** Expiration date of the grocery item. */
    private String expiryDate;
    public GroceryItem(int itemId, String name, double price, int stock, String expiryDate) {
        super(itemId, name, price, stock);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

public double calculateDiscount(double percentage) {
        return getPrice() * (percentage / 100);
    }
}
