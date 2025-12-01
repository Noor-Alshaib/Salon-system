/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

/**
 *
 * @author Renaa
 */
import javax.swing.*;
import java.awt.*;

public class AdminHomePage extends JFrame {


    private final Color backgroundColor = new Color(255, 240, 245); 
    private final Color pinkText = new Color(255, 105, 180);         
    private final Color whiteText = new Color(255, 255, 255);        
    private final Color lightPinkBox = new Color(255, 105, 180);     

 setTitle("Beauty Salon - Admin Home");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(backgroundColor); 
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

 
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);


        
        JLabel welcomeLabel = new JLabel("WELCOME TO OUR BEAUTY SALON ADMIN'S HOMEPAGE!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(40, 40, 50, 50);

        welcomeLabel.setFont(new Font("MV Boil", Font.BOLD, 28));
        welcomeLabel.setForeground(burgundy); 
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 120, 120));
        buttonPanel.setBackground(backgroundColor);

        JButton viewCustomersButton = createStyledButton("View Customers");
        JButton editAppointmentsButton = createStyledButton("Edit Appointments");
        JButton deleteAppointmentsButton = createStyledButton("Delete Appointments");
        JButton logoutButton = createStyledButton("Logout");
        JButton addAppointmentButton = createStyledButton("Add Appointment");

        buttonPanel.add(addAppointmentButton); 
        buttonPanel.add(viewCustomersButton);
        buttonPanel.add(editAppointmentsButton);
        buttonPanel.add(deleteAppointmentsButton);
        buttonPanel.add(logoutButton);

        
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        
        viewCustomersButton.addActionListener(e -> new ViewCustomersPage().setVisible(true));
        
        logoutButton.addActionListener(e -> {
            dispose();
            JOptionPane.showMessageDialog(null, "Logged out!");
        });
        editAppointmentsButton.addActionListener(e -> new ViewAppointmentsPage().setVisible(true));
        deleteAppointmentsButton.addActionListener(e -> new DeleteAppointmentsPage().setVisible(true));
        addAppointmentButton.addActionListener(e -> new AddAppointmentPage().setVisible(true));


    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(burgundy); 
        button.setForeground(whiteText);    
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(180, 40));
        return button;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminHomePage().setVisible(true);
        });
    }
}
