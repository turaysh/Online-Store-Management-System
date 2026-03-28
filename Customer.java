import java.util.Scanner;
class Customer extends User{
    private String location;
    private ShoppingCart shopC;
    private Order[] orders;
    private int CountOrder;
    public Customer(int id, String name, String email, String location, Account account){
        super(id,name,email);
        setAccount(account);
        this.location = location;
        orders = new Order[100];
        CountOrder = 0;
    }

    public boolean addOrder(Order order){
        if(CountOrder >= orders.length || order == null)
            return false;
        orders[CountOrder++] = order;
        return true;
    }
    public Order searchOrder(int orderId){
        for(int i = 0; i < CountOrder; i++)
            if(orders[i].getId() == orderId)
                return orders[i];
        
        return null;
    }
    public Order Checkout(){
Scanner sc = new Scanner(System.in);
        shopC.displayCartItems();
        shopC.calculateTotal();
        System.out.println("If you want to checkout press 1, if you want to clear the cart press 2");
        int choice = sc.nextInt();
        if(choice == 1){
            Order order = new Order(CountOrder + 1, "Processing");
            addOrder(order);
            shopC.clearCart();
            return order;
        } else if(choice == 2){
            shopC.clearCart();
        }
        return null;
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
    public void setEmail(String email) {
        super.setEmail(email);
    }
    public String getName() {
        return super.getUsername();
    }
}