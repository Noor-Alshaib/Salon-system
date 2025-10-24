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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewAppointmentsPage extends JFrame {

    public ViewAppointmentsPage() {
        setTitle("View Appointments");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 240, 245));

        String[] columns = {"ID", "Customer Email", "Date", "Time", "Service"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadAppointments(model);
    }

    private void loadAppointments(DefaultTableModel model) {
try (
            Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
    "SELECT appointment.appointment_id, appointment.user_email, appointment.date, appointment.time, services.name " +
    "FROM appointment  " +
    "JOIN services  ON appointment.service_id = services.services_id"
);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("appointment_id");
                String email = rs.getString("user_email");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String service = rs.getString("name");
                model.addRow(new Object[]{id, email, date, time, service});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading appointments.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewAppointmentsPage().setVisible(true);
        });
    }
}