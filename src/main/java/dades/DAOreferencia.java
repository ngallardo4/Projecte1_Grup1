/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Referencia;
import java.sql.*;
import enums.UnitatMesura;
import java.util.ArrayList;
import java.util.List;
import dades.MyDataSource;

/**
 *
 * @author Héctor Vico
 */
public class DAOreferencia implements DAOinterface<Referencia>{

    @Override
    public void afegir(Referencia referencia) {
         String sql = "INSERT INTO referencia (nom, UOM, id_familia, cif_proveidor, data_alta, quantitat) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, referencia.getNom());
            stmt.setString(2, referencia.getUom().name());
            stmt.setInt(3, referencia.getId_familia());
            stmt.setString(4, referencia.getCif_proveidor());
            stmt.setDate(5, Date.valueOf(referencia.getData_alta()));
            stmt.setInt(6, referencia.getQuantitat());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Referencia> obtenirEntitats() {
        List<Referencia> referencies = new ArrayList<>();
        String sql = "SELECT * FROM referencia";
        try (Connection conn = MyDataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UnitatMesura uom = UnitatMesura.valueOf(rs.getString("UOM"));  // Convertim el String a enum
                Referencia ref = new Referencia(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    uom,
                    rs.getInt("id_familia"),
                    rs.getString("cif_proveidor"),
                    rs.getDate("data_alta").toLocalDate(),
                    rs.getInt("quantitat")
                );
                referencies.add(ref);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return referencies;
    }

    @Override
    public void actualitzar(Referencia referencia) {
        String sql = "UPDATE referencia SET nom = ?, UOM = ?, id_familia = ?, cif_proveidor = ?, data_alta = ?, quantitat = ? WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, referencia.getNom());
            stmt.setString(2, referencia.getUom().name());
            stmt.setInt(3, referencia.getId_familia());
            stmt.setString(4, referencia.getCif_proveidor());
            stmt.setDate(5, Date.valueOf(referencia.getData_alta()));
            stmt.setInt(6, referencia.getQuantitat());
            stmt.setInt(7, referencia.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Referencia referencia) {
        String sql = "DELETE FROM referencia WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, referencia.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
