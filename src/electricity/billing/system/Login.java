package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField userText;
    JPasswordField passwordText; // Changed to JPasswordField for security
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");
        getContentPane().setBackground(Color.white);

        // Username components
        JLabel username = new JLabel("UserName");
        username.setBounds(300, 60, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        // Password components
        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        passwordText = new JPasswordField(); // Changed to JPasswordField for security
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        // Login type components
        JLabel loggin = new JLabel("Loggin In As");
        loggin.setBounds(300, 140, 100, 20);
        add(loggin);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400, 140, 150, 20);
        add(loginChoice);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setBounds(330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(400, 215, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        // Profile image
        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTow = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTow);
        JLabel profileLable = new JLabel(fprofileOne);
        profileLable.setBounds(5, 5, 250, 250);
        add(profileLable);

        setSize(640, 300);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword()); // Get password securely
            String userType = loginChoice.getSelectedItem();

            try (Database db = new Database()) {
                String query = "SELECT * FROM signup WHERE username = ? AND password = ? AND user_type = ?";
                try (PreparedStatement pst = db.connection.prepareStatement(query)) {
                    pst.setString(1, username);
                    pst.setString(2, password);
                    pst.setString(3, userType);

                    ResultSet resultSet = pst.executeQuery();

                    if (resultSet.next()) {
                        String meter = resultSet.getString("meter_number"); // Changed to meter_number
                        setVisible(false);
                        new main_class(userType, meter);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
