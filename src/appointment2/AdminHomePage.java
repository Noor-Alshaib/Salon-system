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
import java.awt.*;

public class AdminHomePage extends JFrame {


    private final Color backgroundColor = new Color(255, 240, 245); 
    private final Color pinkText = new Color(255, 105, 180);         
    private final Color whiteText = new Color(255, 255, 255);        
    private final Color lightPinkBox = new Color(255, 105, 180);     

 setTitle("Beauty Salon - Admin Home");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(backgroundColor); 
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

