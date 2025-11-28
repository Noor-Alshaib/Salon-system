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

        Color backgroundColor = new Color(245, 233, 211);
        Color burgundy = new Color(128, 0, 32);
        Color whiteText = new Color(255, 255, 255);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(backgroundColor);

        JLabel welcomeLabel = new JLabel("WELCOME TO BEAUTY SALON!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
        welcomeLabel.setForeground(burgundy);
        welcomeLabel.setBounds(400, 350, 800, 50);
        
        JButton signUpButton = new JButton("Sign up");
        JButton loginButton = new JButton("Log in");
        JButton adminLoginButton = new JButton("Admin Login");

        signUpButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
        adminLoginButton.setFont(new Font("Times New Roman", Font.BOLD, 21));

        signUpButton.setBounds(480, 450, 120, 40);
        loginButton.setBounds(680, 450, 120, 40);
        adminLoginButton.setBounds(880, 450, 160, 40);

        signUpButton.setBackground(burgundy);
        loginButton.setBackground(burgundy);
        adminLoginButton.setBackground(burgundy);

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
        mainPanel.add(welcomeLabel);
        mainPanel.add(signUpButton);
        mainPanel.add(loginButton);
        mainPanel.add(adminLoginButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
