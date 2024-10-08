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
    public String CIF;
    public String Nom;
    public EstatProveidor Estat;
    public String MotiuInactiu;
    public String Telefon;
    public float Descompte;
    public LocalDate Data_Alt;
    public int Qualificacio;

     /**
     * Constructor para crear un nuevo proveedor con todos los campos.
     *
     */
    
    public Proveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu, String Telefon, float Descompte, LocalDate Data_Alt, int Qualificacio) {
        this.CIF = CIF;
        this.Nom = Nom;
        this.Estat = Estat;
        this.MotiuInactiu = MotiuInactiu;
        this.Telefon = Telefon;
        this.Descompte = Descompte;
        this.Data_Alt = Data_Alt;
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

    public LocalDate getData_Alt() {
        return Data_Alt;
    }

    public void setData_Alt(LocalDate Data_Alt) {
        this.Data_Alt = Data_Alt;
    }

    public int getQualificacio() {
        return Qualificacio;
    }

    public void setQualificacio(int Qualificacio) {
        this.Qualificacio = Qualificacio;
    }
    
}
