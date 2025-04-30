package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    Choice loginASCho;
    TextField meterText, EmployerText, userNameText, nameText, passwordText;
    JButton create, back;

    public Signup() {
        super("Signup Page");
        getContentPane().setBackground(new Color(168, 203, 255));

        // Create Account As
        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30, 50, 125, 20);
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(170, 50, 120, 20);
        add(loginASCho);

        // Meter Number
        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);
        add(meterText);

        // Employer ID
        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30, 100, 125, 20);
        Employer.setVisible(true);
        add(Employer);

        EmployerText = new TextField();
        EmployerText.setBounds(170, 100, 125, 20);
        EmployerText.setVisible(true);
        add(EmployerText);

        // Username
        JLabel userName = new JLabel("UserName");
        userName.setBounds(30, 140, 125, 20);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170, 140, 125, 20);
        add(userNameText);

        // Name
        JLabel name = new JLabel("Name");
        name.setBounds(30, 180, 125, 20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170, 180, 125, 20);
        add(nameText);

        // Password
        JLabel password = new JLabel("Password");
        password.setBounds(30, 220, 125, 20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170, 220, 125, 20);
        add(passwordText);

        // Meter Text Focus Listener
        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                try (Database c = new Database()) {
                    String query = "SELECT * FROM signup WHERE meter_number = ?";
                    try (PreparedStatement pst = c.connection.prepareStatement(query)) {
                        pst.setString(1, meterText.getText());
                        ResultSet resultSet = pst.executeQuery();
                        if (resultSet.next()) {
                            nameText.setText(resultSet.getString("name"));
                        }
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        // Login Type Selection Listener
        loginASCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginASCho.getSelectedItem();
                if (user.equals("Customer")) {
                    Employer.setVisible(false);
                    nameText.setEditable(false);
                    EmployerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                } else {
                    Employer.setVisible(true);
                    EmployerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        // Create Button
        create = new JButton("Create");
        create.setBackground(new Color(66, 127, 219));
        create.setForeground(Color.black);
        create.setBounds(50, 285, 100, 25);
        create.addActionListener(this);
        add(create);

        // Back Button
        back = new JButton("Back");
        back.setBackground(new Color(66, 127, 219));
        back.setForeground(Color.black);
        back.setBounds(180, 285, 100, 25);
        back.addActionListener(this);
        add(back);

        // Image
        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(320, 30, 250, 250);
        add(boyLabel);

        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String suser = loginASCho.getSelectedItem();
            String smeter = meterText.getText();
            String semployer = EmployerText.getText();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();

            try (Database d = new Database()) {
                String query = "INSERT INTO signup VALUES(?, ?, ?, ?, ?)";
                try (PreparedStatement pst = d.connection.prepareStatement(query)) {
                    String id = suser.equals("Admin") ? semployer : smeter;
                    pst.setString(1, id);
                    pst.setString(2, susername);
                    pst.setString(3, sname);
                    pst.setString(4, spassword);
                    pst.setString(5, suser);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                    setVisible(false);
                    new Login();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Signup());
    }
}