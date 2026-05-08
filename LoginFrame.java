
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

/**
 * Landing screen for the GUI.
 * Lets users log in or navigate to account creation.
 * On successful login, opens AdminFrame or CustomerFrame depending on role.
 */
public class LoginFrame extends JFrame {

    private final Store store;
    private JTextField     usernameField;
    private JPasswordField passwordField;

    public LoginFrame(Store store) {
        this.store = store;

        setTitle("Online Store — Login");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        add(buildHeader(),  BorderLayout.NORTH);
        add(buildForm(),    BorderLayout.CENTER);
        add(buildButtons(), BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FileManager.saveAll(store);
                System.exit(0);
            }
        });
    }

    // ── Header ────────────────────────────────────────────────────────────────

    private JPanel buildHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 80, 160));
        panel.setBorder(new EmptyBorder(14, 16, 14, 16));

        JLabel title = new JLabel("Welcome to " + store.getName());
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);

        JLabel sub = new JLabel("Please log in to continue");
        sub.setFont(new Font("Arial", Font.PLAIN, 12));
        sub.setForeground(new Color(180, 210, 255));

        JPanel text = new JPanel(new GridLayout(2, 1));
        text.setOpaque(false);
        text.add(title);
        text.add(sub);
        panel.add(text, BorderLayout.CENTER);
        return panel;
    }

    // ── Form ──────────────────────────────────────────────────────────────────

    private JPanel buildForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 40, 10, 40));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 4, 5, 4);

        usernameField = new JTextField(18);
        passwordField = new JPasswordField(18);

        c.gridx = 0; c.gridy = 0; c.weightx = 0;
        panel.add(new JLabel("Username:"), c);
        c.gridx = 1;             c.weightx = 1;
        panel.add(usernameField, c);

        c.gridx = 0; c.gridy = 1; c.weightx = 0;
        panel.add(new JLabel("Password:"), c);
        c.gridx = 1;             c.weightx = 1;
        panel.add(passwordField, c);

        return panel;
    }

    // ── Buttons ───────────────────────────────────────────────────────────────

    private JPanel buildButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        panel.setBorder(new EmptyBorder(0, 0, 6, 0));

        JButton loginBtn  = new JButton("Login");
        JButton createBtn = new JButton("Create Account");
        JButton exitBtn   = new JButton("Exit");

        loginBtn.setPreferredSize(new Dimension(100, 30));
        createBtn.setPreferredSize(new Dimension(140, 30));
        exitBtn.setPreferredSize(new Dimension(80,  30));

        loginBtn.addActionListener(e  -> doLogin());
        createBtn.addActionListener(e -> openCreate());
        exitBtn.addActionListener(e   -> { FileManager.saveAll(store); System.exit(0); });

        // Allow pressing Enter to log in
        getRootPane().setDefaultButton(loginBtn);

        panel.add(loginBtn);
        panel.add(createBtn);
        panel.add(exitBtn);
        return panel;
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    private void doLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password.",
                "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = store.login(username, password);
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                "Invalid username or password.",
                "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            return;
        }

        dispose();
        if (user instanceof Admin) {
            new AdminFrame(store, (Admin) user).setVisible(true);
        } else if (user instanceof Customer) {
            new CustomerFrame(store, (Customer) user).setVisible(true);
        }
    }

    private void openCreate() {
        dispose();
        new CreateAccountFrame(store).setVisible(true);
    }
}

