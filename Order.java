class Order{
    private int orderId;
    private Item[] items;
    private int count;
    private double totalAmount;
    private String status;
    private ShoppingCart shoppingCart;

    public Order(int orderId, String status) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = calculateTotlaOrder();
        Item[] items = shoppingCart.getItems();
    }
    public double calculateTotlaOrder(){
        return shoppingCart.calculateTotal();
    }
    public void printOrder(){}
    public int getId() {
        return orderId;
    }
}