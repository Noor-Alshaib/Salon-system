/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

import java.util.Scanner;

/**
 *
 * @author Renaa
 */
public class HairService extends ServiceItem {
    private String haircut;
    private String hairStyling;
    private String hairDye;

    public HairService(String serviceName, String description, int duration, double price,
                       String haircut, String hairStyling, String hairDye) {
        super(serviceName, description, duration, price);
        this.haircut = haircut;
        this.hairStyling = hairStyling;
        this.hairDye = hairDye;
    }

    public static HairService createHairService(Scanner input) {
        String hairServiceName = "";
        String hairDescription = "";
        int hairDuration = 0;
        double hairPrice = 0.0;
        String haircut = "None";
        String hairStyling = "None";
        String hairDye = "None";

        System.out.println("Choose a hair service:");
        System.out.println("1. Hair Cutting");
        System.out.println("2. Hair Styling");
        System.out.println("3. Hair Coloring");
        System.out.print("Enter your choice number: ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                hairServiceName = "Hair Cutting";
                hairDescription = "Hair cutting service";
                haircut = "Standard Cut";
                hairPrice = 50.0;
                hairDuration = 30;
                break;
            case 2:
                hairServiceName = "Hair Styling";
                hairDescription = "Hair styling service";
                hairStyling = "Standard Styling";
                hairPrice = 70.0;
                hairDuration = 40;
                break;
            case 3:
                hairServiceName = "Hair Coloring";
                hairDescription = "Hair dyeing service";
                hairDye = "Standard Color";
                hairPrice = 150.0;
                hairDuration = 90;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Hair Cutting.");
                hairServiceName = "Hair Cutting";
                hairDescription = "Hair cutting service";
                haircut = "Standard Cut";
                hairPrice = 50.0;
                hairDuration = 30;
                break;
        }

        System.out.println(hairServiceName + " | " + hairPrice + " SAR | " + hairDuration + " min");

        return new HairService(hairServiceName, hairDescription, hairDuration, hairPrice, haircut, hairStyling, hairDye);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HairService service = createHairService(input);

        ServiceDB db = new ServiceDB();
        db.addService(service.getServiceName(), service.getPrice(), service.getDuration());
        db.close();
    }
}
