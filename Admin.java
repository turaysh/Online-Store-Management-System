public class Admin extends Employee {
    public Admin(int id, String name, String email, double salary) {
        super(id, name, email, salary);
        
    }
    @Override
    public String getRole() {
        return "Admin";
    }
    public boolean addItem(Store store, Item item) {
        return store.addItem(item);
    }
    public boolean removeItem(Store store, int itemId) {
        return store.removeItem(itemId);
    }
    public boolean updateStock(Store store, int itemId, int newStock) {
        return store.updateStock(itemId, newStock);
    }

}
