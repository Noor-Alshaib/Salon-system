/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReviewWindow extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(245, 233, 211);
    private static final Color burgundy =new Color(128, 0, 32);

    public ReviewWindow(List<Appointment> appointments) {
        setTitle("Pending Appointments");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Your Pending Appointments:");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(burgundy);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        for (Appointment appt : appointments) {
            sb.append("- ").append(appt).append("\n");
        }
        textArea.setText(sb.toString());

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton close = new JButton("Close");
        close.setBackground(burgundy);
        close.setForeground(Color.WHITE);
        close.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setBackground(BACKGROUND_COLOR);
        bottom.add(close);

        add(bottom, BorderLayout.SOUTH);
        
        setVisible(true);
    }
}
