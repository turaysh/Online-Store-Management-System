import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Represents the admin dashboard GUI frame.
 * It allows the admin to view items, add items, remove items, update stock, and log out.
 */
public class AdminFrame extends JFrame {
    private final Store store;
    private final Admin admin;
    private final JTextArea itemsArea;

    /**
     * Creates the admin dashboard frame and initializes its GUI components.
     *
     * @param store the store system used by the admin
     * @param admin the logged-in admin user
     */
    public AdminFrame(Store store, Admin admin) {
        this.store = store;
        this.admin = admin;

        // Initialize text areas first, before building UI
        itemsArea = new JTextArea();
        itemsArea.setEditable(false);
        itemsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        setTitle("Admin Dashboard - " + admin.getName());
        setSize(760, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildMain(), BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            /**
             * Saves store data and returns to the login frame when the window is closed.
             *
             * @param e the window closing event
             */
            @Override
            public void windowClosing(WindowEvent e) {
                FileManager.saveAll(store);
                dispose();
                new LoginFrame(store).setVisible(true);
            }
        });
    }

    /**
     * Builds the header panel that displays the admin dashboard title and username.
     *
     * @return the header panel
     */
    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel subtitle = new JLabel("Signed in as " + admin.getName());
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.DARK_GRAY);

        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.SOUTH);
        return header;
    }

    /**
     * Builds the main admin panel that contains the items area and control panel.
     *
     * @return the main panel
     */
    private JPanel buildMain() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));

        main.add(new JScrollPane(itemsArea), BorderLayout.CENTER);
        main.add(buildControlPanel(), BorderLayout.EAST);

        refreshItems();
        return main;
    }

    /**
     * Builds the control panel that contains admin action buttons and connects them to events.
     *
     * @return the control panel
     */
    private JPanel buildControlPanel() {
        JPanel controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        controls.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton refreshBtn = new JButton("Refresh Items");
        JButton addBtn = new JButton("Add Item");
        JButton removeBtn = new JButton("Remove Item");
        JButton updateBtn = new JButton("Update Stock");
        JButton logoutBtn = new JButton("Logout");

        refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        refreshBtn.addActionListener(e -> refreshItems());
        addBtn.addActionListener(e -> showAddItemDialog());
        removeBtn.addActionListener(e -> showRemoveItemDialog());
        updateBtn.addActionListener(e -> showUpdateStockDialog());
        logoutBtn.addActionListener(e -> logout());

        controls.add(refreshBtn);
        controls.add(Box.createVerticalStrut(10));
        controls.add(addBtn);
        controls.add(Box.createVerticalStrut(10));
        controls.add(removeBtn);
        controls.add(Box.createVerticalStrut(10));
        controls.add(updateBtn);
        controls.add(Box.createVerticalStrut(20));
        controls.add(logoutBtn);

        return controls;
    }

    /**
     * Refreshes the items text area by displaying all store items.
     */
    private void refreshItems() {
        itemsArea.setText(store.getAllItemsText());
    }

    /**
     * Opens a dialog that allows the admin to add a new electronic or grocery item.
     * It handles invalid input and duplicate item exceptions.
     */
    private void showAddItemDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();
        String[] typeOptions = {"Electronic", "Grocery"};
        JComboBox<String> typeCombo = new JComboBox<>(typeOptions);
        JTextField extraField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1, 4, 4));
        panel.add(new JLabel("Item ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Stock:"));
        panel.add(stockField);
        panel.add(new JLabel("Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Warranty months / Expiry date:"));
        panel.add(extraField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int stock = Integer.parseInt(stockField.getText().trim());
            String type = (String) typeCombo.getSelectedItem();
            String extra = extraField.getText().trim();

            if (name.isEmpty() || extra.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and type details are required.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Item item;
            if ("Electronic".equals(type)) {
                int warranty = Integer.parseInt(extra);
                item = new ElectronicItem(id, name, price, stock, warranty);
            } else {
                item = new GroceryItem(id, name, price, stock, extra);
            }

            admin.addItem(store, item);
            refreshItems();
            JOptionPane.showMessageDialog(this, "Item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for ID, price, and stock.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (DuplicateItemException ex) {
            JOptionPane.showMessageDialog(this, "An item with this ID already exists.", "Duplicate Item", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens a dialog that allows the admin to remove an item by its ID.
     * It handles invalid numeric input and missing items.
     */
    private void showRemoveItemDialog() {
        String input = JOptionPane.showInputDialog(this, "Enter item ID to remove:", "Remove Item", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(input.trim());
            if (admin.removeItem(store, id)) {
                refreshItems();
                JOptionPane.showMessageDialog(this, "Item removed successfully.", "Removed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No item found with that ID.", "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric item ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens a dialog that allows the admin to update the stock of an item.
     * It handles invalid numeric input and missing items.
     */
    private void showUpdateStockDialog() {
        JTextField idField = new JTextField();
        JTextField stockField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1, 4, 4));
        panel.add(new JLabel("Item ID:"));
        panel.add(idField);
        panel.add(new JLabel("New stock amount:"));
        panel.add(stockField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Item Stock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            int id = Integer.parseInt(idField.getText().trim());
            int stock = Integer.parseInt(stockField.getText().trim());
            if (admin.updateStock(store, id, stock)) {
                refreshItems();
                JOptionPane.showMessageDialog(this, "Stock updated successfully.", "Updated", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No item found with that ID.", "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Saves all store data, closes the admin dashboard, and returns to the login frame.
     */
    private void logout() {
        FileManager.saveAll(store);
        dispose();
        new LoginFrame(store).setVisible(true);
    }
}
