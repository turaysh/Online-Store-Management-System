import java.util.Scanner;

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

    public boolean addOrder(Order order) {
        if (countOrder >= orders.length || order == null) return false;
        orders[countOrder++] = order;
        return true;
    }

    public Order searchOrder(int orderId) {
        for (int i = 0; i < countOrder; i++) {
            if (orders[i].getId() == orderId) return orders[i];
        }
        return null;
    }

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
            System.out.println(orders[i]);
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

    public ShoppingCart getCart() {
        return shopC;
    }
}