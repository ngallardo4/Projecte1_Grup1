/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacio;

import dades.DAOusuari;

/**
 *
 * @author ngall
 */
public class Main {

    public static void main(String[] args) {
        String rutaArxiu = "uspass.txt";
        
        DAOusuari usuariDAO = new DAOusuari(rutaArxiu);
    }
    
}
