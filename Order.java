/**
 * Represents a customer order.
 * It stores order id, items, total amount, status, and the customer.
 */

class Order {
    private int orderId;
    private Item[] items;
    private int itemCount;
    private double totalAmount;
    private String status;
    private User customer;

    public Order(User customer, ShoppingCart cart) {
        this.customer = customer;
        this.orderId = (int)(Math.random() * 10000) + 1;
        this.status = "Processing";

        this.itemCount = cart.getNofItem();
        this.items = new Item[itemCount];

        Item[] cartItems = cart.getItems();

        for (int i = 0; i < itemCount; i++) {
            this.items[i] = cartItems[i];
        }

        this.totalAmount = calculateTotalOrder();
    }
        /**
     * Calculates the total amount of all items in the order.
     *
     * return the total order value
     */

    public double calculateTotalOrder() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null) {
                total += items[i].getPrice();
            }
        }
        return total;
    }
    /**
     * Confirms the order by changing its status.
     */
    public void confirmOrder() {
        this.status = "Confirmed";
    }

    public int getId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }
    // Print all the order informaiton
    public void printOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Status: " + status);
        System.out.println("Items:");
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null) {
                System.out.println(items[i]);
            }
        }
        System.out.println("Total Amount: " + totalAmount);
    }
}