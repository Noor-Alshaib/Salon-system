/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Renaa
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn33 extends JFrame {

    private User currentUser;
    private userDB userDatabase;
    private JTextField nameField, emailField, phoneField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton signInButton;
    private JLabel titleLabel, nameLabel, emailLabel, phoneLabel, passwordLabel, confirmPasswordLabel;

    public SignIn33() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        getContentPane().setBackground(new Color(255, 240, 245));
        setLayout(null);
        setVisible(true);

        userDatabase = new userDB();
        int centerX = 700; 
        int labelWidth = 200;
        int fieldWidth = 220;
        int labelFieldGap = 20;
        int startY = 200;
        int gapY = 60;

        
        titleLabel = new JLabel("Glow Starts Here");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setForeground(new Color(255, 105, 180));
        titleLabel.setBounds(centerX - 120, 100, 400, 50);
        add(titleLabel);

        
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        nameLabel.setForeground(new Color(255, 105, 180));
        nameLabel.setBounds(centerX - labelWidth, startY, labelWidth, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(centerX + labelFieldGap, startY, fieldWidth, 30);
        add(nameField);

        
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        emailLabel.setForeground(new Color(255, 105, 180));
        emailLabel.setBounds(centerX - labelWidth, startY + gapY, labelWidth, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(centerX + labelFieldGap, startY + gapY, fieldWidth, 30);
        add(emailField);

        
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        phoneLabel.setForeground(new Color(255, 105, 180));
        phoneLabel.setBounds(centerX - labelWidth, startY + 2 * gapY, labelWidth, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(centerX + labelFieldGap, startY + 2 * gapY, fieldWidth, 30);
        add(phoneField);

        
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passwordLabel.setForeground(new Color(255, 105, 180));
        passwordLabel.setBounds(centerX - labelWidth, startY + 3 * gapY, labelWidth, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(centerX + labelFieldGap, startY + 3 * gapY, fieldWidth, 30);
        add(passwordField);

        
        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        confirmPasswordLabel.setForeground(new Color(255, 105, 180));
        confirmPasswordLabel.setBounds(centerX - labelWidth - 30, startY + 4 * gapY, labelWidth + 50, 30);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(centerX + labelFieldGap, startY + 4 * gapY, fieldWidth, 30);
        add(confirmPasswordField);

        
        signInButton = new JButton("Sign Up");
        signInButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(new Color(255, 105, 180));
        signInButton.setBounds(centerX, startY + 5 * gapY + 30, 150, 50);
        add(signInButton);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {handleSignIn();
            }
        });
    }

    private void handleSignIn() {
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();
    String phone = phoneField.getText().trim();
    String password = new String(passwordField.getPassword());
    String confirmPassword = new String(confirmPasswordField.getPassword());

    if (name.isEmpty() | email.isEmpty() | phone.isEmpty() | password.isEmpty() | confirmPassword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Registration failed", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match!", "Registration failed", JOptionPane.ERROR_MESSAGE);
        return;
    }

    User user = new User(name, email, phone);

    if (!user.isNameValid()) {
        JOptionPane.showMessageDialog(this, "Invalid name. Only letters allowed.", "Registration failed", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!user.isEmailValid()) {
        JOptionPane.showMessageDialog(this, "Invalid email address.", "Registration failed", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!user.isPhoneNumberValid()) {
        JOptionPane.showMessageDialog(this, "Phone number must be 10 digits.", "Registration failed", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!user.isValidPassword(password)) {
        JOptionPane.showMessageDialog(this, "Password must be at least 8 characters and contain one special character.", "Registration failed", JOptionPane.ERROR_MESSAGE);
        return;
    }
    userDatabase.addUser(name, email, phone, password);
    new Dashboard(user.getName()).setVisible(true); 
this.dispose();
}
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new SignIn33().setVisible(true);
//        });
//    }
    
}