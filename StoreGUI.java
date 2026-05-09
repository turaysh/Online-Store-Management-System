/**
 * GUI runner for the Online Store Management System.
 * Launches the login screen. From there users can log in or create an account.
 * Mirrors the same setup as test.java (same admins, same sample items).
 */
public class StoreGUI {
    /**
     * Starts the GUI version of the Online Store Management System.
     * Loads saved data, creates default admin accounts and sample items,
     * then opens the login frame on the Swing event dispatch thread.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Store store = new Store("My Store");
        FileManager.loadItems(store);
        FileManager.loadUsers(store);

        // Pre-load admins — same credentials as test.java
        User admin1 = new Admin(12345678, "Ahmed",   "ahmed@example.com",       5000);
        User admin2 = new Admin(22345678, "Malik",   "abdulmalik@example.com",  4000);
        User admin3 = new Admin(32345678, "Bader",   "bader@example.com",       2000);
        admin1.setAccount(new Account("Ahmed123",       "092233"));
        admin2.setAccount(new Account("Abdulmalik123",  "093344"));
        admin3.setAccount(new Account("Bader123",       "094455"));
        store.addUser(admin1);
        store.addUser(admin2);
        store.addUser(admin3);

        // Sample items (skipped if already loaded from file)
        if (!store.itemIdExists(1)) store.addItem(new ElectronicItem(1, "Laptop", 999.99,  1,  2));
        if (!store.itemIdExists(2)) store.addItem(new GroceryItem(2,   "Milk",   2.99,    50, "2024-12-31"));

        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(store);
            loginFrame.setVisible(true);
        });
    }
}