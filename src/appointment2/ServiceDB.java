/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renaa
 */
public class ServiceDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/salonsystem";
    private Connection connection;
    private PreparedStatement insertService;
    private PreparedStatement selectAllServices;

    public ServiceDB() {
        try {
            connection = DriverManager.getConnection(DB_URL);

            createTableIfNotExists();

            insertService = connection.prepareStatement(
                    "INSERT INTO Services (name, price, duration) VALUES (?, ?, ?)"
            );

            selectAllServices = connection.prepareStatement("SELECT * FROM Services");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE Services (" +
                "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "duration INT NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Services table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Services table already exists.");
            } else {
                System.out.println("Error creating table: " + e.getMessage());
            }
        }
    }

    public void addService(String name, double price, int duration) {
        try {
            insertService.setString(1, name);
            insertService.setDouble(2, price);
            insertService.setInt(3, duration);
            int rows = insertService.executeUpdate();
            if (rows > 0) {
                System.out.println("Service added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding service: " + e.getMessage());
        }
    }

    public List<String> getAllServicesByKeyword(String keyword) {
        List<String> services = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = selectAllServices.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int duration = resultSet.getInt("duration");

                if (name.toLowerCase().contains(keyword.toLowerCase())) {
                    String info = name + " | " + price + " SAR | " + duration + " min";
                    services.add(info);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching services: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return services;
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}