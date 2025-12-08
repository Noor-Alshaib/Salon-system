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
public class ServicesCatalog extends JFrame {
    private String userEmail;
     public ServicesCatalog(String userEmail) {
        this.userEmail=userEmail;
        
        setTitle("Services Catalog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(245, 233, 211)); 
        JLabel title = new JLabel("Services Catalog", SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 47));
        title.setForeground( new Color(128, 0, 32)); 
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton hairButton = new JButton("Hair Services");
        JButton nailButton = new JButton("Nail Services");
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 36);
        Color buttonColor = new Color(128, 0, 32);
        hairButton.setFont(buttonFont);
        hairButton.setBackground(buttonColor);
        hairButton.setForeground(Color.WHITE);
        hairButton.setFocusPainted(false);
        nailButton.setFont(buttonFont);
        nailButton.setBackground(buttonColor);
        nailButton.setForeground(Color.WHITE);
        nailButton.setFocusPainted(false);

        hairButton.addActionListener(e -> {
        HairServicesPage.userEmail = this.userEmail;
        HairServicesPage.main(null);
        dispose(); 
        });
        nailButton.addActionListener(e -> {
        NailServicesPage.userEmail = this.userEmail;
        NailServicesPage.main(null);
        dispose();
        });
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.add(Box.createVerticalStrut(200));
        panel.add(title);
        panel.add(Box.createVerticalStrut(150));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
        buttonPanel.add(hairButton);
        buttonPanel.add(nailButton);
        panel.add(buttonPanel);
        add(panel);
        setVisible(true);
    }
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ServicesCatalog(null).setVisible(true);
        });
    }

}
