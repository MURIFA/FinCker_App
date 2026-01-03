/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fincker.app;

/**
 *
 * @author User
 */
public class User {
    private String username = "";
    private String password = "";
    private String rtp = "";
    
    public void setUsername (String username){
        this.username = username;
    }
    
        
    public String getUsername(){
        return this.username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setRtp(String rtp){
        this.rtp = rtp;
    }
    
    public String getRtp(){
        return this.rtp;
    }
    
    public User(String username, String password, String rtp){
        this.username = username;
        this.password = password;
        this.rtp = rtp;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        registerView fu = new registerView();
        fu.setLocationRelativeTo(null);
        fu.setVisible(true);
    }


    
}
