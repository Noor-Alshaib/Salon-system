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
public class NailService extends ServiceItem {
    private String polishType;
    private String designType;
    private String careType;

    public NailService(String serviceName, String description, int duration, double price,
                       String polishType, String designType, String careType) {
        super(serviceName, description, duration, price);
        this.polishType = polishType;
        this.designType = designType;
        this.careType = careType;
    }

    public static NailService createNailService(Scanner input) {
        String name = "";
        String description = "";
        double price = 0.0;
        int duration = 0;
        String polish = "None";
        String design = "None";
        String care = "None";

        System.out.println("Choose a nail service:");
        System.out.println("1. Nail Polish");
        System.out.println("2. Nail Design");
        System.out.println("3. Nail Care");
        System.out.print("Enter your choice number: ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                name = "Nail Polish";
                description = "Basic nail polish service";
                polish = "Standard Polish";
                price = 30.0;
                duration = 20;
                break;
            case 2:
                name = "Nail Design";
                description = "Nail design service";
                design = "Basic Design";
                price = 60.0;
                duration = 45;
                break;
            case 3:
                name = "Nail Care";
                description = "Nail care and cleaning";
                care = "Standard Care";
                price = 40.0;
                duration = 30;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Nail Polish.");
                name = "Nail Polish";
                description = "Basic nail polish service";
                polish = "Standard Polish";
                price = 30.0;
                duration = 20;
                break;
        }

        System.out.println(name + " | " + price + " SAR | " + duration + " min");

        return new NailService(name, description, duration, price, polish, design, care);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        NailService service = createNailService(input);

        ServiceDB db = new ServiceDB();
        db.addService(service.getServiceName(), service.getPrice(), service.getDuration());
        db.close();
    }
}