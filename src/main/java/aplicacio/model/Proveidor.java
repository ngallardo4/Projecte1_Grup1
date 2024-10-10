/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import enums.EstatProveidor;
import java.time.LocalDate;

/**
 * Clase que representa un proveedor en el sistema.
 * Contiene información sobre el proveedor como su CIF, nombre, estado,
 * motivo de inactividad, teléfono, descuento, fecha de alta y calificación.
 * 
 * @author ngall
 */
public class Proveidor {
    private String CIF;
    private String Nom;
    private EstatProveidor Estat;
    private String MotiuInactiu;
    private String Telefon;
    private float Descompte;
    private LocalDate Data_Alta;
    private int Qualificacio;

     /**
     * Constructor para crear un nuevo proveedor con todos los campos.
     *
     */
    
    public Proveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu, String Telefon, float Descompte, LocalDate Data_Alta, int Qualificacio) {
        this.CIF = CIF;
        this.Nom = Nom;
        this.Estat = Estat;
        this.MotiuInactiu = MotiuInactiu;
        this.Telefon = Telefon;
        this.Descompte = Descompte;
        this.Data_Alta = Data_Alta;
        this.Qualificacio = Qualificacio;
    }
    
    /**
     * Constructor para crear un nuevo proveedor con solo CIF y nombre.
     *
     * 
     * @param Nom El nombre del proveedor.
     */
    public Proveidor(String Nom) {
        this.Nom = Nom;
    }
    
    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public EstatProveidor getEstat() {
        return Estat;
    }

    public void setEstat(EstatProveidor Estat) {
        this.Estat = Estat;
    }
    
    public String getMotiuInactiu() {
        return MotiuInactiu;
    }

    public void setMotiuInactiu(String MotiuInactiu) {
        this.MotiuInactiu = MotiuInactiu;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public float getDescompte() {
        return Descompte;
    }

    public void setDescompte(float Descompte) {
        this.Descompte = Descompte;
    }

    public LocalDate getData_Alta() {
        return Data_Alta;
    }

    public void setData_Alta(LocalDate Data_Alta) {
        this.Data_Alta = Data_Alta;
    }

    public int getQualificacio() {
        return Qualificacio;
    }

    public void setQualificacio(int Qualificacio) {
        this.Qualificacio = Qualificacio;
    }
    
}
