public class ElectronicItem extends Item {
    private int warrantyMonths;
    public ElectronicItem(int itemId, String name, double price, int stock, int warrantyMonths) {
        super(itemId, name, price, stock);
        this.warrantyMonths = warrantyMonths;
    }
    public double calculateDiscount(double percentage) {
        return getPrice() * (percentage / 100);
    }
}
