package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pay_bill extends JFrame implements ActionListener {
    Choice searchmonthcho;
    String meter;
    JButton pay, back;

    public pay_bill(String meter) {
        this.meter = meter;
        setSize(900, 600);
        setLocation(300, 150);
        setLayout(null);

        // UI Components
        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        // Meter Number
        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(35, 80, 200, 20);
        add(meterNumber);

        JLabel meterNumberText = new JLabel("");
        meterNumberText.setBounds(300, 80, 200, 20);
        add(meterNumberText);

        // Name
        JLabel name = new JLabel("Name");
        name.setBounds(35, 140, 200, 20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(300, 140, 200, 20);
        add(nameText);

        // Month
        JLabel month = new JLabel("Month");
        month.setBounds(35, 200, 200, 20);
        add(month);

        searchmonthcho = new Choice();
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        for (String m : months) {
            searchmonthcho.add(m);
        }
        searchmonthcho.setBounds(300, 200, 150, 20);
        add(searchmonthcho);

        // Unit
        JLabel unit = new JLabel("Unit");
        unit.setBounds(35, 260, 200, 20);
        add(unit);

        JLabel unitText = new JLabel("");
        unitText.setBounds(300, 260, 200, 20);
        add(unitText);

        // Total Bill
        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setBounds(35, 320, 200, 20);
        add(totalBill);

        JLabel totalBillText = new JLabel("");
        totalBillText.setBounds(300, 320, 200, 20);
        add(totalBillText);

        // Status
        JLabel status = new JLabel("Status");
        status.setBounds(35, 380, 200, 20);
        add(status);

        JLabel statusText = new JLabel("");
        statusText.setBounds(300, 380, 200, 20);
        statusText.setForeground(Color.RED);
        add(statusText);

        // Load customer data
        loadCustomerData(meterNumberText, nameText);

        // Month selection listener
        searchmonthcho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadBillData(unitText, totalBillText, statusText);
            }
        });

        // Buttons
        pay = new JButton("Pay");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    private void loadCustomerData(JLabel meterNumberText, JLabel nameText) {
        try (Database c = new Database()) {
            String query = "SELECT * FROM new_customer WHERE meter_no = ?";
            try (var preparedStatement = c.connection.prepareStatement(query)) {
                preparedStatement.setString(1, meter);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    meterNumberText.setText(meter);
                    nameText.setText(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading customer data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadBillData(JLabel unitText, JLabel totalBillText, JLabel statusText) {
        try (Database c = new Database()) {
            String query = "SELECT * FROM bill WHERE meter_no = ? AND month = ?";
            try (var preparedStatement = c.connection.prepareStatement(query)) {
                preparedStatement.setString(1, meter);
                preparedStatement.setString(2, searchmonthcho.getSelectedItem());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    unitText.setText(resultSet.getString("unit"));
                    totalBillText.setText(resultSet.getString("total_bill"));
                    statusText.setText(resultSet.getString("status"));
                } else {
                    unitText.setText("");
                    totalBillText.setText("");
                    statusText.setText("No bill found");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading bill data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            try (Database c = new Database()) {
                String updateQuery = "UPDATE bill SET status = 'Paid' WHERE meter_no = ? AND month = ?";
                try (var preparedStatement = c.connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, meter);
                    preparedStatement.setString(2, searchmonthcho.getSelectedItem());
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Bill paid successfully!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating payment: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new pay_bill(""));
    }
}