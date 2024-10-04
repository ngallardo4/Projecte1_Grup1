/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Referencia;
import aplicacio.model.Proveidor;
import java.sql.*;
import enums.UnitatMesura;
import java.util.ArrayList;
import java.util.List;
import dades.MyDataSource;

/**
 *
 * @author Héctor Vico
 */
public class DAOreferenciaImpl implements DAOinterface<Referencia>, DAOreferencia<Referencia> {

    @Override
    public void afegir(Referencia referencia) {
        // Añadir "data_caducitat" a la sentencia SQL
        String sql = "INSERT INTO referencia (nom, UOM, id_familia, cif_proveidor, data_alta, pes, data_caducitat, quantitat, preu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, referencia.getNom());
            stmt.setString(2, referencia.getUom().name());
            stmt.setInt(3, referencia.getId_familia());
            stmt.setString(4, referencia.getCif_proveidor());
            stmt.setDate(5, Date.valueOf(referencia.getData_alta()));
            stmt.setFloat(6, referencia.getPes());
            stmt.setDate(7, referencia.getData_caducitat() != null ? Date.valueOf(referencia.getData_caducitat()) : null); // Añadir data_caducitat
            stmt.setInt(8, referencia.getQuantitat());
            stmt.setFloat(9, referencia.getPreu());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Referencia> obtenirEntitats() {
        List<Referencia> referencies = new ArrayList<>();
        String sql = "SELECT * FROM referencia";
        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UnitatMesura uom = UnitatMesura.valueOf(rs.getString("UOM"));  // Convertim el String a enum
                Referencia ref = new Referencia(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        uom,
                        rs.getInt("id_familia"),
                        rs.getString("cif_proveidor"),
                        rs.getDate("data_alta").toLocalDate(),
                        rs.getFloat("pes"),
                        rs.getDate("data_caducitat") != null ? rs.getDate("data_caducitat").toLocalDate() : null, // Recuperar data_caducitat
                        rs.getInt("quantitat"),
                        rs.getFloat("preu")
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
        // Añadir "data_caducitat" a la sentencia SQL
        String sql = "UPDATE referencia SET nom = ?, UOM = ?, id_familia = ?, cif_proveidor = ?, data_alta = ?, pes = ?, data_caducitat = ?, quantitat = ?, preu = ? WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, referencia.getNom());
            stmt.setString(2, referencia.getUom().name());
            stmt.setInt(3, referencia.getId_familia());
            stmt.setString(4, referencia.getCif_proveidor());
            stmt.setDate(5, Date.valueOf(referencia.getData_alta()));
            stmt.setFloat(6, referencia.getPes());
            stmt.setDate(7, referencia.getData_caducitat() != null ? Date.valueOf(referencia.getData_caducitat()) : null); // Actualizar data_caducitat
            stmt.setInt(8, referencia.getQuantitat());
            stmt.setFloat(9, referencia.getPreu());
            stmt.setInt(10, referencia.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Referencia referencia) {
        String sql = "DELETE FROM referencia WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, referencia.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Referencia> obtenirReferenciesSenseEstoc() {
        List<Referencia> referenciesSenseEstoc = new ArrayList<>();

        // SQL que també agafa informació del proveïdor associat
        String sql = "SELECT r.*, p.nom as nom_proveidor, p.cif as cif_proveidor "
                + "FROM referencia r "
                + "JOIN proveidor p ON r.cif_proveidor = p.cif "
                + "WHERE r.quantitat = 0";

        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UnitatMesura uom = UnitatMesura.valueOf(rs.getString("UOM"));

                Proveidor proveidor = new Proveidor(
                        rs.getString("cif_proveidor"),
                        rs.getString("nom_proveidor")
                );

                Referencia ref = new Referencia(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        uom,
                        rs.getInt("id_familia"),
                        rs.getString("cif_proveidor"),
                        rs.getDate("data_alta").toLocalDate(),
                        rs.getFloat("pes"),
                        rs.getDate("data_caducitat") != null ? rs.getDate("data_caducitat").toLocalDate() : null, // Recuperar data_caducitat
                        rs.getInt("quantitat"),
                        rs.getFloat("preu")
                );

                ref.setProveidor(proveidor);

                referenciesSenseEstoc.add(ref);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return referenciesSenseEstoc;
    }

}

