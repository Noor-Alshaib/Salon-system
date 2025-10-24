/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author Renaa
 */
public class SignUpPage {

    public SignUpPage() {
        JFrame frame = new JFrame("Sign Up");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Color backgroundColor = new Color(255, 240, 245);
        Color pinkText = new Color(255, 105, 180);
        Color whiteText = new Color(255, 255, 255);
        Color lightPinkBox = new Color(255, 105, 180);

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        titleLabel.setForeground(pinkText);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 20));
        formPanel.setBackground(backgroundColor);
        formPanel.setMaximumSize(new Dimension(600, 200));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email Address:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        JButton registerButton = new JButton("Sign Up");
        registerButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        registerButton.setBackground(lightPinkBox);
        registerButton.setForeground(whiteText);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(150, 40));

        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonsystem", "root", "Ee8691282")) {
                String sql = "INSERT INTO User (email, name, phone, password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, email);
                    ps.setString(2, name);
                    ps.setString(3, phone);
                    ps.setString(4, password);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                    frame.dispose();
                    new LoginPage();
                }
            } catch (SQLIntegrityConstraintViolationException ex) {
                JOptionPane.showMessageDialog(frame, "An account with this email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error registering user.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(Box.createVerticalStrut(80));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(40));
        panel.add(formPanel);
        panel.add(Box.createVerticalStrut(40));
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    
}