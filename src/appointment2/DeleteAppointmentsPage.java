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
 
