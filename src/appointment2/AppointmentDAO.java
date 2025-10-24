/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

/**
 *
 * @author Renaa
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
   
    private Connection conn;

    public AppointmentDAO() throws SQLException {
        conn = DriverManager.getConnection(
           "jdbc:mysql://localhost:3306/salonsystem", "root", "Ee8691282"
        );
    }

    public void save(Appointment2 appt) throws SQLException {
        String sql = "INSERT INTO appointment (email, date, period, time, service) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, appt.email);
        stmt.setDate(2, Date.valueOf(appt.date));
        stmt.setString(3, appt.period);
        stmt.setString(4, appt.time);
        stmt.setString(5, appt.service);
        stmt.executeUpdate();
    }

    public List<Appointment2> getByEmail(String email) throws SQLException {
        String sql = "SELECT appointment.user_email, appointment.date, appointment.time, services.name, services.duration " +
                        "FROM appointment " +
                        "JOIN services  ON appointment.service_id = services.services_id " + 
                        "WHERE user_email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        List<Appointment2> appointments = new ArrayList<>();
        while (rs.next()) {
            Appointment2 appt = new Appointment2(
                rs.getString("user_email"),
                rs.getDate("date").toLocalDate(),
                rs.getString("duration"),
                rs.getString("time"),
                rs.getString("name")
            );
            appointments.add(appt);
        }
        return appointments;
    }
}