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

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginPage {
    public LoginPage() {
        JFrame frame = new JFrame("Log in");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Color backgroundColor = new Color(255, 240, 245);
        Color pinkText = new Color(255, 105, 180);
        Color whiteText = new Color(255, 255, 255);
        Color lightPinkBox = new Color(255, 105, 180);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\userr\\Downloads\\photo_2025-03-01_05-44-16.jpg");
        Image scaledImage = logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Glow Starts Here");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setForeground(pinkText);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 20));
        formPanel.setBackground(backgroundColor);
        formPanel.setMaximumSize(new Dimension(500, 100));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        emailLabel.setForeground(pinkText);
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passwordLabel.setForeground(pinkText);
        JPasswordField passwordField = new JPasswordField();

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Log in");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginButton.setBackground(lightPinkBox);
        loginButton.setForeground(whiteText);
        loginButton.setMaximumSize(new Dimension(120, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            UIManager.put("OptionPane.background", backgroundColor);
            UIManager.put("Panel.background", backgroundColor);
            UIManager.put("OptionPane.messageForeground", pinkText);
            UIManager.put("Button.background", lightPinkBox);
            UIManager.put("Button.foreground", whiteText);
            UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.BOLD, 18));

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in both fields.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else if (!checkUserCredentials(email, password)) {
                Object[] options = {"OK", "I forgot my password"};
                int choice = JOptionPane.showOptionDialog(
                        frame,
                        "Incorrect password.",
                        "Login Failed",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null, options,
                        options[0]
                );

                if (choice == 1) {
                    ResetPassword.showResetPasswordPage(email);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Login successful.");
                frame.dispose();
                new Dashboard(email); 
            }
        });

        mainPanel.add(Box.createVerticalStrut(55));
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(loginButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private boolean checkUserCredentials(String email, String password) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonsystem", "root", "Ee8691282");
            String query = "SELECT * FROM User WHERE email = ? AND password = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                isValid = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }

    
}