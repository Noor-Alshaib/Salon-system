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
public class AdminLoginPage {
    public AdminLoginPage() {
        JFrame frame = new JFrame("Admin Login");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color burgundy = new Color(128, 0, 32);
        frame.setResizable(false);

        Color backgroundColor = new Color(245, 233, 211); 
        Color whiteText = new Color(255, 255, 255);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Admin Access Panel");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setForeground(burgundy);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 20));
        formPanel.setBackground(backgroundColor);
        formPanel.setMaximumSize(new Dimension(500, 100));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Admin Email:");
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        emailLabel.setForeground(burgundy);
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passwordLabel.setForeground(burgundy);
        JPasswordField passwordField = new JPasswordField();

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Login in");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginButton.setBackground(burgundy);
        loginButton.setForeground(whiteText);
        loginButton.setMaximumSize(new Dimension(120, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            UIManager.put("OptionPane.background", backgroundColor);
            UIManager.put("Panel.background", backgroundColor);
            UIManager.put("OptionPane.messageForeground", burgundy);
            UIManager.put("Button.background", burgundy);
            UIManager.put("Button.foreground", whiteText);
            UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.BOLD, 18));

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in both fields.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else if (!checkAdminCredentials(email, password)) {
                JOptionPane.showMessageDialog(frame, "Incorrect email or password.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                AdminHomePage home = new AdminHomePage();
                home.setVisible(true);

            }
        });

        mainPanel.add(Box.createVerticalStrut(55));
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(loginButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }


    private boolean checkAdminCredentials(String email, String password) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonsystem", "root", "Ee8691282"); 

            String query = "SELECT * FROM Admin WHERE admin_email = ? AND password = ?";
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

    public static void main(String[] args) {
        new AdminLoginPage();
    }
}


