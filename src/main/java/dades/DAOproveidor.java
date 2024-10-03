/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Proveidor;
import enums.EstatProveidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class DAOproveidor implements DAOinterface<Proveidor> {

    @Override
    public void afegir(Proveidor proveidor) {

        try (Connection conn = MyDataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO proveidor (CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alt, Qualificacio) VALUES " + "(?, ?, ?, ?, ?, ?, ?)\";");

            stmt.setString(1, proveidor.getCIF());
            stmt.setString(2, proveidor.getNom());
            stmt.setString(3, proveidor.getEstat().name());
            stmt.setString(4, proveidor.getMotiuInactiu());
            stmt.setString(5, proveidor.getTelefon());
            stmt.setFloat (6, proveidor.getDescompte());
            stmt.setDate  (7, java.sql.Date.valueOf(proveidor.getData_Alt()));
            stmt.setInt   (8, proveidor.getQualificacio());

            stmt.executeUpdate();

        } catch (Exception e) {
        }
    }

    @Override
    public List<Proveidor> obtenirEntitats() {

        List<Proveidor> proveidors = new ArrayList<>();
        try (Connection conn = MyDataSource.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM proveidor");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EstatProveidor Estat = EstatProveidor.valueOf(rs.getString("Estat"));  // Convertim el String a enum
                Proveidor proveidor = new Proveidor(
                        rs.getString("CIF"),
                        rs.getString("Nom"),
                        Estat,
                        rs.getString("MotiuInactiu"),
                        rs.getString("Telefon"),
                        rs.getFloat("Descompte"),
                        rs.getDate("Data_Alt").toLocalDate(),
                        rs.getInt("Qualificacio")
                );
                proveidors.add(proveidor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proveidors;
    }

    @Override
    public void actualitzar(Proveidor proveidor) {
        
        try (Connection conn = MyDataSource.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("UPDATE proveidor SET Nom = ?, MotiuInactiu = ?, Telefon = ?, Descompte = ?, Data_Alt = ?, Qualificacio = ? WHERE CIF = ?");

            stmt.setString(1, proveidor.getCIF());
            stmt.setString(2, proveidor.getNom());
            stmt.setString(3, proveidor.getEstat().name());
            stmt.setString(4, proveidor.getMotiuInactiu());
            stmt.setString(5, proveidor.getTelefon());
            stmt.setFloat (6, proveidor.getDescompte());
            stmt.setDate  (7, java.sql.Date.valueOf(proveidor.getData_Alt()));
            stmt.setInt   (8, proveidor.getQualificacio());

            if (stmt.executeUpdate() > 0) {
                System.out.println("Dades modificades.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Proveidor proveidor) {
        
        try (Connection conn = MyDataSource.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO proveidor (CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alt, Qualificacio) VALUES " + "(?, ?, ?, ?, ?, ?, ?)\";");

            stmt.setString(1, proveidor.CIF);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
