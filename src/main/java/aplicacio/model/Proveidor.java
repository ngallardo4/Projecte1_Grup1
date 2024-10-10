/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import enums.EstatProveidor;
import java.time.LocalDate;

/**
 * descripció: Aquesta classe representa un proveïdor al sistema.
 * Conté informació sobre el proveïdor com el seu CIF, nom, estat (Actiu o Inactiu),
 * motiu d'inactivitat, número de telèfon, descompte, data d'alta i qualificació.
 * 
 * @author ngall
 * @version 10/2024.1
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
     * Constructor per crear un nou proveïdor amb els detalls proporcionats.
     * @param CIF, identificador únic per cada proveïdor.
     * @param Nom, el nom del proveïdor.
     * @param Estat, l'estat en què es troba el proveïdor. (Actiu o Inactiu)
     * @param MotiuInactiu, si l'estat es inactiu detallarem en aquest paràmetre el motiu.
     * @param Telefon, el telèfon del proveïdor.
     * @param Descompte, el descompte del proveïdor.
     * @param Data_Alta, la data d'alta del proveïdor al sistema.
     * @param Qualificacio, la qualificació general que té el proveïdor.
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
     * Constructor per crear un nou proveïdor només amb el nom.
     * @param CIF El cif del proveïdor.
     */
    public Proveidor(String CIF) {
        this.CIF = CIF;
    }
    
    /**
     * @return el CIF del proveïdor.
     */
    public String getCIF() {
        return CIF;
    }

    /**
     * Estableix el CIF del proveïdor.
     * @param CIF, l'identificador únic del proveïdor.
     */
    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    /**
     * @return el nom del proveïdor.
     */
    public String getNom() {
        return Nom;
    }

    /**
     * Estableix el nom del proveïdor.
     * @param Nom, el nom del proveïdor.
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * @return l'estat del proveïdor (Actiu o Inactiu).
     */
    public EstatProveidor getEstat() {
        return Estat;
    }

    /**
     * Estableix l'estat del proveïdor
     * @param Estat, l'estat del proveïdor, pot ser: Actiu o inactiu.
     */
    public void setEstat(EstatProveidor Estat) {
        this.Estat = Estat;
    }
    
    /**
     * @return el motiu d'inactivitat del proveïdor.
     */
    public String getMotiuInactiu() {
        return MotiuInactiu;
    }

    /**
     * Estableix el motiu d'inactivitat del proveïdor.
     * @param MotiuInactiu, el motiu pel qual el proveïdor està inactiu.
     */
    public void setMotiuInactiu(String MotiuInactiu) {
        this.MotiuInactiu = MotiuInactiu;
    }

    /**
     * @return el telèfon del proveïdor.
     */
    public String getTelefon() {
        return Telefon;
    }

    /**
     * Estableix el telèfon del proveïdor.
     * @param Telefon, el telèfon del proveïdor.
     */
    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    /**
     * @return el descompte del proveïdor.
     */
    public float getDescompte() {
        return Descompte;
    }

    /**
     * Estableix el descompte del proveïdor.
     * @param Descompte, el descompte del proveïdor.
     */
    public void setDescompte(float Descompte) {
        this.Descompte = Descompte;
    }

    /**
     * @return la data d'alta del proveïdor al sistema.
     */
    public LocalDate getData_Alta() {
        return Data_Alta;
    }

    /**
     * Estableix la data d'alta del proveïdor al sistema.
     * @param Data_Alta, la data d'alta del proveïdor.
     */
    public void setData_Alta(LocalDate Data_Alta) {
        this.Data_Alta = Data_Alta;
    }

    /**
     * @return la qualificació general del proveïdor.
     */
    public int getQualificacio() {
        return Qualificacio;
    }

    /**
     * Estableix la qualificació general del proveïdor.
     * @param Qualificacio, la qualificació del proveïdor.
     */
    public void setQualificacio(int Qualificacio) {
        this.Qualificacio = Qualificacio;
    }
    
}
