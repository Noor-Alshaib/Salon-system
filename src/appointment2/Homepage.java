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
public class Homepage {
    public static void main(String[] args) {
        new Homepage();
    }

    public Homepage() {
        JFrame frame = new JFrame("Beauty Salon - Home");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Color backgroundColor = new Color(255, 240, 245);
        Color pinkText = new Color(255, 105, 180);
        Color whiteText = new Color(255, 255, 255);
        Color lightPinkBox = new Color(255, 105, 180);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(backgroundColor);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\userr\\Downloads\\photo_2025-03-01_05-44-16.jpg");
        Image scaledImage = logoIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setBounds(200, 200, 400, 400);

        JLabel welcomeLabel = new JLabel("WELCOME TO BEAUTY SALON!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
        welcomeLabel.setForeground(pinkText);
        welcomeLabel.setBounds(700, 350, 800, 50);

        JButton signUpButton = new JButton("Sign up");
        JButton loginButton = new JButton("Log in");
        JButton adminLoginButton = new JButton("Admin Login");

        signUpButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
        adminLoginButton.setFont(new Font("Times New Roman", Font.BOLD, 21));

        signUpButton.setBounds(800, 450, 120, 40);
        loginButton.setBounds(1000, 450, 120, 40);
        adminLoginButton.setBounds(1200, 450, 160, 40);

        signUpButton.setBackground(lightPinkBox);
        loginButton.setBackground(lightPinkBox);
        adminLoginButton.setBackground(lightPinkBox);

        signUpButton.setForeground(whiteText);
        loginButton.setForeground(whiteText);
        adminLoginButton.setForeground(whiteText);

        signUpButton.setFocusPainted(false);
        loginButton.setFocusPainted(false);
        adminLoginButton.setFocusPainted(false);

        loginButton.addActionListener(e -> {
            frame.dispose();
            new LoginPage();
        });

        signUpButton.addActionListener(e -> {
            frame.dispose();
            new SignIn33();
        });

        loginButton.addActionListener(e -> {
            frame.dispose();
            new LoginPage();
        });

        adminLoginButton.addActionListener(e -> {
            frame.dispose();
            new AdminLoginPage();
        });

        adminLoginButton.addActionListener(e -> {
            frame.dispose();
            new AdminLoginPage();
        });

        mainPanel.add(logoLabel);
        mainPanel.add(welcomeLabel);
        mainPanel.add(signUpButton);
        mainPanel.add(loginButton);
        mainPanel.add(adminLoginButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}