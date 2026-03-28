class Order{
    private int orderId;
    private Item[] items;
    private int count;
    private double totalAmount;
    private String status;
    private ShoppingCart shoppingCart;
    private User customer;

    public Order(int orderId, String status) {
        this.orderId = orderId;
        this.status = status;
       
        
    }
    public Order(User customer, ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
        this.customer = customer;
        this.totalAmount = calculateTotlaOrder();
        Item[] items = shoppingCart.getItems(); 
        this.totalAmount = calculateTotlaOrder();
    }
    public double calculateTotlaOrder(){
        return shoppingCart.calculateTotal();
    }
    public void printOrder(){}
    public int getId() {
        return orderId;
    }
}