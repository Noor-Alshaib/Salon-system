/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appointment2;

/**
 *
 * @author Renaa
 */
public class User {
    private String name ;
    private String userPassword ;
    private String userEmail ;
    private String phoneNumber ;
    private String role ;
    ///// constructor
    public User (String name, String userEmail, String phoneNumber) {
        this.name= name ;
//        this.userPassword= userPassword;
        this.userEmail= userEmail ;
        this.phoneNumber=phoneNumber;
        //setRole(role);
        }
    
    //getter and setter 
    public String getName() {
        return name ;
    }
    public void setName(String name) {
        this.name = name ;
    }
//     public String getUserPassword() {
//        return userPassword ;
//    }
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword ; 
//    }
    public String getUserEmail() {
        return userEmail ;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail ;
    }
     public String getPhoneNumber() {
        return phoneNumber ;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber ; 
    }
     public String getRole() {
        return role ;
    }
//    public void setRole(String role) {
//    if (role == null || role.trim().isEmpty()) {
//        System.out.println("Error: Role cannot be empty or null. Access denied.");
//        this.role = null;
//    } else if (role.equalsIgnoreCase("Customer")) {
//        this.role = role;
//    } else {
//        System.out.println("Error: Invalid role '" + role + "'. Access denied.");
//        this.role = null;
//    }
//}
        /////method to check if name contains only letters
       public boolean isNameValid() {
    if (name == null || name.isEmpty()) {
        System.out.println("Name cannot be empty.");
        return false;
    }
    for (char c : name.toCharArray()) {
        if (!Character.isLetter(c)) {
            System.out.println("Name must contain only letters.");
            return false;
        }
    }
    return true;
}
                /////method to check if number contains only numbers
    public boolean isPhoneNumberValid() {
    if (phoneNumber == null || phoneNumber.length() != 10) {
        System.out.println("Phone number must be exactly 10 digits.");
        return false;
    }
    for (char c : phoneNumber.toCharArray()) {
        if (!Character.isDigit(c)) {
            System.out.println("Phone number must contain only digits.");
            return false;
        }
    }
    return true;
 }
    public boolean isValidPassword(String password){
        if(password.length() < 8){
            System.out.println("The password can not be less then 8 numbers");
            return false;
        }
        
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";
        boolean hasSpecialCharacter = false;
        
        for (int i = 0; i < password.length(); i++ ){
            char currntChar = password.charAt(i);
             if(specialCharacters.indexOf(currntChar) != -1) {
                hasSpecialCharacter = true;
                break;  
             }
        }
        if(!hasSpecialCharacter){
            System.out.println("Error the password must contain at least one spacial character.");
        }
        return hasSpecialCharacter;
    }
    public void validatePassword(String password){
        boolean isVaild = false;
        
        while(!isVaild){
            isVaild = isValidPassword(password);
            if(!isVaild){
                System.out.println("Invalid password please try again");
                return;
            }
            
        }
    }
    
    public boolean isEmailValid(){
        if (userEmail == null || userEmail.isEmpty()) {
        System.out.println("Email cannot be empty.");
        return false;
    }
    if (userEmail.indexOf('@') == -1) {
        System.out.println("Email must contain the '@' character.");
        return false;
    }
    return true;
    }
}
