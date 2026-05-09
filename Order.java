/**
 * Represents a customer order.
 * It stores order id, items, total amount, status, and the customer.
 */

class Order {
    private int orderId;
    private LinkedListNode<Item> itemsHead;
    private LinkedListNode<Item> itemsTail;
    private int itemCount;
    private double totalAmount;
    private String status;
    private User customer;

    /**
     * Creates a new order for the given customer using the items currently in the shopping cart.
     * The cart items are copied into the order linked list, and the total amount is calculated.
     *
     * @param customer the customer who placed the order
     * @param cart the shopping cart used to create the order
     */
    public Order(User customer, ShoppingCart cart) {
        this.customer = customer;
        this.orderId = (int)(Math.random() * 10000) + 1;
        this.status = "Processing";

        this.itemCount = cart.getNofItem();

        // Deep copy items from cart's linked list
        LinkedListNode<Item> cartCurrent = cart.getItemsHead();
        while (cartCurrent != null) {
            if (cartCurrent.getData() != null) {
                LinkedListNode<Item> newNode = new LinkedListNode<>(cartCurrent.getData());
                if (itemsHead == null) {
                    itemsHead = newNode;
                    itemsTail = newNode;
                } else {
                    itemsTail.setNext(newNode);
                    itemsTail = newNode;
                }
            }
            cartCurrent = cartCurrent.getNext();
        }

        this.totalAmount = calculateTotalOrder();
    }

    /**
     * Calculates the total amount of all items in the order.
     *
     * @return the total order value
     */
    public double calculateTotalOrder() {
        double total = 0;
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null) {
                total += current.getData().getPrice();
            }
            current = current.getNext();
        }
        return total;
    }

    /**
     * Confirms the order by changing its status.
     */
    public void confirmOrder() {
        this.status = "Confirmed";
    }

    /**
     * Returns the order ID.
     *
     * @return the order ID
     */
    public int getId() {
        return orderId;
    }

    /**
     * Returns the current status of the order.
     *
     * @return the order status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Prints the complete order details to the console, including the order ID,
     * customer name, status, ordered items, and total amount.
     */
    public void printOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Status: " + status);
        System.out.println("Items:");
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null) {
                System.out.println(current.getData());
            }
            current = current.getNext();
        }
        System.out.println("Total Amount: " + totalAmount);
    }

    /**
     * Builds and returns the complete order details as formatted text for GUI display.
     *
     * @return a formatted string containing the order details
     */
    public String getOrderText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Items:\n");

        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null) {
                sb.append(current.getData()).append("\n");
            }
            current = current.getNext();
        }

        sb.append("Total Amount: ").append(totalAmount).append("\n");
        return sb.toString();
    }
}
