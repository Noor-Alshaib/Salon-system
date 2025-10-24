/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

import java.sql.*;
/**
 *
 * @author Renaa
 */
public class DataBase {
   public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/salonsystem";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            
            try {
                stmt.executeUpdate("DROP TABLE appointments");
            } catch (SQLException ignored) {}

            // ️ إنشاء جدول المواعيد
            stmt.executeUpdate("CREATE TABLE appointments ("
                    + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                    + "appointment_date DATE NOT NULL, "
                    + "period VARCHAR(20) NOT NULL, "
                    + "time VARCHAR(20) NOT NULL, "
                    + "service VARCHAR(50) NOT NULL)");

            stmt.executeUpdate("INSERT INTO appointments (appointment_date, period, time, service) "
                    + "VALUES ('2025-05-01', 'Morning', '1:00 PM', 'Hair Cutting')");
            stmt.executeUpdate("INSERT INTO appointments (appointment_date, period, time, service) "
                    + "VALUES ('2025-05-02', 'Afternoon', '2:00 PM', 'Nail Design')");
            stmt.executeUpdate("INSERT INTO appointments (appointment_date, period, time, service) "
                    + "VALUES ('2025-05-03', 'Evening', '3:00 PM', 'Hair Coloring')");

            System.out.println("📅 All Appointments:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointments");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rsmd.getColumnName(i) + ": " + rs.getObject(i) + " | ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
