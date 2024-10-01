/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import java.time.LocalDate;

/**
 *
 * @author ngall
 */
public class Proveidor {
        public String CIF;
    public String Nom;
    public String MotiuInactiu;
    public String Telefon;
    public float Descompte;
    public LocalDate Data_Alt;
    public int Qualificacio;

    public Proveidor(String CIF, String Nom, String MotiuInactiu, String Telefon, 
                     float Descompte, LocalDate Data_Alt, int Qualificacio) {
        this.CIF = CIF;
        this.Nom = Nom;
        this.MotiuInactiu = MotiuInactiu;
        this.Telefon = Telefon;
        this.Descompte = Descompte;
        this.Data_Alt = Data_Alt;
        this.Qualificacio = Qualificacio;
        }

    }
