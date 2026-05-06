import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Main input window for the Swing GUI.
 * It uses the existing Store, Admin, Customer, Item, and ShoppingCart classes.
 */
public class InputFrame extends JFrame implements ActionListener {
    private Store store;
    private Admin admin;
    private Customer customer;
    private ResultFrame resultFrame;

    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField extraField;

    private JButton viewItemsButton;
    private JButton addElectronicButton;
    private JButton addGroceryButton;
    private JButton removeButton;
    private JButton updateStockButton;
    private JButton searchButton;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private JButton checkoutButton;

    public InputFrame(Store store, Admin admin, Customer customer) {
        this.store = store;
        this.admin = admin;
        this.customer = customer;
        this.resultFrame = new ResultFrame();

        setTitle("Online Store Input");
        setSize(520, 430);
        setLocation(220, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        add(createInputPanel());
        add(createButtonPanel());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FileManager.saveItems(store);
            }
        });
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        idField = new JTextField();
        nameField = new JTextField();
        priceField = new JTextField();
        stockField = new JTextField();
        extraField = new JTextField();

        panel.add(new JLabel("Item ID:"));
        panel.add(idField);
        panel.add(new JLabel("Item Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Stock:"));
        panel.add(stockField);
        panel.add(new JLabel("Warranty / Expiry Date:"));
        panel.add(extraField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        viewItemsButton = new JButton("View Items");
        addElectronicButton = new JButton("Add Electronic");
        addGroceryButton = new JButton("Add Grocery");
        removeButton = new JButton("Remove Item");
        updateStockButton = new JButton("Update Stock");
        searchButton = new JButton("Search Item");
        addToCartButton = new JButton("Add To Cart");
        viewCartButton = new JButton("View Cart");
        checkoutButton = new JButton("Checkout");

        JButton[] buttons = {
            viewItemsButton, addElectronicButton, addGroceryButton,
            removeButton, updateStockButton, searchButton,
            addToCartButton, viewCartButton, checkoutButton
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == viewItemsButton) {
                resultFrame.showResult(store.getAllItemsText());
            } else if (e.getSource() == addElectronicButton) {
                addElectronicItem();
            } else if (e.getSource() == addGroceryButton) {
                addGroceryItem();
            } else if (e.getSource() == removeButton) {
                removeItem();
            } else if (e.getSource() == updateStockButton) {
                updateStock();
            } else if (e.getSource() == searchButton) {
                searchItem();
            } else if (e.getSource() == addToCartButton) {
                addToCart();
            } else if (e.getSource() == viewCartButton) {
                resultFrame.showResult(customer.getCart().getCartText());
            } else if (e.getSource() == checkoutButton) {
                checkout();
            }
        } catch (NumberFormatException ex) {
            resultFrame.showResult("Please enter valid numbers for ID, price, stock, and warranty.");
        } catch (DuplicateItemException ex) {
            resultFrame.showResult(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            resultFrame.showResult(ex.getMessage());
        }
    }

    private void addElectronicItem() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());
        int warranty = Integer.parseInt(extraField.getText());

        Item item = new ElectronicItem(id, name, price, stock, warranty);
        admin.addItem(store, item);
        FileManager.saveItems(store);
        resultFrame.showResult("Electronic item added:\n" + item);
    }

    private void addGroceryItem() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());
        String expiryDate = extraField.getText();

        Item item = new GroceryItem(id, name, price, stock, expiryDate);
        admin.addItem(store, item);
        FileManager.saveItems(store);
        resultFrame.showResult("Grocery item added:\n" + item);
    }

    private void removeItem() {
        int id = Integer.parseInt(idField.getText());
        if (admin.removeItem(store, id)) {
            FileManager.saveItems(store);
            resultFrame.showResult("Item removed successfully.");
        } else {
            resultFrame.showResult("Item not found.");
        }
    }

    private void updateStock() {
        int id = Integer.parseInt(idField.getText());
        int stock = Integer.parseInt(stockField.getText());
        if (admin.updateStock(store, id, stock)) {
            FileManager.saveItems(store);
            resultFrame.showResult("Stock updated successfully.");
        } else {
            resultFrame.showResult("Item not found.");
        }
    }

    private void searchItem() {
        int id = Integer.parseInt(idField.getText());
        Item item = store.searchItemById(id);
        if (item == null) {
            resultFrame.showResult("Item not found.");
        } else {
            resultFrame.showResult("Item found:\n" + item);
        }
    }

    private void addToCart() {
        int id = Integer.parseInt(idField.getText());
        Item item = store.searchItemById(id);
        if (item == null) {
            resultFrame.showResult("Item not found.");
            return;
        }
        if (item.getStock() <= 0) {
            throw new IllegalArgumentException("Sorry, this item is out of stock.");
        }

        customer.getCart().addItem(item);
        resultFrame.showResult("Item added to cart:\n" + item);
    }

    private void checkout() {
        ShoppingCart cart = customer.getCart();
        if (cart.countItemRecursive(0) == 0) {
            resultFrame.showResult("Your cart is empty.");
            return;
        }

        Order order = new Order(customer, cart);
        customer.addOrder(order);
        store.reduceStockFromCart(cart);
        FileManager.saveItems(store);
        cart.clearCart();
        order.confirmOrder();
        resultFrame.showResult("Order placed successfully.\nOrder ID: " + order.getId());
    }
}
