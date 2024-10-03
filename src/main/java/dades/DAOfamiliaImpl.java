/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Familia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yaiza
 */
public class DAOfamiliaImpl implements DAOinterface<Familia> {

    @Override
    public void afegir(Familia familia) {
        String insertSQL = "INSERT INTO familia (nom, descripcio, prov_defecte, observacions) VALUES (?, ?, ?, ?)";
        try (Connection conn = MyDataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                stmt.setString(1, familia.getNom());
                stmt.setString(2, familia.getDescripcio());
                stmt.setString(3, familia.getProv_defecte());
                stmt.setString(4, familia.getObservacions());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public List<Familia> obtenirEntitats() {
        String sql = "SELECT * FROM familia";
        List<Familia> families = new ArrayList<>();
        try (Connection conn = MyDataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Familia familia = new Familia(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("descripcio"),
                        rs.getDate("data_alta").toLocalDate(),
                        rs.getString("prov_defecte"),
                        rs.getString("observacions")
                );
                families.add(familia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return families;
    }

    @Override
    public void actualitzar(Familia familia) {
        String updateSQL = "UPDATE familia SET nom = ?, descripcio = ?, prov_defecte = ?, observacions = ? WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                stmt.setString(1, familia.getNom());
                stmt.setString(2, familia.getDescripcio());
                stmt.setString(3, familia.getProv_defecte());
                stmt.setString(4, familia.getObservacions());
                stmt.setInt(5, familia.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void eliminar(Familia familia) {
        String deleteSQL = "DELETE FROM familia WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
                stmt.setInt(1, familia.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
