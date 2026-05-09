import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Represents the customer account creation GUI frame.
 * It collects customer information, validates the input, creates a customer account, and saves it.
 */
public class CreateAccountFrame extends JFrame {
    private final Store store;

    /**
     * Creates the account creation frame and initializes its GUI layout.
     *
     * @param store the store system where the new customer account will be saved
     */
    public CreateAccountFrame(Store store) {
        this.store = store;

        setTitle("Create Customer Account");
        setSize(420, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildForm(), BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            /**
             * Closes the account creation frame and returns to the login frame.
             *
             * @param e the window closing event
             */
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new LoginFrame(store).setVisible(true);
            }
        });
    }

    /**
     * Builds the header panel for the account creation frame.
     *
     * @return the header panel
     */
    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        JLabel title = new JLabel("Create a New Customer Account");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        header.add(title, BorderLayout.CENTER);
        return header;
    }

    /**
     * Builds the account creation form with input fields and buttons.
     * It validates the entered data, creates the customer, saves data, and returns to login.
     *
     * @return the form panel
     */
    private JPanel buildForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(8, 8, 8, 8);

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        String[] saudiCities = {
            "Riyadh", "Jeddah", "Mecca", "Medina", "Dammam", "Khobar",
            "Tabuk", "Taif", "Abha", "Yanbu", "Najran", "Hail",
            "Buraydah", "Al Jubail", "Jazan", "Al Ula", "Arar", "Buraidah"
        };
        JComboBox<String> locationCombo = new JComboBox<>(saudiCities);
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        c.gridx = 0; c.gridy = 0;
        panel.add(new JLabel("Name:"), c);
        c.gridx = 1; c.gridy = 0;
        panel.add(nameField, c);

        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Email:"), c);
        c.gridx = 1; c.gridy = 1;
        panel.add(emailField, c);

        c.gridx = 0; c.gridy = 2;
        panel.add(new JLabel("Location:"), c);
        c.gridx = 1; c.gridy = 2;
        panel.add(locationCombo, c);

        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Username:"), c);
        c.gridx = 1; c.gridy = 3;
        panel.add(usernameField, c);

        c.gridx = 0; c.gridy = 4;
        panel.add(new JLabel("Password:"), c);
        c.gridx = 1; c.gridy = 4;
        panel.add(passwordField, c);

        JButton createBtn = new JButton("Create Account");
        JButton cancelBtn = new JButton("Cancel");

        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createBtn);
        buttonPanel.add(cancelBtn);
        panel.add(buttonPanel, c);

        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String location = (String) locationCombo.getSelectedItem();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (name.isEmpty() || email.isEmpty() || location == null || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!store.isValidUsername(username)) {
                JOptionPane.showMessageDialog(this, "Username must be at least 4 characters and contain only letters, numbers or underscores.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!store.isValidPassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters and contain at least one digit.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (store.usernameExists(username)) {
                JOptionPane.showMessageDialog(this, "This username is already taken.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Account account = new Account(username, password);
            Customer customer = new Customer(store.getNextUserId(), name, email, location, account);

            if (!store.addUser(customer)) {
                JOptionPane.showMessageDialog(this, "Failed to create account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            FileManager.saveAll(store);
            JOptionPane.showMessageDialog(this, "Account created successfully. Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginFrame(store).setVisible(true);
        });

        cancelBtn.addActionListener(e -> {
            dispose();
            new LoginFrame(store).setVisible(true);
        });

        return panel;
    }

    /**
     * Checks whether the entered email has a valid email format.
     *
     * @param email the email address to validate
     * @return true if the email format is valid, otherwise false
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
}
