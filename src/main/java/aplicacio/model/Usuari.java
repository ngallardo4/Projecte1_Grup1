/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;
import java.time.LocalDate;
/**
 *
 * @author ngall
 */
public class Usuari {
    private int idUsuari;
    private String email;
    private String password;
    private String nom;
    private Float descompte_treballador;
    private LocalDate data_alta;
    private boolean rol; //True = Responsable Magatzem , False = Venedor

    public Usuari(int idUsuari, String email, String password, String nom, Float descompte_treballador, LocalDate data_alta, boolean rol) {
        this.idUsuari = idUsuari;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.descompte_treballador = descompte_treballador;
        this.data_alta = data_alta;
        this.rol = rol;
    }

    //Getters

    public int getIdUsuari() {
        return idUsuari;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public Float getDescompte_treballador() {
        return descompte_treballador;
    }

    public LocalDate getData_alta() {
        return data_alta;
    }

    public boolean isRol() {
        return rol;
    }
    
    //Setters

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescompte_treballador(Float descompte_treballador) {
        this.descompte_treballador = descompte_treballador;
    }

    public void setData_alta(LocalDate data_alta) {
        this.data_alta = data_alta;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }
}