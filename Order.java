class Order {
    private int orderId;
    private Item[] items;
    private double totalAmount;
    private String status;
    private ShoppingCart shoppingCart;
    private User customer;

    public Order(int orderId, String status) {
        this.orderId = orderId;
       this.status = "Confirmed";
    }

    public Order(User customer, ShoppingCart shoppingCart) {
    this.customer = customer;
    this.shoppingCart = shoppingCart;
    this.items = shoppingCart.getItems();
    this.totalAmount = calculateTotalOrder();
    this.status = "Confirmed";
    this.orderId = (int)(Math.random() * 10000) + 1;
}

    public double calculateTotalOrder() {
        return shoppingCart.calculateTotal();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Items:\n");
        for (Item item : items) {
            if (item != null) {
                sb.append("- ").append(item.getName()).append(": $").append(item.getPrice()).append("\n");
            }
        }
        sb.append("Total Amount: $").append(totalAmount).append("\n");
        sb.append("Status: ").append(status).append("\n");
        return sb.toString();
    }

    public int getId() {
        return orderId;
    }
}