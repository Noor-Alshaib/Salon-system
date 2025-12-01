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

public class DeleteAppointmentsPage extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public DeleteAppointmentsPage() {
         setTitle("Delete Appointments");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 233, 211));

        model = new DefaultTableModel(new String[]{"ID", "Customer Email", "Date", "Time", "Service"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton deleteButton = new JButton("Delete Selected Appointment");

        deleteButton.setBackground(new Color(128, 0, 32));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));

        deleteButton.addActionListener(e -> deleteSelectedAppointment());

        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        loadAppointments();
    }

    private void loadAppointments() {
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
                model.addRow(new Object[]{
                    rs.getInt("appointment_id"),
                    rs.getString("user_email"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("name")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading appointments.");
        }
    }

    private void deleteSelectedAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment to delete.");
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM appointment WHERE appointment_id = ?");
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Appointment deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting appointment.");
        }
    }
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DeleteAppointmentsPage().setVisible(true);
        });
     }
}
 
