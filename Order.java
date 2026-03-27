class Order{
    private int orderId;
    private double totalAmount;
    private String status;
    private ShoppingCart shoppingCart;

    public Order(int orderId, Item items,String status) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = calculateTotlaOrder();
        Item[] items = shoppingCart.getItems();
    }
    public double calculateTotlaOrder(){
        double total;
        for(int i = 0; i < items.length; i++)
            total += items[i].getPrice;
        
        return total;
    }
    public void printOrder(){
        System.out.println("Order ID : " + orderId);
        System.out.println("status : " + status);
        
        for(int i = 0; i < items.length; i++)
            System.out.println("Product : " + items[i].getName + " price : " + items[i].getPrice)
        System.out.println("Total price is : " + totalAmount);

    }
    public int getId() {
        return orderId;
    }
}