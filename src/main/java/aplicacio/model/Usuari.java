/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

/**
 *
 * @author ngall
 */
public class Usuari {
    private String user;
    private String password;

    public Usuari(String user, String password) {
        this.user = user;
        this.password = password;
    }

    //Getters
    
    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    //Setters

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
