/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import java.time.LocalDate;

/**
 * descripció: Aquesta classe representa una familia del sistema amb els atributs
 * id, el nom, la descripció, la data d'alta, el proveïdor per defecte i les observacions.
 *
 * @author Yaiza
 * @version 10/2024.1
 */
public class Familia {

    private int id;
    private String nom;
    private String descripcio;
    private LocalDate data_alta;
    private String prov_defecte;
    private String observacions;

    /**
     * Constructor per inicialitzar una nova família amb els detalls proporcionats.
     * @param id, Identificador únic de la família.
     * @param nom, Nom de la família.
     * @param descripcio, Descripció de la família.
     * @param data_alta, Data d'alta de la família en el sistema.
     * @param prov_defecte, Proveïdor per defecte de la família.
     * @param observacions, Observacions addicionals sobre la família.
     */
    public Familia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.data_alta = data_alta;
        this.prov_defecte = prov_defecte;
        this.observacions = observacions;
    }

    /**
     * @return L'identificador de la família.
     */
    public int getId() {
        return id;
    }

    /**
     * Estableix l'identificador de la família.
     * @param id El nou identificador de la família.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return El nom de la família.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Estableix el nom de la família.
     * @param nom El nou nom de la família.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return La descripció de la família.
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Estableix la descripció de la família.
     * @param descripcio La nova descripció.
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * @return La data d'alta de la família.
     */
    public LocalDate getData_alta() {
        return data_alta;
    }

    /**
     * Estableix la data d'alta de la família al sistema.
     * @param data_alta la nova data d'alta.
     */
    public void setData_alta(LocalDate data_alta) {
        this.data_alta = data_alta;
    }

    /**
     * @return El proveïdor per defecte de la família.
     */
    public String getProv_defecte() {
        return prov_defecte;
    }

    /**
     * Estableix el proveïdor per defecte de la família.
     * @param prov_defecte El nou proveïdor per defecte.
     */
    public void setProv_defecte(String prov_defecte) {
        this.prov_defecte = prov_defecte;
    }

    /**
     * @return Les observacions de la família.
     */
    public String getObservacions() {
        return observacions;
    }

    /**
     * Estableix les observacions de la família.
     * @param observacions Les noves observacions de la família.
     */
    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }
    
    @Override
    public String toString() {
        return "Familia{" + "idFamilia=" + id + ", nom=" + nom + ", descripcio=" + descripcio + ", dataAlta=" + data_alta + ", proveidor=" + prov_defecte + ", observacions=" + observacions + '}';
    }
}
