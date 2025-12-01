/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import javax.swing.*;
import java.awt.*;

public class appointmentManagement extends JFrame {

    private JButton myAppointmentsButton;
    private JButton availableAppointmentsButton;
    private JLabel titleLabel, subTextLabel;
    private String userEmail;

        public appointmentManagement(String userEmail) {
        this.userEmail=userEmail;
        setTitle("Appointments Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 233, 211));
        setLayout(null);
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setForeground(new Color(128, 0, 32));
        backButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.setBounds(1200, 20, 200, 30);
        backButton.addActionListener(e -> {
        dispose(); 
        new Dashboard(userEmail); 
    });
        add(backButton);
        titleLabel = new JLabel("Appointments Management");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 46));
        titleLabel.setForeground(new Color(128, 0, 32));
        titleLabel.setBounds(500, 270, 600, 50);
        add(titleLabel);
        subTextLabel = new JLabel("How can we help you today? Choose an option below.");
        subTextLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        subTextLabel.setForeground(new Color(128, 0, 32));
        subTextLabel.setBounds(500, 360, 700, 40);
        add(subTextLabel);
        myAppointmentsButton = new JButton("My Appointments");
        myAppointmentsButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        myAppointmentsButton.setBackground(new Color(128, 0, 32));
        myAppointmentsButton.setForeground(Color.WHITE);
        myAppointmentsButton.setBounds(450, 450, 250, 90);
        add(myAppointmentsButton);
        availableAppointmentsButton = new JButton("Available Appointments");
        availableAppointmentsButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        availableAppointmentsButton.setBackground(new Color(128, 0, 32));
        availableAppointmentsButton.setForeground(Color.WHITE);
        availableAppointmentsButton.setBounds(750, 450, 300, 90);
        add(availableAppointmentsButton);
        availableAppointmentsButton.addActionListener(e -> new AppointmentScheduler(userEmail));
        myAppointmentsButton.addActionListener(e -> new MyAppointment2(this.userEmail).setVisible(true));
//availableAppointmentsButton.addActionListener(e -> new AvailableAppointments());

    }
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new appointmentManagement(null).setVisible(true);
        });
    }
}
