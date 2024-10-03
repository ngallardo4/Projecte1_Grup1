/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import java.time.LocalDate;

/**
 *
 * @author Yaiza
 */
public class Familia {

    private int id;
    private String nom;
    private String descripcio;
    private LocalDate data_alta;
    private String prov_defecte;
    private String observacions;

    public Familia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.data_alta = data_alta;
        this.prov_defecte = prov_defecte;
        this.observacions = observacions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public LocalDate getData_alta() {
        return data_alta;
    }

    public void setData_alta(LocalDate data_alta) {
        this.data_alta = data_alta;
    }

    public String getProv_defecte() {
        return prov_defecte;
    }

    public void setProv_defecte(String prov_defecte) {
        this.prov_defecte = prov_defecte;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }
}
