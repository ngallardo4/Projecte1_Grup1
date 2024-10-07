/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import enums.UnitatMesura;
import java.time.LocalDate;

/**
 * La clase {@code Referencia} representa un producto o ítem en el sistema.
 * Contiene información sobre el producto, incluyendo su identificador, nombre, unidad de medida,
 * proveedor, fechas relevantes, peso, cantidad y precio.
 * 
 * @author Héctor Vico
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
     * Crea una nueva instancia de {@code Referencia}.
     *
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

    public UnitatMesura getUom() {
        return uom;
    }

    public void setUom(UnitatMesura uom) {
        this.uom = uom;
    }

    public int getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    public String getCif_proveidor() {
        return cif_proveidor;
    }

    public void setCif_proveidor(String cif_proveidor) {
        this.cif_proveidor = cif_proveidor;
    }

    public LocalDate getData_alta() {
        return data_alta;
    }

    public void setData_alta(LocalDate data_alta) {
        this.data_alta = data_alta;
    }

    public float getPes_total() {
        return pes_total;
    }

    public void setPes_total(float pes_total) {
        this.pes_total = pes_total;
    }

    public LocalDate getData_caducitat() {
        return data_caducitat;
    }

    public void setData_caducitat(LocalDate data_caducitat) {
        this.data_caducitat = data_caducitat;
    }

    public int getQuantitat_total() {
        return quantitat_total;
    }

    public void setQuantitat_total(int quantitat_total) {
        this.quantitat_total = quantitat_total;
    }

    public float getPreu_total() {
        return preu_total;
    }

    public void setPreu_total(float preu_total) {
        this.preu_total = preu_total;
    }

    public Proveidor getProveidor() {
        return proveidor;
    }

    public void setProveidor(Proveidor proveidor) {
        this.proveidor = proveidor;
    }
    
}

