/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import enums.UnitatMesura;
import java.time.LocalDate;

/**
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
    private float pes;
    private LocalDate data_caducitat; 
    private int quantitat;
    private float preu;
    private Proveidor proveidor;

    public Referencia(int id, String nom, UnitatMesura uom, int id_familia, String cif_proveidor, LocalDate data_alta, float pes, LocalDate data_caducitat, int quantitat, float preu) {
        this.id = id;
        this.nom = nom;
        this.uom = uom;
        this.id_familia = id_familia;
        this.cif_proveidor = cif_proveidor;
        this.data_alta = data_alta;
        this.pes = pes;
        this.data_caducitat = data_caducitat; 
        this.quantitat = quantitat;
        this.preu = preu;
    }

    public LocalDate getData_caducitat() {
        return data_caducitat;
    }

    public void setData_caducitat(LocalDate data_caducitat) {
        this.data_caducitat = data_caducitat;
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

    public float getPes() {
        return pes;
    }

    public void setPes(float pes) {
        this.pes = pes;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public Proveidor getProveidor() {
        return proveidor;
    }

    public void setProveidor(Proveidor proveidor) {
        this.proveidor = proveidor;
    }
}

