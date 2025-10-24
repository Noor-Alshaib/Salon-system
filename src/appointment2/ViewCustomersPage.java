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

public class ViewCustomersPage extends JFrame {

    public ViewCustomersPage() {
        setTitle("View Customers");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 240, 245));

        String[] columns = {"Email", "Name", "Phone"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadCustomers(model);
    }

private void loadCustomers(DefaultTableModel model) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = DBConnection.getConnection();
        String sql = "SELECT email, name, phone FROM User";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String email = rs.getString("email");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            model.addRow(new Object[]{email, name, phone});
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading customers.");
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}


}