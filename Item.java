public abstract class Item {
    private int itemId;
    private String name;
    private double price;
    private int stock;
    public Item(int ItemId, String name,double price, int stock){
        this.itemId = ItemId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public int getId() {
        return itemId;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void updateStock(int quantity) {
        this.stock += quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int itemId) {
        this.itemId = itemId;
    }
public String getDetails(){
    return "ID: " + itemId + ", Name: " + name + ", Price: $" + price + ", Stock: " + stock;
}
public double calculateDiscount(double percentage){
    return getPrice() * (percentage / 100);
}

    }

