/**
 * Represents an electronic item in the store.
 * Adds warranty information to the base Item class.
 */
public class ElectronicItem extends Item {
    /** Warranty period in months or years, depending on how it is used. */
    private int warrantyMonths;

    /**
     * Creates a new electronic item with basic item information and warranty details.
     *
     * @param itemId the electronic item ID
     * @param name the electronic item name
     * @param price the electronic item price
     * @param stock the available stock quantity
     * @param warrantyMonths the warranty period for the electronic item
     */
    public ElectronicItem(int itemId, String name, double price, int stock, int warrantyMonths) {
        super(itemId, name, price, stock);
        this.warrantyMonths = warrantyMonths;
    }

    /**
     * Returns the warranty period of the electronic item.
     *
     * @return the warranty period
     */
    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    /**
     * Calculates the discount value for this electronic item.
     *
     * @param percentage the discount percentage
     * @return the discount amount
     */
    public double calculateDiscount(double percentage) {
        return getPrice() * (percentage / 100);
    }
}
