public class GroceryItem extends Item {
    private String expiryDate;
    public GroceryItem(int itemId, String name, double price, int stock, String expiryDate) {
        super(itemId, name, price, stock);
        this.expiryDate = expiryDate;
    }
public double calculateDiscount(double percentage) {
        return getPrice() * (percentage / 100);
    }
}
