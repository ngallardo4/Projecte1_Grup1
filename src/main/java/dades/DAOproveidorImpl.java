/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Proveidor;
import enums.EstatProveidor;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * descripció: Implementació del DAO (Data Access Object) per la classe
 * Proveidor. Aquesta classe gestiona les operacions CRUD (Crear, Llegir,
 * Actualitzar, Eliminar) per als proveïdors a la base de dades.
 *
 * @author danie
 * @version 10/2024.1
 */
public class DAOproveidorImpl implements DAOinterface<Proveidor>, DAOinterfaceLlista<Proveidor> {

    /**
     * Afegeix un nou proveïdor a la base de dades.
     *
     * @param proveidor l'objecte Proveidor que es vol afegir.
     */
    @Override
    public void afegir(Proveidor proveidor) {
        String sql = "INSERT INTO proveidor (cif, nom, Estat, motiuInactiu, telefon, descompte, data_alta, qualificacio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveidor.getCIF());
            stmt.setString(2, proveidor.getNom());
            stmt.setString(3, proveidor.getEstat().name());
            stmt.setString(4, proveidor.getMotiuInactiu());
            stmt.setString(5, proveidor.getTelefon());
            stmt.setFloat(6, proveidor.getDescompte());
            stmt.setTimestamp(7, Timestamp.valueOf(proveidor.getData_Alta().atStartOfDay()));
            stmt.setInt(8, proveidor.getQualificacio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obté tots els proveïdors de la base de dades.
     *
     * @return una llista d'objectes Proveidor que representen tots els
     * proveïdors.
     */
    @Override
    public List<Proveidor> obtenirEntitats() {
        String sql = "SELECT * FROM proveidor";
        List<Proveidor> proveidors = new ArrayList<>();
        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EstatProveidor estat = EstatProveidor.valueOf(rs.getString("Estat"));
                Proveidor pro = new Proveidor(
                        rs.getString("cif"),
                        rs.getString("nom"),
                        estat,
                        rs.getString("motiuInactiu"),
                        rs.getString("telefon"),
                        rs.getFloat("descompte"),
                        rs.getDate("data_alta").toLocalDate(),
                        rs.getInt("qualificacio")
                );
                proveidors.add(pro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveidors;
    }

    /**
     * Actualitza un proveïdor existent a la base de dades.
     *
     * @param proveidor l'objecte Proveidor amb la informació actualitzada.
     */
    @Override
    public void actualitzar(Proveidor proveidor) {
        String sql = "UPDATE proveidor SET Nom = ?, Estat = ?, MotiuInactiu = ?, Telefon = ?, Descompte = ?, Data_Alta = ?, Qualificacio = ? WHERE CIF = ?";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveidor.getNom());
            stmt.setString(2, proveidor.getEstat().name());
            stmt.setString(3, proveidor.getMotiuInactiu());
            stmt.setString(4, proveidor.getTelefon());
            stmt.setFloat(5, proveidor.getDescompte());
            stmt.setDate(6, Date.valueOf(proveidor.getData_Alta()));
            stmt.setInt(7, proveidor.getQualificacio());
            stmt.setString(8, proveidor.getCIF());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un proveïdor de la base de dades.
     *
     * @param proveidor l'objecte Proveidor que es vol eliminar.
     */
    @Override
    public void eliminar(Proveidor proveidor) {
        String sql = "DELETE FROM proveidor WHERE cif = ?";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveidor.getCIF());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
