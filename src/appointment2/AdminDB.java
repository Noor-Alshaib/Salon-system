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

public class AdminDB {

    private Connection connection;
    private PreparedStatement insertNewAdmin;
    private PreparedStatement selectAllAdmins;

    public AdminDB() {
        try {
            connection = DBConnection.getConnection(); 
            createTableIfNotExists(); 
           
            selectAllAdmins = connection.prepareStatement("SELECT * FROM Admin");
            insertNewAdmin = connection.prepareStatement(
                "INSERT INTO Admin (admin_email, name, phone, password) VALUES (?, ?, ?, ?)"
            );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Admin (" +
                "admin_email VARCHAR(255) PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "phone VARCHAR(15), " +
                "password VARCHAR(255) NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
        } catch (SQLException ignored) {
        
        }
    }

    public int addAdmin(String email, String name, String phone, String password) {
        int result = 0;
        try {
            insertNewAdmin.setString(1, email);
            insertNewAdmin.setString(2, name);
            insertNewAdmin.setString(3, phone);
            insertNewAdmin.setString(4, password);
            result = insertNewAdmin.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }


    public List<Admin> getAllAdmins() {
        List<Admin> results = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            resultSet = selectAllAdmins.executeQuery();
            while (resultSet.next()) {
                results.add(new Admin(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("adminPassword")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException ignored) {}
        }

        return results;
    }

   
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    
    }
}
