/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

/**
 *
 * @author Renaa
 */
public class ServiceItem {
       private String serviceName;
       private String description;
       private int duration;
       private double price;
    public ServiceItem(String serviceName, String description, int duration, double price) {
        this.serviceName = serviceName;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void displayServiceDetails(){
        System.out.println("Servise Name : "+serviceName);
         System.out.println(" Description : "+description);
          System.out.println("Duration : "+duration+"minute");
           System.out.println("Price : "+price+"SAR");
    }
}

