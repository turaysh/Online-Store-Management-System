/**
 * Represents a grocery item in the store.
 * Adds expiry date information to the base Item class.
 */
public class GroceryItem extends Item {
    /** Expiration date of the grocery item. */
    private String expiryDate;

    /**
     * Creates a new grocery item with basic item information and expiry date.
     *
     * @param itemId the grocery item ID
     * @param name the grocery item name
     * @param price the grocery item price
     * @param stock the available stock quantity
     * @param expiryDate the expiry date of the grocery item
     */
    public GroceryItem(int itemId, String name, double price, int stock, String expiryDate) {
        super(itemId, name, price, stock);
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the expiry date of the grocery item.
     *
     * @return the expiry date
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Calculates the discount value for this grocery item.
     *
     * @param percentage the discount percentage
     * @return the discount amount
     */
    public double calculateDiscount(double percentage) {
        return getPrice() * (percentage / 100);
    }
}
