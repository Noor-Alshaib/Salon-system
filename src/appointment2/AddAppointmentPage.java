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
import java.sql.*;
import java.util.HashMap;

public class AddAppointmentPage extends JFrame {
    private JTextField emailField, dateField, timeField;
    private JComboBox<String> serviceComboBox;
    private HashMap<String, Integer> serviceMap = new HashMap<>();

    public AddAppointmentPage() {
        setTitle("Add New Appointment");
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 240, 245));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
       
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Dimension fieldSize = new Dimension(200, 30);

       
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel emailLabel = new JLabel("Customer Email:");
        emailLabel.setFont(labelFont);
        add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField();
        emailField.setFont(fieldFont);
        emailField.setPreferredSize(fieldSize);
        add(emailField, gbc);

        // Date
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setFont(labelFont);
        add(dateLabel, gbc);

        gbc.gridx = 1;
        dateField = new JTextField();
        dateField.setFont(fieldFont);
        dateField.setPreferredSize(fieldSize);
        add(dateField, gbc);

      
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel timeLabel = new JLabel("Time (HH:MM):");
        timeLabel.setFont(labelFont);
        add(timeLabel, gbc);

        gbc.gridx = 1;
        timeField = new JTextField();
        timeField.setFont(fieldFont);
        timeField.setPreferredSize(fieldSize);
        add(timeField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel serviceLabel = new JLabel("Select Service:");
        serviceLabel.setFont(labelFont);
        add(serviceLabel, gbc);

        gbc.gridx = 1;
        serviceComboBox = new JComboBox<>();
        serviceComboBox.setFont(fieldFont);
        serviceComboBox.setPreferredSize(fieldSize);
        loadServices();
        add(serviceComboBox, gbc);

        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JButton saveButton = new JButton("Save Appointment");
        saveButton.setBackground(new Color(255, 105, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setPreferredSize(new Dimension(250, 40));
        saveButton.addActionListener(e -> saveAppointment());
        add(saveButton, gbc);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void loadServices() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT services_id, name FROM Services")) {

            while (rs.next()) {
                int id = rs.getInt("services_id");
                String name = rs.getString("name");
                serviceComboBox.addItem(name);
                serviceMap.put(name, id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading services.");
        }
    }

    private void saveAppointment() {
        String email = emailField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String selectedService = (String) serviceComboBox.getSelectedItem();

        if (email.isEmpty() | date.isEmpty() | time.isEmpty() | selectedService == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int serviceId = serviceMap.get(selectedService);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Appointment (user_email, service_id, date, time, state) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, email);
            stmt.setInt(2, serviceId);
            try{
            stmt.setDate(3, Date.valueOf(date));
            }catch(IllegalArgumentException ex){
                JOptionPane.showMessageDialog(this,"Invalid date formate yyyy-mm-dd.");
            }
            stmt.setTime(4, Time.valueOf(time + ":00"));
            stmt.setString(5, "P");
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Appointment added successfully.");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving appointment.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddAppointmentPage().setVisible(true);
        });
    }
}
