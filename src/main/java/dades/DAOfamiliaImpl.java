/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Familia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementació del DAO (Data Access Object) per a l'entitat {@code Familia}.
 * Proporciona métodes per a realitzar operacions CRUD (Crear, Llegir,
 * Actualitzar, Eliminar) sobre la taula 'familia' a la base de dades.
 *
 * @author Yaiza
 */
public class DAOfamiliaImpl implements DAOinterface<Familia> {

    /**
     * Afegeix una nova família a la base de dades.
     *
     * @param familia la familia a afegir.
     */
    @Override
    public void afegir(Familia familia) {
        String insertSQL = "INSERT INTO familia (nom, descripcio, data_alta, prov_defecte, observacions) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = MyDataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                stmt.setString(1, familia.getNom());
                stmt.setString(2, familia.getDescripcio());
                stmt.setDate(3, Date.valueOf(familia.getData_alta()));
                stmt.setString(4, familia.getProv_defecte());
                stmt.setString(5, familia.getObservacions());
                stmt.executeUpdate();

                System.out.println("Executant INSERT INTO familia...");

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        familia.setId(generatedId);
                        System.out.println("ID generat: " + generatedId);
                    } else {
                        throw new SQLException("No s'ha pogut obtenir l'ID generat.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Obté totes les families de la base de dades.
     *
     * @return una llista d'objectes {@code Familia} que representen totes les
     * families.
     */
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

    /**
     * Actualitza les dades d'una família existent a la base de dades.
     *
     * @param familia la família amb les noves dades.
     */
    @Override
    public void actualitzar(Familia familia) {
        String updateSQL = "UPDATE familia SET nom = ?, descripcio = ?, prov_defecte = ?, observacions = ? WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, familia.getNom());
            stmt.setString(2, familia.getDescripcio());
            stmt.setString(3, familia.getProv_defecte());
            stmt.setString(4, familia.getObservacions());
            stmt.setInt(5, familia.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Familia modificada correctamente en la base de datos.");
            } else {
                System.out.println("No se encontró ninguna familia con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la familia en la base de datos.");
        }
    }

    /**
     * Elimina una família de la base de dades.
     *
     * @param familia la família a eliminar.
     */
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
