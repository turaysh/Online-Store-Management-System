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

    public int getId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    // Print all the order information
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
}