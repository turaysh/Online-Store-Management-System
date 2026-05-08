import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomerFrame extends JFrame {
    private final Store store;
    private final Customer customer;
    private final JTextArea itemsArea;
    private final JTextArea cartArea;
    private final JTextArea ordersArea;

    public CustomerFrame(Store store, Customer customer) {
        this.store = store;
        this.customer = customer;

        // Initialize text areas first, before building UI
        itemsArea = new JTextArea();
        itemsArea.setEditable(false);
        itemsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        cartArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        ordersArea = new JTextArea();
        ordersArea.setEditable(false);
        ordersArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        setTitle("Customer Dashboard - " + customer.getName());
        setSize(820, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildMain(), BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileManager.saveAll(store);
                dispose();
                new LoginFrame(store).setVisible(true);
            }
        });
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));

        JLabel title = new JLabel("Customer Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel subtitle = new JLabel("Signed in as " + customer.getName());
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.DARK_GRAY);

        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.SOUTH);
        return header;
    }

    private JPanel buildMain() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.6);
        splitPane.setLeftComponent(new JScrollPane(itemsArea));
        splitPane.setRightComponent(buildRightPanel());

        main.add(splitPane, BorderLayout.CENTER);
        refreshItems();
        refreshCart();
        refreshOrders();
        return main;
    }

    private JPanel buildRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));

        JPanel topControls = new JPanel();
        topControls.setLayout(new BoxLayout(topControls, BoxLayout.Y_AXIS));

        JButton addToCartBtn = new JButton("Add To Cart");
        JButton viewCartBtn = new JButton("View Cart");
        JButton checkoutBtn = new JButton("Checkout");
        JButton viewOrdersBtn = new JButton("View Orders");
        JButton logoutBtn = new JButton("Logout");

        addToCartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewOrdersBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        addToCartBtn.addActionListener(e -> showAddToCartDialog());
        viewCartBtn.addActionListener(e -> refreshCart());
        checkoutBtn.addActionListener(e -> checkout());
        viewOrdersBtn.addActionListener(e -> refreshOrders());
        logoutBtn.addActionListener(e -> logout());

        topControls.add(addToCartBtn);
        topControls.add(Box.createVerticalStrut(8));
        topControls.add(viewCartBtn);
        topControls.add(Box.createVerticalStrut(8));
        topControls.add(checkoutBtn);
        topControls.add(Box.createVerticalStrut(8));
        topControls.add(viewOrdersBtn);
        topControls.add(Box.createVerticalStrut(20));
        topControls.add(logoutBtn);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        contentPanel.add(new JScrollPane(cartArea));
        contentPanel.add(new JScrollPane(ordersArea));

        rightPanel.add(topControls, BorderLayout.NORTH);
        rightPanel.add(contentPanel, BorderLayout.CENTER);
        return rightPanel;
    }

    private void refreshItems() {
        itemsArea.setText(store.getAllItemsText());
    }

    private void refreshCart() {
        cartArea.setText("Shopping Cart:\n" + customer.getCart().getCartText());
    }

    private void refreshOrders() {
        ordersArea.setText("Order History:\n" + customer.getOrdersText());
    }

    private void showAddToCartDialog() {
        String input = JOptionPane.showInputDialog(this, "Enter item ID to add to cart:", "Add to Cart", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;

        try {
            int itemId = Integer.parseInt(input.trim());
            Item item = store.searchItemById(itemId);
            if (item == null) {
                JOptionPane.showMessageDialog(this, "No item found with that ID.", "Not Found", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (item.getStock() <= 0) {
                JOptionPane.showMessageDialog(this, "This item is out of stock.", "Out of Stock", JOptionPane.WARNING_MESSAGE);
                return;
            }
            customer.getCart().addItem(item);
            refreshCart();
            JOptionPane.showMessageDialog(this, "Item added to cart.", "Added", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric item ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkout() {
        if (customer.getCart().getNofItem() == 0) {
            JOptionPane.showMessageDialog(this, "Your cart is empty.", "Empty Cart", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Buy " + customer.getCart().getNofItem() + " item(s) for $" + String.format("%.2f", customer.getCart().calculateTotal()) + "?",
                "Confirm Checkout", JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) return;

        Order order = new Order(customer, customer.getCart());
        order.confirmOrder();
        customer.addOrder(order);
        store.reduceStockFromCart(customer.getCart());
        customer.getCart().clearCart();

        refreshCart();
        refreshItems();
        refreshOrders();
        JOptionPane.showMessageDialog(this, "Checkout completed. Your order has been placed.", "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        FileManager.saveAll(store);
        dispose();
        new LoginFrame(store).setVisible(true);
    }

}
