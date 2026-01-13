/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fincker.app;

/**
 *
 * @author Aziz
 */
public class User {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String occupation;
    private String dateOfBirth;
    private String password;

    public User(String fullName, String email, String phoneNumber, String occupation, String dateOfBirth, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; } 
    public String getPhoneNumber() { return phoneNumber; }
    public String getOccupation() { return occupation; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getPassword() { return password; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setPassword(String password) { this.password = password; } 
}
