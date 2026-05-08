import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Swing GUI that follows the same flow as test.java:
 * create account, login, admin menu, and customer menu.
 */
public class InputFrame extends JFrame implements ActionListener {
    private static final String AUTH_CARD = "auth";
    private static final String ADMIN_CARD = "admin";
    private static final String CUSTOMER_CARD = "customer";

    private Store store;
    private ResultFrame resultFrame;
    private CardLayout cardLayout;
    private JPanel cards;
    private Admin currentAdmin;
    private Customer currentCustomer;

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JTextField registerNameField;
    private JTextField registerEmailField;
    private JTextField registerLocationField;

    private JTextField adminIdField;
    private JTextField adminNameField;
    private JTextField adminPriceField;
    private JTextField adminStockField;
    private JTextField adminExtraField;

    private JTextField customerIdField;
    private JTextField customerNameField;

    private JLabel adminWelcomeLabel;
    private JLabel customerWelcomeLabel;

    public InputFrame(Store store) {
        this.store = store;
        this.resultFrame = new ResultFrame();

        setTitle("Online Store");
        setSize(640, 520);
        setLocation(220, 140);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(createAuthPanel(), AUTH_CARD);
        cards.add(createAdminPanel(), ADMIN_CARD);
        cards.add(createCustomerPanel(), CUSTOMER_CARD);
        add(cards);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FileManager.saveItems(store);
            }
        });
    }

    private JPanel createAuthPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(new JLabel("Welcome to " + store.getName(), JLabel.CENTER), BorderLayout.NORTH);

        JPanel forms = new JPanel(new GridLayout(1, 2, 12, 12));
        forms.add(createLoginPanel());
        forms.add(createRegisterPanel());
        panel.add(forms, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        loginUsernameField = new JTextField();
        loginPasswordField = new JPasswordField();

        panel.add(new JLabel("Login", JLabel.CENTER));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Username:"));
        panel.add(loginUsernameField);
        panel.add(new JLabel("Password:"));
        panel.add(loginPasswordField);
        panel.add(new JLabel(""));
        panel.add(createButton("Login"));
        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 8, 8));
        registerUsernameField = new JTextField();
        registerPasswordField = new JPasswordField();
        registerNameField = new JTextField();
        registerEmailField = new JTextField();
        registerLocationField = new JTextField();

        panel.add(new JLabel("Create Customer Account", JLabel.CENTER));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Username:"));
        panel.add(registerUsernameField);
        panel.add(new JLabel("Password:"));
        panel.add(registerPasswordField);
        panel.add(new JLabel("Name:"));
        panel.add(registerNameField);
        panel.add(new JLabel("Email:"));
        panel.add(registerEmailField);
        panel.add(new JLabel("Location:"));
        panel.add(registerLocationField);
        panel.add(new JLabel(""));
        panel.add(createButton("Create Account"));
        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        adminWelcomeLabel = new JLabel("Admin Menu", JLabel.CENTER);
        panel.add(adminWelcomeLabel, BorderLayout.NORTH);

        JPanel fields = new JPanel(new GridLayout(5, 2, 8, 8));
        adminIdField = new JTextField();
        adminNameField = new JTextField();
        adminPriceField = new JTextField();
        adminStockField = new JTextField();
        adminExtraField = new JTextField();

        fields.add(new JLabel("Item ID:"));
        fields.add(adminIdField);
        fields.add(new JLabel("Item Name:"));
        fields.add(adminNameField);
        fields.add(new JLabel("Price:"));
        fields.add(adminPriceField);
        fields.add(new JLabel("Stock:"));
        fields.add(adminStockField);
        fields.add(new JLabel("Warranty / Expiry Date:"));
        fields.add(adminExtraField);
        panel.add(fields, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(4, 2, 8, 8));
        buttons.add(createButton("Admin View Items"));
        buttons.add(createButton("Admin Add Electronic"));
        buttons.add(createButton("Admin Add Grocery"));
        buttons.add(createButton("Admin Remove Item"));
        buttons.add(createButton("Admin Search Item"));
        buttons.add(createButton("Admin Count Items"));
        buttons.add(createButton("Admin Update Stock"));
        buttons.add(createButton("Logout"));
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        customerWelcomeLabel = new JLabel("Customer Menu", JLabel.CENTER);
        panel.add(customerWelcomeLabel, BorderLayout.NORTH);

        JPanel fields = new JPanel(new GridLayout(2, 2, 8, 8));
        customerIdField = new JTextField();
        customerNameField = new JTextField();

        fields.add(new JLabel("Item ID:"));
        fields.add(customerIdField);
        fields.add(new JLabel("Item Name:"));
        fields.add(customerNameField);
        panel.add(fields, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(4, 2, 8, 8));
        buttons.add(createButton("Customer View Items"));
        buttons.add(createButton("Customer Search Name"));
        buttons.add(createButton("Customer Add To Cart"));
        buttons.add(createButton("Customer Remove From Cart"));
        buttons.add(createButton("Customer View Cart"));
        buttons.add(createButton("Customer Checkout"));
        buttons.add(createButton("Customer View Orders"));
        buttons.add(createButton("Logout"));
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            if (command.equals("Login")) {
                login();
            } else if (command.equals("Create Account")) {
                createAccount();
            } else if (command.equals("Logout")) {
                logout();
            } else if (command.startsWith("Admin")) {
                handleAdminAction(command);
            } else if (command.startsWith("Customer")) {
                handleCustomerAction(command);
            }
        } catch (NumberFormatException ex) {
            resultFrame.showResult("Please enter valid numbers where numbers are required.");
        } catch (DuplicateItemException ex) {
            resultFrame.showResult(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            resultFrame.showResult(ex.getMessage());
        }
    }

    private void login() {
        String username = loginUsernameField.getText().trim();
        String password = new String(loginPasswordField.getPassword());
        User user = store.login(username, password);

        if (user == null) {
            resultFrame.showResult("Invalid username or password.");
            return;
        }

        if (user instanceof Admin) {
            currentAdmin = (Admin) user;
            currentCustomer = null;
            adminWelcomeLabel.setText("Admin Menu - " + user.getName());
            cardLayout.show(cards, ADMIN_CARD);
        } else if (user instanceof Customer) {
            currentCustomer = (Customer) user;
            currentAdmin = null;
            customerWelcomeLabel.setText("Customer Menu - " + user.getName());
            cardLayout.show(cards, CUSTOMER_CARD);
        }
        resultFrame.showResult("Logged in as " + user.getRole() + ": " + user.getName());
    }

    private void createAccount() {
        String username = registerUsernameField.getText().trim();
        String password = new String(registerPasswordField.getPassword());
        String name = registerNameField.getText().trim();
        String email = registerEmailField.getText().trim();
        String location = registerLocationField.getText().trim();

        if (!store.isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username. Use at least 4 characters, no spaces, and only letters, numbers, or underscore.");
        }
        if (store.usernameExists(username)) {
            throw new IllegalArgumentException("This username already exists.");
        }
        if (!store.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password. Use at least 6 characters and at least one number.");
        }
        if (name.length() == 0 || email.length() == 0 || location.length() == 0) {
            throw new IllegalArgumentException("Please fill in name, email, and location.");
        }

        Account account = new Account(username, password);
        Customer customer = new Customer(store.getNextUserId(), name, email, location, account);
        if (store.addUser(customer)) {
            clearRegisterFields();
            resultFrame.showResult("Account created successfully. You can now log in.");
        } else {
            resultFrame.showResult("Failed to create account.");
        }
    }

    private void handleAdminAction(String command) {
        if (currentAdmin == null) {
            resultFrame.showResult("Please log in as an admin first.");
            return;
        }

        if (command.equals("Admin View Items")) {
            resultFrame.showResult(store.getAllItemsText());
        } else if (command.equals("Admin Add Electronic")) {
            addElectronicItem();
        } else if (command.equals("Admin Add Grocery")) {
            addGroceryItem();
        } else if (command.equals("Admin Remove Item")) {
            removeItem();
        } else if (command.equals("Admin Search Item")) {
            searchAdminItem();
        } else if (command.equals("Admin Count Items")) {
            resultFrame.showResult("Total available items = " + store.countAvailableItemsRecursive(0));
        } else if (command.equals("Admin Update Stock")) {
            updateStock();
        }
    }

    private void handleCustomerAction(String command) {
        if (currentCustomer == null) {
            resultFrame.showResult("Please log in as a customer first.");
            return;
        }

        if (command.equals("Customer View Items")) {
            resultFrame.showResult(store.getAllItemsText());
        } else if (command.equals("Customer Search Name")) {
            searchCustomerItem();
        } else if (command.equals("Customer Add To Cart")) {
            addToCart();
        } else if (command.equals("Customer Remove From Cart")) {
            removeFromCart();
        } else if (command.equals("Customer View Cart")) {
            resultFrame.showResult(currentCustomer.getCart().getCartText());
        } else if (command.equals("Customer Checkout")) {
            checkout();
        } else if (command.equals("Customer View Orders")) {
            resultFrame.showResult(currentCustomer.getOrdersText());
        }
    }

    private void addElectronicItem() {
        int id = parseAdminId();
        String name = requireText(adminNameField, "Item name is required.");
        double price = Double.parseDouble(adminPriceField.getText().trim());
        int stock = Integer.parseInt(adminStockField.getText().trim());
        int warranty = Integer.parseInt(adminExtraField.getText().trim());

        Item item = new ElectronicItem(id, name, price, stock, warranty);
        currentAdmin.addItem(store, item);
        FileManager.saveItems(store);
        resultFrame.showResult("Electronic item added:\n" + item);
    }

    private void addGroceryItem() {
        int id = parseAdminId();
        String name = requireText(adminNameField, "Item name is required.");
        double price = Double.parseDouble(adminPriceField.getText().trim());
        int stock = Integer.parseInt(adminStockField.getText().trim());
        String expiryDate = requireText(adminExtraField, "Expiry date is required.");

        Item item = new GroceryItem(id, name, price, stock, expiryDate);
        currentAdmin.addItem(store, item);
        FileManager.saveItems(store);
        resultFrame.showResult("Grocery item added:\n" + item);
    }

    private void removeItem() {
        int id = parseAdminId();
        if (currentAdmin.removeItem(store, id)) {
            FileManager.saveItems(store);
            resultFrame.showResult("Item removed successfully.");
        } else {
            resultFrame.showResult("Item not found.");
        }
    }

    private void updateStock() {
        int id = parseAdminId();
        int stock = Integer.parseInt(adminStockField.getText().trim());
        if (currentAdmin.updateStock(store, id, stock)) {
            FileManager.saveItems(store);
            resultFrame.showResult("Stock updated successfully.");
        } else {
            resultFrame.showResult("Item not found.");
        }
    }

    private void searchAdminItem() {
        int id = parseAdminId();
        Item item = store.searchItemById(id);
        resultFrame.showResult(item == null ? "Item not found." : "Item found:\n" + item);
    }

    private void searchCustomerItem() {
        String name = requireText(customerNameField, "Item name is required.");
        Item item = store.searchByName(name);
        resultFrame.showResult(item == null ? "Item not found." : "Item found:\n" + item);
    }

    private void addToCart() {
        int id = parseCustomerId();
        Item item = store.searchItemById(id);
        if (item == null) {
            resultFrame.showResult("Item not found.");
            return;
        }
        if (item.getStock() <= 0) {
            throw new IllegalArgumentException("Sorry, this item is out of stock.");
        }

        currentCustomer.getCart().addItem(item);
        resultFrame.showResult("Item added to cart:\n" + item);
    }

    private void removeFromCart() {
        int id = parseCustomerId();
        if (currentCustomer.getCart().removeItem(id)) {
            resultFrame.showResult("Item removed from cart.");
        } else {
            resultFrame.showResult("Item not found in cart.");
        }
    }

    private void checkout() {
        ShoppingCart cart = currentCustomer.getCart();
        if (cart.countItemRecursive(0) == 0) {
            resultFrame.showResult("Your cart is empty.");
            return;
        }

        Order order = new Order(currentCustomer, cart);
        currentCustomer.addOrder(order);
        store.reduceStockFromCart(cart);
        FileManager.saveItems(store);
        cart.clearCart();
        order.confirmOrder();
        resultFrame.showResult("Order placed successfully.\nOrder ID: " + order.getId());
    }

    private void logout() {
        FileManager.saveItems(store);
        currentAdmin = null;
        currentCustomer = null;
        clearLoginFields();
        cardLayout.show(cards, AUTH_CARD);
        resultFrame.showResult("Logged out.");
    }

    private int parseAdminId() {
        return Integer.parseInt(adminIdField.getText().trim());
    }

    private int parseCustomerId() {
        return Integer.parseInt(customerIdField.getText().trim());
    }

    private String requireText(JTextField field, String message) {
        String value = field.getText().trim();
        if (value.length() == 0) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    private void clearLoginFields() {
        loginUsernameField.setText("");
        loginPasswordField.setText("");
    }

    private void clearRegisterFields() {
        registerUsernameField.setText("");
        registerPasswordField.setText("");
        registerNameField.setText("");
        registerEmailField.setText("");
        registerLocationField.setText("");
    }
}
