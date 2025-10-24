/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Renaa
 */
public class custDB {
    private static final String URL = "jdbc:mysql://localhost:3306/salonsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "nn1234";
    private Connection connection;
    private PreparedStatement insertNewuser;
    private PreparedStatement selectAlluser;

    public custDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            createTableIfNotExists(); // Ensure the table is created if it doesn't exist
            selectAlluser = connection.prepareStatement("SELECT * FROM People");
            insertNewuser = connection.prepareStatement(
                    "INSERT INTO Customer "
                    + "( Name , Email, PhoneNumber , userPassword ) "
                    + "VALUES (?, ?, ?, ?)");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
        private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE Customer ("
                + "email VARCHAR(255) NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                + "name VARCHAR(255) NOT NULL, "
                + "phone VARCHAR(15), "
                + "userPassword VARCHAR(255) NOT NULL)";
        
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
        } catch (SQLException ignored) {}
    }
         public List<User> getAllCustomers() {
        List<User> results = null;
        ResultSet resultSet = null;
        try {
            resultSet = selectAlluser.executeQuery();
            results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(new User(resultSet.getString("name"),
                        resultSet.getString("userEmail"),
                        resultSet.getString("phoneNumber")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return results;
    }
          public int addUser(String name, String email, String phone, String password) {
        int result = 0;
        try {
            insertNewuser.setString(1, name);
            insertNewuser.setString(2, email);
            insertNewuser.setString(3, phone);
            insertNewuser.setString(4, password);
            result = insertNewuser.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

         public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


}
