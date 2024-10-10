/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;
import java.time.LocalDate;
/**
 * descripció: Aquesta classe representa un usuari del sistema amb els atributs id, email,
 * contrasenya, nom, el descompte del treballador, la data d'alta i el rol, si 
 * és Responsable de Magatzem o Venedor.
 * 
 * @author ngall
 * @version 10/2024.1
 */
public class Usuari {
    private int idUsuari;
    private String email;
    private String password;
    private String nom;
    private Float descompte_treballador;
    private LocalDate data_alta;
    private boolean rol; //True = Responsable Magatzem , False = Venedor

    /**
     * Constructor per inicialitzar un nou usuari amb els detalls proporcionats.
     * 
     * @param idUsuari, identificador únic per cada usuari.
     * @param email, el correu electrònic de l'usuari que utilitzar1à per registrar-se.
     * @param password, la contrasenya de l'usuari que també utilitzarà per registrar-se.
     * @param nom, el nom de l'usuari.
     * @param descompte_treballador, el descompte que té assignat el treballador.
     * @param data_alta, la data d'alta de l'usuari al sistema.
     * @param rol, el rol de l'usuari si és Responsable de Magatzem (true) o Venedor(false).
     */
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

    /**
     * @return L'identificador de l'usuari.
     */
    public int getIdUsuari() {
        return idUsuari;
    }
    
    /**
     * @return El correu electrònic de l'usuari.
     */
    public String getEmail() {
        return email;
    }

    /** 
     * @return La contrasenya de l'usuari.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return El nom de l'usuari.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return El descompte del treballador.
     */
    public Float getDescompte_treballador() {
        return descompte_treballador;
    }

    /**
     * @return La data d'alta de l'usuari.
     */
    public LocalDate getData_alta() {
        return data_alta;
    }

    /**
     * @return True si és Responsable de Magatzem o False si és Venedor.
     */
    public boolean isRol() {
        return rol;
    }
    
    //Setters

    /**
     * Estableix l'identificador de l'usuari.
     * @param idUsuari El nou identificador de l'usuari.
     */
    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    /**
     * Estableix el correu electrònic de l'usuari.
     * @param email El nou correu electrònic de l'usuari.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Estableix la contrasenya de l'usuari.
     * @param password La nova contrasenya de l'usuari.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Estableix el nom de l'usuari.
     * @param nom El nou nom de l'usuari.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Estableix el descompte del treballador.
     * @param descompte_treballador El nou descompte del treballador.
     */
    public void setDescompte_treballador(Float descompte_treballador) {
        this.descompte_treballador = descompte_treballador;
    }

    /**
     * Estableix la data d'alta de l'usuari.
     * @param data_alta La nova data d'alta de l'usuari.
     */
    public void setData_alta(LocalDate data_alta) {
        this.data_alta = data_alta;
    }

    /**
     * Estableix el rol de l'usuari. 
     * @param rol El nou rol de l'usuari (true= Responsable de Magatzem, false= Venedor).
     */
    public void setRol(boolean rol) {
        this.rol = rol;
    }
}