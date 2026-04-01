import java.util.Scanner;
/**
 * Represents a customer in the store system.
 * A customer has a location, a shopping cart, and a list of orders.
 */
class Customer extends User {
    private String location;
    private ShoppingCart shopC;
    private Order[] orders;
    private int countOrder;

    public Customer(int id, String name, String email, String location, Account account) {
        super(id, name, email);
        setAccount(account);
        this.location = location;
        this.shopC = new ShoppingCart();
        this.orders = new Order[100];
        this.countOrder = 0;
    }
    /**
     * Adds an order to the customer's order list.
     *
     * param order the order to add
     * return true if added successfully, otherwise false
     */
    public boolean addOrder(Order order) {
        if (countOrder >= orders.length || order == null) return false;
        orders[countOrder++] = order;
        return true;
    }
     /**
     * Searches for an order using its id.
     *
     * param orderId the order id to search for
     * return the matching order if found, otherwise null
     */
    public Order searchOrder(int orderId) {
        for (int i = 0; i < countOrder; i++) {
            if (orders[i].getId() == orderId) return orders[i];
        }
        return null;
    }
    /**
     * Starts the checkout process for the current cart.
     * Displays cart details, asks the user for confirmation,
     * and creates an order if checkout is confirmed.
     *
     * return the created order if checkout succeeds, otherwise null
     */
    public Order checkout() {
        Scanner sc = new Scanner(System.in);
        shopC.displayCartItems();
        System.out.println("Total = " + shopC.calculateTotal());
        System.out.println("If you want to checkout press 1, if you want to clear the cart press 2");
        int choice = sc.nextInt();

        if (choice == 1) {
            Order order = new Order(this, shopC);
            addOrder(order);
            shopC.clearCart();
            return order;
        } else if (choice == 2) {
            shopC.clearCart();
        }
        return null;
    }
public void viewOrders() {
        if (countOrder == 0) {
            System.out.println("You have no orders.");
            return;
        }
        for (int i = 0; i < countOrder; i++) {
            orders[i].printOrder();
        }
    }
    public String getRole() {
        return "Customer";
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getlocation() {
        return location;
    }

    public String getName() {
        return super.getName();
    }
    /**
     * Returns the customer's shopping cart.
     *
     * return the shopping cart
     */
    public ShoppingCart getCart() {
        return shopC;
    }
}