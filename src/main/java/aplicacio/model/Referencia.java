/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import java.time.LocalDate;

/**
 *
 * @author Héctor Vico
 */
public class Referencia {

    private int id;
    private String nom;
    private String uom;
    private int id_familia;
    private String cif_proveidor;
    private LocalDate data_alta;
    private int quantitat;

    public Referencia(int id, String nom, String uom, int id_familia, String cif_proveidor, LocalDate data_alta, int quantitat) {
        this.id = id;
        this.nom = nom;
        this.uom = uom;
        this.id_familia = id_familia;
        this.cif_proveidor = cif_proveidor;
        this.data_alta = data_alta;
        this.quantitat = quantitat;
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

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
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

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }
}
