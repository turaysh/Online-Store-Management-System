/**
 * Abstract base class that represents any item in the store.
 * It stores common item information such as ID, name, price, and stock.
 */
public abstract class Item {
    private int itemId;
    private String name;
    private double price;
    private int stock;

    /**
     * Creates a new item with basic item information.
     *
     * @param ItemId the item ID
     * @param name the item name
     * @param price the item price
     * @param stock the available stock quantity
     */
    public Item(int ItemId, String name, double price, int stock) {
        this.itemId = ItemId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Returns the item ID.
     *
     * @return the item ID
     */
    public int getId() {
        return itemId;
    }

    /**
     * Returns the item name.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the item price.
     *
     * @return the item price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the available stock quantity.
     *
     * @return the current stock quantity
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the item stock to a new value.
     *
     * @param stock the new stock quantity
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Increases or decreases stock by the given quantity.
     *
     * @param quantity the amount to add to the current stock
     */
    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    /**
     * Sets a new price for the item.
     *
     * @param price the new item price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets a new name for the item.
     *
     * @param name the new item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new ID for the item.
     *
     * @param itemId the new item ID
     */
    public void setId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Returns the item information as formatted text.
     *
     * @return a string containing the item ID, name, price, and stock
     */
    public String toString() {
        return "ID: " + itemId +
               ", Name: " + name +
               ", Price: " + price +
               ", Stock: " + stock;
    }

    /**
     * Calculates the discount amount based on a percentage.
     *
     * @param percentage the discount percentage
     * @return the discount value
     */
    public double calculateDiscount(double percentage) {
        return getPrice() * (percentage / 100);
    }
}
