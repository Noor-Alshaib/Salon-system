/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Renaa
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;

public class PaymentPage extends JFrame {

    private final Color backgroundColor = new Color(255, 240, 245);
    private final Color pinkText = new Color(255, 105, 180);
    private final Color whiteText = new Color(255, 255, 255);
    private static final String DB_URL = "jdbc:mysql://localhost:3306/salonsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Ee8691282";

    private final String userEmail;
    private final List<Appointment> appointments;

    public PaymentPage(List<Appointment> appointments, String userEmail) {
        this.userEmail = userEmail;
        this.appointments = appointments;

        setTitle("Payment Confirmation");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Review and Confirm Your Appointments");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(pinkText);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);

        StringBuilder sb = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            for (Appointment appt : appointments) {
                String serviceName = appt.service;
                String priceQuery = "SELECT price FROM Services WHERE name = ?";
                double servicePrice = 0.0;

                try (PreparedStatement ps = conn.prepareStatement(priceQuery)) {
                    ps.setString(1, serviceName);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        servicePrice = rs.getDouble("price");
                    }
                }

                sb.append(String.format("- %s | SR: %.2f\n", appt, servicePrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("Error loading services prices.\n");
        }

        textArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        scrollPane.setPreferredSize(new Dimension(600, 200));

        JRadioButton cashRadio = new JRadioButton("Pay with Cash");
        JRadioButton cardRadio = new JRadioButton("Pay with Card");
        ButtonGroup group = new ButtonGroup();
        group.add(cashRadio);
        group.add(cardRadio);
        cashRadio.setSelected(true);

        cashRadio.setBackground(backgroundColor);
        cardRadio.setBackground(backgroundColor);
        cashRadio.setForeground(pinkText);
        cardRadio.setForeground(pinkText);
        cashRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cardRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JPanel methodPanel = new JPanel();
        methodPanel.setBackground(backgroundColor);
        methodPanel.setLayout(new FlowLayout());
        methodPanel.add(cashRadio);
        methodPanel.add(cardRadio);

        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(backgroundColor);
        cardPanel.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JLabel nameLabel = new JLabel("Name on Card:");
        JLabel expLabel = new JLabel("Expiry Date (MM/YY):");
        JLabel cvvLabel = new JLabel("CVV:");

        JTextField cardNumberField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField expiryField = new JTextField(10);
        JTextField cvvField = new JTextField(5);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        cardNumberLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        expLabel.setFont(labelFont);
        cvvLabel.setFont(labelFont);

        gbc.gridx = 0; gbc.gridy = 0;
        cardPanel.add(cardNumberLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(cardNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        cardPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        cardPanel.add(expLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(expiryField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        cardPanel.add(cvvLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(cvvField, gbc);

        cashRadio.addActionListener(e -> cardPanel.setVisible(false));
        cardRadio.addActionListener(e -> cardPanel.setVisible(true));

        JButton confirm = new JButton("Confirm Payment");
        confirm.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirm.setBackground(pinkText);
        confirm.setForeground(whiteText);
        confirm.setPreferredSize(new Dimension(220, 50));
        confirm.setFocusPainted(false);

        confirm.addActionListener(e -> {
            double totalAmount = calculateTotalAmount(appointments);
            if (cardRadio.isSelected()) {
                try {
                    String cardNumber = cardNumberField.getText().trim();
                    String name = nameField.getText().trim();
                    String expiry = expiryField.getText().trim();
                    String cvv = cvvField.getText().trim();
                    validateCardInputs(cardNumber, name, expiry, cvv);
                    JOptionPane.showMessageDialog(this, "Payment confirmed via Card!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    savePaymentToDB("C", totalAmount);
                    dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Payment confirmed via Cash!", "Success", JOptionPane.INFORMATION_MESSAGE);
                savePaymentToDB("C", totalAmount);
                dispose();
            }
        });

        JPanel bottom = new JPanel();
        bottom.setBackground(backgroundColor);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        bottom.add(confirm);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(backgroundColor);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(methodPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(cardPanel);

        add(centerPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setForeground(pinkText);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.addActionListener(e -> {
            dispose(); 
            new Dashboard(userEmail);
        });

        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(backgroundColor);
        topPanel.add(backButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.PAGE_START);
        setVisible(true);
    }

    private double calculateTotalAmount(List<Appointment> appointments) {
        double total = 0.0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            for (Appointment appt : appointments) {
                String sql = "SELECT price FROM Services WHERE name = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, appt.service);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        total += rs.getDouble("price");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    private void validateCardInputs(String cardNumber, String name, String expiry, String cvv) {
        StringBuilder errors = new StringBuilder();

        if (cardNumber.isEmpty() || !cardNumber.matches("\\d{16}")) {
            errors.append("- Card number must be 16 digits.\n");
        }
        if (name.isEmpty() || !name.matches("^[A-Za-z ]+$")) {
            errors.append("- Name must contain only letters and spaces.\n");
        }
        if (expiry.isEmpty() || !expiry.matches("\\d{2}/\\d{2}")) {
            errors.append("- Expiry must be MM/YY.\n");
        }
        if (cvv.isEmpty() || !cvv.matches("\\d{3,4}")) {
            errors.append("- CVV must be 3 or 4 digits.\n");
        }

        if (errors.length() > 0) {
            throw new IllegalArgumentException(errors.toString());
        }
    }

    private void savePaymentToDB(String status, double amount) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO Payment (user_email, appointment_id, amount, payment_status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userEmail);
            ps.setInt(2, 1); // FIXME: Replace this with real appointment ID if available
            ps.setDouble(3, amount);
            ps.setString(4, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save payment.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}