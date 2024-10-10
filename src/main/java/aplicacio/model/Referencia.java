/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import enums.UnitatMesura;
import java.time.LocalDate;

/**
 * descripció: Aquesta classe representa un conjunt de productes idèntics al sistema.
 * Conté informació sobre el producte, el seu identificador, el nom, l'unitat de
 * mesura, el proveïdor, la data d'alta, el pes total, la data de caducitat, 
 * la quantidat total i el preu.
 * @author Héctor Vico
 * @version 10/2024.1
 */
public class Referencia{

    private int id;
    private String nom;
    private UnitatMesura uom;
    private int id_familia;
    private String cif_proveidor;
    private LocalDate data_alta;
    private float pes_total;
    private LocalDate data_caducitat; 
    private int quantitat_total;
    private float preu_total;
    private Proveidor proveidor;

    /**
     * Constructor per inicialitzar una nova referència amb les següents característiques.
     * @param id, identificador únic per cada referència.
     * @param nom, el nom del conjunt de productes idèntics.
     * @param uom, l'unitat de mesura del producte que pot ser: KG, L o Packs.
     * @param id_familia, l'identificador de la familía a la qual pertany.
     * @param cif_proveidor, el CIF del proveïdor del conjunt de productes idèntics.
     * @param data_alta, la data d'alta de la referència al sistema.
     * @param pes_total, el pes total del conjunt de productes idèntics.
     * @param data_caducitat, la data de caducitat del conjunt de productes idèntics.
     * @param quantitat_total, la quantitat total disponible del conjunt de productes idèntics.
     * @param preu_total, el preu total del conjunt de productes idèntics.
     */
    public Referencia(int id, String nom, UnitatMesura uom, int id_familia, String cif_proveidor, LocalDate data_alta, float pes_total, LocalDate data_caducitat, int quantitat_total, float preu_total) {
        this.id = id;
        this.nom = nom;
        this.uom = uom;
        this.id_familia = id_familia;
        this.cif_proveidor = cif_proveidor;
        this.data_alta = data_alta;
        this.pes_total = pes_total;
        this.data_caducitat = data_caducitat;
        this.quantitat_total = quantitat_total;
        this.preu_total = preu_total;
    }
    
    /**
     * @return L'identificador de la referència.
     */
    public int getId() {
        return id;
    }

    /**
     * Estableix l'identificador de la referència.
     * @param id, el nou identificador de la referència.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return El nom del conjunt de productes idèntics.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Estableix el nom de la referència
     * @param nom, el nou nom de la referència.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return L'unitat de mesura del producte que pot ser: KG, L o Packs.
     */
    public UnitatMesura getUom() {
        return uom;
    }

    /**
     * Estableix l'unitat de mesura de la referència.
     * @param uom, la nova unitat de mesura que pot ser: KG, L i Packs.
     */
    public void setUom(UnitatMesura uom) {
        this.uom = uom;
    }

    /**
     * @return L'identificador de la família a la qual pertany.
     */
    public int getId_familia() {
        return id_familia;
    }

    /**
     * Estableix l'identificador de la família.
     * @param id_familia, el nou identificador de la familía a la qual pertany.
     */
    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    /**
     * @return El CIF del proveïdor de la referència.
     */
    public String getCif_proveidor() {
        return cif_proveidor;
    }

    /**
     * Estableix el CIF del proveïdor per defecte.
     * @param cif_proveidor, el nou CIF del proveïdor del conjunt de productes idèntics.
     */
    public void setCif_proveidor(String cif_proveidor) {
        this.cif_proveidor = cif_proveidor;
    }

    /**
     * @return La data d'alta de la referència al sistema.
     */
    public LocalDate getData_alta() {
        return data_alta;
    }

    /**
     * Estableix la data d'alta de la referència al sistema.
     * @param data_alta, la nova data d'alta de la referència al sistema.
     */
    public void setData_alta(LocalDate data_alta) {
        this.data_alta = data_alta;
    }

    /**
     * @return El pes total del conjunt de productes idèntics.
     */
    public float getPes_total() {
        return pes_total;
    }

    /**
     * Estableix el pes total de la referència.
     * @param pes_total , el nou pes total.
     */
    public void setPes_total(float pes_total) {
        this.pes_total = pes_total;
    }

    /**
     * @return La data de caducitat del conjunt de productes idèntics.
     */
    public LocalDate getData_caducitat() {
        return data_caducitat;
    }

    /**
     * Estableix la data de caducitat del conjunt de productes idèntics.
     * @param data_caducitat, la nova data de caducitat.
     */
    public void setData_caducitat(LocalDate data_caducitat) {
        this.data_caducitat = data_caducitat;
    }

    /**
     * @return La quantitat total del conjunt de productes idèntics.
     */
    public int getQuantitat_total() {
        return quantitat_total;
    }

    /**
     * Estableix la quantitat total del conjunt de productes idèntics.
     * @param quantitat_total, la nova quantitat total.
     */
    public void setQuantitat_total(int quantitat_total) {
        this.quantitat_total = quantitat_total;
    }

    /**
     * @return El preu total del conjunt de productes idèntics.
     */
    public float getPreu_total() {
        return preu_total;
    }

    /**
     * Estableix el preu total de la referència.
     * @param preu_total, el nou preu total.
     */
    public void setPreu_total(float preu_total) {
        this.preu_total = preu_total;
    }

    /**
     * @return El proveïdor assignat al conjunt de productes idèntics.
     */
    public Proveidor getProveidor() {
        return proveidor;
    }

    /**
     * Estableix el proveïdor assignat a la referència.
     * @param proveidor , el nou proveïdor.
     */
    public void setProveidor(Proveidor proveidor) {
        this.proveidor = proveidor;
    }
    
}

