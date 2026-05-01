/**
 * Represents an electronic item in the store.
 * Adds warranty information to the base Item class.
 */

public class ElectronicItem extends Item {
    /** Warranty period in months or years, depending on how it is used. */
    private int warrantyMonths;
    public ElectronicItem(int itemId, String name, double price, int stock, int warrantyMonths) {
        super(itemId, name, price, stock);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    /**
     * Calculates the discount value for this item.
     *
     * param percentage discount percentage
     * return the discount amount
     */
    public double calculateDiscount(double percentage) {
        return getPrice() * (percentage / 100);
    }
}
