/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import javax.swing.*;
import java.awt.*;


import javax.swing.*;
import java.awt.*;

public class NailServicesPage {
       
    public static String userEmail = "";
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Nail Services");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Color backgroundColor = new Color(245, 233, 211);
        Color burgundy = new Color(128, 0, 32);
        Color whiteText = new Color(255 , 255 , 255);
        Color lightBox = new Color(190, 170, 140);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Nail Services");
        title.setFont(new Font("Times New Roman", Font.BOLD, 45));
        title.setForeground(burgundy);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(40));

        String[] services = {
                "Nail Polish | 30 SAR | 20 min",
                "Nail Design | 60 SAR | 45 min",
                "Nail Care | 40 SAR | 30 min"
        };

        mainPanel.add(Box.createVerticalStrut(150));

        for (String service : services) {
            JPanel servicePanel = new JPanel();
            servicePanel.setBackground(lightBox);
            servicePanel.setMaximumSize(new Dimension(400, 40));
            servicePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel label = new JLabel(service);
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
            label.setForeground(burgundy);

            servicePanel.add(label);
            mainPanel.add(servicePanel);
            mainPanel.add(Box.createVerticalStrut(30));
        }

        JLabel questionLabel = new JLabel("Would you like to book an appointment?");
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        questionLabel.setForeground(burgundy);
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);

        JButton yesButton = new JButton("Yes");
        yesButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        yesButton.setBackground(burgundy);
        yesButton.setForeground(whiteText);
        yesButton.addActionListener(e -> {
        new AppointmentScheduler(userEmail);
        frame.dispose();
        });

        JButton noButton = new JButton("No");
        noButton.addActionListener(e -> {
        new ServicesCatalog(userEmail);
        frame.dispose();
        });
        noButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        noButton.setBackground(burgundy);
        noButton.setForeground(whiteText);

        buttonPanel.add(yesButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(noButton);

        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(questionLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
