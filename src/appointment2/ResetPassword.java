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

public class ResetPassword {
    public static void showResetPasswordPage(String email) {
        JFrame frame = new JFrame("Reset Password");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Color backgroundColor = new Color(245, 233, 211);
        Color burgundy = new Color(128, 0, 32);
        Color whiteText = new Color(255, 255, 255);

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Reset Your Password");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setForeground(burgundy);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBackground(backgroundColor);
        formPanel.setMaximumSize(new Dimension(550, 100));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel newPassLabel = new JLabel("Enter New Password:");
        newPassLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        newPassLabel.setForeground(burgundy);
        JPasswordField newPassField = new JPasswordField();

        JLabel confirmLabel = new JLabel("Confirm New Password:");
        confirmLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        confirmLabel.setForeground(burgundy);
        JPasswordField confirmField = new JPasswordField();

        formPanel.add(newPassLabel);
        formPanel.add(newPassField);
        formPanel.add(confirmLabel);
        formPanel.add(confirmField);

        JButton resetButton = new JButton("Reset Password");
        resetButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        resetButton.setBackground(burgundy);
        resetButton.setForeground(whiteText);
        resetButton.setMaximumSize(new Dimension(180, 40));
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        resetButton.addActionListener(e -> {
            String newPassword = new String(newPassField.getPassword());
            String confirmPassword = new String(confirmField.getPassword());

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in both fields.", "Password Reset Failed", JOptionPane.ERROR_MESSAGE);
            } else if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Password Reset Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                if (updatePasswordInDatabase(email, newPassword)) {
                    JOptionPane.showMessageDialog(frame, "Password updated successfully.");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update password. Please try again.", "Password Reset Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(Box.createVerticalStrut(150));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(120));
        panel.add(formPanel);
        panel.add(Box.createVerticalStrut(110));
        panel.add(resetButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static boolean updatePasswordInDatabase(String email, String newPassword) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonsystem", "root", "Ee8691282");

            String query = "UPDATE User SET password = ? WHERE email = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, newPassword);
            stmt.setString(2, email);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isUpdated;
    }

    
}
