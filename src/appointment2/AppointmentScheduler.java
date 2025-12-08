/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


/**
 *
 * @author Renaa
 */

import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AppointmentScheduler extends JFrame {

    private final List<Appointment> pendingAppointments = new ArrayList<>();
    private final String userEmail;
    private LocalDate selectedDate;
    private String selectedPeriod;
    private final JPanel dynamicPanel = new JPanel();
    private final JPanel calendarPanel = new JPanel(new GridLayout(0, 7, 10, 10));
    private final JComboBox<String> serviceComboBox = new JComboBox<>();

    private static final Color BACKGROUND_COLOR = new Color(255, 240, 245);
    private static final Color PINK = new Color(255, 105, 180);
    private static final Color WHITE = Color.WHITE;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/salonsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Ee8691282";

    public AppointmentScheduler(String userEmail) {
        this.userEmail = userEmail;

        setTitle("Hair Salon - Appointment Scheduler");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        loadServicesFromDB();

        add(createTitle(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setForeground(PINK);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.addActionListener(e -> {
            dispose(); 
            new Dashboard(userEmail); 
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(backButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.PAGE_START);
        setVisible(true);
    }

    private void loadServicesFromDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM Services")) {
            while (rs.next()) {
                serviceComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load services from database", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel createTitle() {
        JLabel title = new JLabel("Pick a Service and Date", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(PINK);
        return title;
    }

    private JPanel createCenterPanel() {
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel, BoxLayout.Y_AXIS));
        dynamicPanel.setBackground(BACKGROUND_COLOR);

        serviceComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        serviceComboBox.setBackground(PINK);
        serviceComboBox.setForeground(WHITE);
        serviceComboBox.setMaximumSize(new Dimension(250, 30));
        serviceComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        dynamicPanel.add(Box.createVerticalStrut(20));
        dynamicPanel.add(serviceComboBox);
        dynamicPanel.add(Box.createVerticalStrut(30));

        calendarPanel.setPreferredSize(new Dimension(800, 600));
        calendarPanel.setBackground(BACKGROUND_COLOR);
        drawCalendar(LocalDate.now());
        dynamicPanel.add(calendarPanel);

        JPanel wrapper = new JPanel();
        wrapper.setBackground(BACKGROUND_COLOR);
        wrapper.add(dynamicPanel);

        return wrapper;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(BACKGROUND_COLOR);

        JButton reviewButton = createButton("Review Appointments", e -> new ReviewWindow(pendingAppointments));
        JButton payButton = createButton("Go to Payment", e -> proceedToPayment());

        bottomPanel.add(reviewButton);
        bottomPanel.add(payButton);

        return bottomPanel;
    }

    private void drawCalendar(LocalDate date) {
        calendarPanel.removeAll();
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (String day : days) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setForeground(PINK);
            calendarPanel.add(label);
        }

        LocalDate firstDay = date.withDayOfMonth(1);
        int startDay = firstDay.getDayOfWeek().getValue();
        int totalDays = date.lengthOfMonth();

        for (int i = 1; i < startDay; i++) calendarPanel.add(new JLabel(""));

        for (int day = 1; day <= totalDays; day++) {
            LocalDate currentDay = date.withDayOfMonth(day);
            JButton button = createButton(String.valueOf(day), e -> showPeriods(currentDay));
            calendarPanel.add(button);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private void showPeriods(LocalDate date) {
        if (serviceComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a service first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        selectedDate = date;
        removeDynamicComponentsFrom(2);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(BACKGROUND_COLOR);

        JLabel label = new JLabel("Select time for " + date);
        label.setForeground(PINK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.add(label);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(BACKGROUND_COLOR);

        for (String period : new String[]{"Morning", "Afternoon", "Evening"}) {
            JButton button = createButton(period, e -> showSlots(period));
            buttonsPanel.add(button);
        }

        wrapper.add(buttonsPanel);
        dynamicPanel.add(wrapper);
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void showSlots(String period) {
        selectedPeriod = period;
        removeDynamicComponentsFrom(3);

        JPanel slotPanel = new JPanel();
        slotPanel.setLayout(new BoxLayout(slotPanel, BoxLayout.Y_AXIS));
        slotPanel.setBackground(BACKGROUND_COLOR);

        JLabel label = new JLabel("Select time - " + period + " on " + selectedDate);
        label.setForeground(PINK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        slotPanel.add(label);

        JPanel timeButtons = new JPanel();
        timeButtons.setBackground(BACKGROUND_COLOR);

        String[] times = switch (period) {
            case "Morning" -> new String[]{"9:00 AM", "10:00 AM", "11:00 AM"};
            case "Afternoon" -> new String[]{"12:00 PM", "1:00 PM", "2:00 PM"};
            case "Evening" -> new String[]{"4:00 PM", "5:00 PM", "6:00 PM"};
            default -> new String[]{"1:00 PM"};
        };

        for (String time : times) {
            JButton button = createButton(time, e -> {
                String serviceName = (String) serviceComboBox.getSelectedItem();
                LocalDate date = selectedDate;
                String sqlTime = convertTo24H(time);

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                    String getServiceId = "SELECT services_id FROM Services WHERE name = ?";
                    int serviceId = -1;

                    try (PreparedStatement ps = conn.prepareStatement(getServiceId)) {
                        ps.setString(1, serviceName);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) serviceId = rs.getInt("services_id");
                    }

                    if (serviceId != -1) {
                        String insertSQL = "INSERT INTO Appointment (user_email, service_id, date, time, state) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                            ps.setString(1, userEmail);
                            ps.setInt(2, serviceId);
                            ps.setDate(3, java.sql.Date.valueOf(date));
                            ps.setTime(4, java.sql.Time.valueOf(sqlTime));
                            ps.setString(5, "P");
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(this, "Appointment saved to database!");
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error saving appointment", "DB Error", JOptionPane.ERROR_MESSAGE);
                }

                pendingAppointments.add(new Appointment(date, period, time, serviceName));
            });
            timeButtons.add(button);
        }

        slotPanel.add(timeButtons);
        dynamicPanel.add(slotPanel);
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void proceedToPayment() {
        if (pendingAppointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No appointments to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        new PaymentPage(pendingAppointments, userEmail);
    }

    private void removeDynamicComponentsFrom(int index) {
        while (dynamicPanel.getComponentCount() > index) {
            dynamicPanel.remove(index);
        }
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(PINK);
        button.setForeground(WHITE);
        button.addActionListener(action);
        return button;
    }

    private String convertTo24H(String time12h) {
        try {
            java.text.SimpleDateFormat sdf12 = new java.text.SimpleDateFormat("h:mm a");
            java.text.SimpleDateFormat sdf24 = new java.text.SimpleDateFormat("HH:mm:ss");
            return sdf24.format(sdf12.parse(time12h));
        } catch (Exception e) {
            e.printStackTrace();
            return "00:00:00";
        }
    }
}