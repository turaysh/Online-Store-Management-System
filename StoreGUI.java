/**
 * Separate GUI runner.
 * The original console program in test.java is still available.
 */
public class StoreGUI {
    public static void main(String[] args) {
        Store store = new Store("My Store");
        FileManager.loadItems(store);

        Admin admin = new Admin(12345678, "Ahmed", "ahmed@example.com", 5000);
        admin.setAccount(new Account("Ahmed123", "092233"));
        store.addUser(admin);

        Customer customer = new Customer(
            1,
            "GUI Customer",
            "customer@example.com",
            "Riyadh",
            new Account("customer1", "123456")
        );
        store.addUser(customer);

        addSampleItems(store);

        InputFrame inputFrame = new InputFrame(store, admin, customer);
        inputFrame.setVisible(true);
    }

    private static void addSampleItems(Store store) {
        if (!store.itemIdExists(1)) {
            store.addItem(new ElectronicItem(1, "Laptop", 999.99, 1, 2));
        }
        if (!store.itemIdExists(2)) {
            store.addItem(new GroceryItem(2, "Milk", 2.99, 50, "2024-12-31"));
        }
    }
}
