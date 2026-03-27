class Customer extends User{
    private String location;
    private ShoppingCart[] shopC;
    private Order[] orders;
    private int CountOrder;
    private Account account; // new atribute!!

    public Customer(int id, String name, String email, String location, Account account){
        super(id,name,email);
        this.location = location;
        orders = new Order[100];
        shopC = new ShoppingCart[100];
        CountOrder = 0;
    }
    public Account creatAcount(String userName, String password)
    {
        if(account == null){
            account = new Account(userName, password);
            return account;
        }
        return account;
    }

    public boolean addOrder(Order order){
        if(CountOrder >= orders.length || order == null)
            return false;
        orders[CountOrder++] = order;
        return true;
    }
    public Order searchOrder(int orderId){
        for(int i = 0; i < CountOrder; i++)
            if(orders[i].getId == orderId)
                return orders[i];
        
        return null;
    }
    public Order Checkout(){
        
    }
    public String getRole(){}
}