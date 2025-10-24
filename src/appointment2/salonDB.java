/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
/**
 *
 * @author Renaa
 * comment
 */

    public class salonDB {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/salonsystem";
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "nn1234"; 

    // دالة لإدخال موعد جديد في قاعدة البيانات
    public static void insertAppointment(String userEmail, int serviceId, String date, String time, String state) {
        String sql = "INSERT INTO Appointment (user_email, service_id, date, time, state) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userEmail);  
            pstmt.setInt(2, serviceId);     
            pstmt.setString(3, date);       
            pstmt.setString(4, time);        
            pstmt.setString(5, state);     

            pstmt.executeUpdate();  
            System.out.println("Appointment added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getServiceIdByName(String serviceName) {
        int serviceId = -1;
        String sql = "SELECT services_id FROM services WHERE name = ?"; // تأكد من اسم العمود والجدول هنا

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, serviceName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                serviceId = rs.getInt("services_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceId;
    }
    public static void cancelAppointment(String userEmail, String date, String time) {
    String sql = "UPDATE Appointment SET state = 'C' WHERE user_email = ? AND date = ? AND time = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, userEmail);
        pstmt.setString(2, date);
        pstmt.setString(3, time);

        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("Appointment canceled successfully!");
        } else {
            System.out.println("No matching appointment found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}


