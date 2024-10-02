/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Proveidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danie
 */
public class DAOproveidor implements DAOinterface<Proveidor> {

    @Override
    public void afegir(Proveidor proveidor) {
        
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projecte1?useUnicode=true&serverTimezone=Europe/Madrid", "root", "123456")){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO proveidor (CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alt, Qualificacio) VALUES " + "(?, ?, ?, ?, ?, ?, ?)\";");
            
            stmt.setString  (1, proveidor.CIF);
            stmt.setString  (2, proveidor.Nom);
            stmt.setString  (3, proveidor.getEstat().name());
            stmt.setString  (4, proveidor.MotiuInactiu);
            stmt.setString  (5, proveidor.Telefon);
            stmt.setFloat   (6, proveidor.Descompte);
            //La línea asegura que el valor de fecha se inserte correctamente en la base de datos, 
            //utilizando el tipo adecuado para evitar errores y garantizar el uso correcte de datos de fecha.
            stmt.setDate    (7, java.sql.Date.valueOf(proveidor.getData_Alt()));
            stmt.setInt     (8, proveidor.Qualificacio);
            
        }catch(Exception e){}
    }

    @Override
    public List<Proveidor> obtenirEntitats() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualitzar(Proveidor entitat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Proveidor entitat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
