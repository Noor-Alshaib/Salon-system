/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.text.SimpleDateFormat;

/**
 *
 * @author Renaa
 */

import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.text.SimpleDateFormat;

public class Dashboard extends JFrame {

    private final JTextArea appointmentsArea = new JTextArea();
    private final List<Appointment2> appointments = new ArrayList<>();
    private static final Color BACKGROUND_COLOR = new Color(245, 233, 211);
    private static final Color burgundy =  new Color(128, 0, 32);
    private static final Color WHITE = Color.WHITE;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/salonsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Ee8691282";

    private final String userEmail;
    private String userName = "";
    private String userPhone = "";

    public Dashboard(String userEmail) {
        this.userEmail = userEmail;
        loadUserInfo();
        setTitle("Dashboard");
        setSize(1200, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        add(initUI());
        setVisible(true);
    }

    private void loadUserInfo() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT name, phone FROM User WHERE email = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, userEmail);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    userName = rs.getString("name");
                    userPhone = rs.getString("phone");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JPanel initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        mainPanel.add(createLeftPanel(), BorderLayout.WEST);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createRightPanel(), BorderLayout.EAST);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(BACKGROUND_COLOR);
        leftPanel.setPreferredSize(new Dimension(300, 0));

        leftPanel.add(createUserProfile());
        leftPanel.add(Box.createVerticalStrut(100));
        leftPanel.add(createActionsPanel());

        return leftPanel;
    }

    private JPanel createUserProfile() {
        JPanel panel = createStyledPanel("User Profile");
        panel.add(new JLabel("Name: " + userName));
        panel.add(new JLabel("Email: " + userEmail));
        panel.add(new JLabel("Phone: " + userPhone));
        return panel;
    }

    private JPanel createActionsPanel() {
        JPanel panel = createStyledPanel("Actions");

        String[] actions = {"Service Catalog", "Offers", "Contact Support", "Appointments Management"};
        for (String action : actions) {
            JButton btn = createStyledButton(action, e -> handleAction(action));
            panel.add(btn);
        }
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = createTitledPanel("Latest Appointment");
        appointmentsArea.setEditable(false);
        panel.add(new JScrollPane(appointmentsArea), BorderLayout.CENTER);
        updateAppointmentsDisplay();
        JButton refreshButton = new JButton("Refresh Appointments");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshButton.setForeground(burgundy);
        refreshButton.setBackground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> updateAppointmentsDisplay());

        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        refreshPanel.setBackground(BACKGROUND_COLOR);
        refreshPanel.add(refreshButton);

        panel.add(refreshPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = createTitledPanel("Tips & Care");
        JTextArea tipsArea = new JTextArea("""
                Hair Tip:
                Avoid using heat tools daily.

                Nail Tip:
                Keep nails moisturized.

                Skin Tip:
                Remove makeup before sleeping.

                Pro Tip:
                Regular trims avoid split ends.
                """);
        tipsArea.setEditable(false);
        tipsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        tipsArea.setBackground(new Color(255, 250, 252));
        panel.add(new JScrollPane(tipsArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setPreferredSize(new Dimension(0, 230));

        JPanel offersPanel = createStyledPanel("What's New?");
        offersPanel.add(createOfferCard("Beauty Updates", "Explore the latest products and trends."));
        offersPanel.add(createOfferCard("Dyes That Define You", "Try vibrant hair colors."));
        offersPanel.add(createOfferCard("Elegant Nail Designs", "Luxury nail treatments available."));

        JPanel ratingsPanel = createStyledPanel("Ratings & Reviews");
        ratingsPanel.add(createStyledReview("Haircut", "Sara Mohammed", "Rating: 5/5", "2025-03-20"));
        ratingsPanel.add(createStyledReview("Manicure", "Noor Ahmed", "Rating: 4/5", "2025-03-19"));
        ratingsPanel.add(createStyledReview("Pedicure", "Layla Ali", "Rating: 4.5/5", "2025-03-18"));

        panel.add(offersPanel);
        panel.add(ratingsPanel);

        return panel;
    }

    private JPanel createStyledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(burgundy), title));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JPanel createTitledPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(burgundy), title));
        return panel;
    }

    private JButton createStyledButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(burgundy);
        button.setBackground(new Color(245,233,211)); 
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(250, 45));
        button.setMaximumSize(new Dimension(280, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(action);
        return button;
    }
    private JPanel createOfferCard(String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(burgundy),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        titleLabel.setForeground(new Color(128, 0, 32));

        JLabel descLabel = new JLabel("<html><p style='width:180px'>" + description + "</p></html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(Color.DARK_GRAY);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(descLabel);

        return card;
    }

    private JPanel createStyledReview(String service, String name, String rating, String date) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel serviceDate = new JLabel(service + " (" + date + ")");
        serviceDate.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel reviewer = new JLabel(name + " - " + rating);
        reviewer.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(serviceDate);
        panel.add(reviewer);
        panel.add(new JSeparator());

        return panel;
    }

    private void handleAction(String action) {
        switch (action) {
            case "Service Catalog" -> new ServicesCatalog(userEmail);
            case "Offers" -> JOptionPane.showMessageDialog(this, "Exclusive Offer: 50% off your next service!");
            case "Contact Support" -> JOptionPane.showMessageDialog(this, "Contact us: 123-456-7890");
            case "Appointments Management" -> new appointmentManagement(userEmail).setVisible(true);
        }
    }

    private void updateAppointmentsDisplay() {
    appointmentsArea.setText("");

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        String sql = """
        SELECT a.date, a.time, s.name AS service_name, a.state
        FROM Appointment a
        JOIN Services s ON a.service_id = s.services_id
        WHERE a.user_email = ? AND a.date >= CURRENT_DATE
        ORDER BY a.appointment_id DESC
        LIMIT 5;
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdf12 = new SimpleDateFormat("h:mm a");

            while (rs.next()) {
                String date = rs.getString("date");
                String timeRaw = rs.getString("time");
                String formattedTime;

                try {
                    formattedTime = sdf12.format(sdf24.parse(timeRaw));
                } catch (Exception e) {
                    formattedTime = timeRaw; // fallback
                }

                String service = rs.getString("service_name");
                String state = rs.getString("state");

                String fullState = switch (state) {
                    case "P" -> "Pending";
                    case "C" -> "Completed";
                    case "X" -> "Cancelled";
                    default -> "Unknown";
                };

                sb.append(String.format("- %s | %s | %s | %s\n", date, formattedTime, service, fullState));
            }

            if (sb.length() == 0) {
                appointmentsArea.setText("No appointments booked yet.");
            } else {
                appointmentsArea.setText(sb.toString());
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        appointmentsArea.setText("Error loading appointments.");
    }
}
    
}
