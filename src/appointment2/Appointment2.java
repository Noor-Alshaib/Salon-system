/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

import java.time.LocalDate;

/**
 *
 * @author Renaa
 */
public class Appointment2 {
     private int duration;


    
    public String email;
    public LocalDate date;
    public String period;
    public String time;
    public String service;
    
    public Appointment2() {
    // Constructor 
}
    

    public Appointment2(String email, LocalDate date, String period, String time, String service) {
        this.email = email;
        this.date = date;
        this.period = period;
        this.time = time;
        this.service = service;
    }

    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    public int getDuration() {
    return duration;
}

public void setDuration(int duration) {
    this.duration = duration;
}


    @Override
    public String toString() {
        return email + " | " + date + " | " + period + " | " + time + " | " + service;
    }

    String getemail() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    double getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
//#