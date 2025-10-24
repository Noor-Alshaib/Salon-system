/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

/**
 *
 * @author Renaa
 */
import java.time.LocalDate;

public class Appointment {
    public LocalDate date;
    public String period;
    public String time;
    public String service;

    public Appointment(LocalDate date, String period, String time, String service) {
        this.date = date;
        this.period = period;
        this.time = time;
        this.service = service;
    }

    @Override
    public String toString() {
        return date + " | " + period + " | " + time + " | " + service;
    }
}