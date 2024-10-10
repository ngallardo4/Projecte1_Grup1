/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Proveidor;
import enums.EstatProveidor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO (Data Access Object) para la entidad Proveidor. Esta
 * clase gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para
 * los proveedores en la base de datos.
 *
 * @author danie
 */
public class DAOproveidorImpl implements DAOinterface<Proveidor> {

    /**
     * Añade un nuevo proveedor a la base de datos.
     *
     * @param proveidor El objeto Proveidor que se desea añadir.
     */
    @Override
    public void afegir(Proveidor proveidor) {
        String sql = "INSERT INTO proveidor (cif, nom, Estat, motiuInactiu, telefon, descompte, data_alta, qualificacio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyDataSource.getConnection(); // Cambiar el PreparedStatement para que devuelva las claves generadas
                 PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la sentencia SQL
            stmt.setString(1, proveidor.getCIF());
            stmt.setString(2, proveidor.getNom());
            stmt.setString(3, proveidor.getEstat().name());
            stmt.setString(4, proveidor.getMotiuInactiu());
            stmt.setString(5, proveidor.getTelefon());
            stmt.setFloat(6, proveidor.getDescompte());
            stmt.setTimestamp(7, Timestamp.valueOf(proveidor.getData_Alta().atStartOfDay()));
            stmt.setInt(8, proveidor.getQualificacio());

            // Ejecutar la inserción
            int result = stmt.executeUpdate();
            System.out.println("Resultado de la inserción: " + result);

            // Obtener las claves generadas (el CIF)
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Asignar el CIF generado al objeto proveidor
                    String generatedCIF = generatedKeys.getString(1); // Cambiamos a String
                    proveidor.setCIF(generatedCIF); // Suponiendo que hay un método setCIF en la clase Proveidor
                    System.out.println("CIF generat: " + generatedCIF);  // Imprime el CIF generado
                } else {
                    throw new SQLException("No se ha generado un CIF para el proveidor.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todos los proveedores de la base de datos.
     *
     * @return Una lista de objetos Proveidor que representan todos los
     * proveedores.
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

    @Override
    public void actualitzar(Proveidor proveidor) {
        String sql = "UPDATE proveidor SET CIF= ?, Nom = ?, Estat = ?, MotiuInactiu = ?, Telefon = ?, Descompte = ?, Data_Alta = ?, Qualificacio = ? WHERE cif = ?";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveidor.getCIF());
            stmt.setString(2, proveidor.getNom());
            stmt.setString(3, proveidor.getEstat().name());
            stmt.setString(4, proveidor.getMotiuInactiu());
            stmt.setString(5, proveidor.getTelefon());
            stmt.setFloat(6, proveidor.getDescompte());
            stmt.setDate(7, Date.valueOf(proveidor.getData_Alta()));
            stmt.setInt(8, proveidor.getQualificacio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un proveedor de la base de datos.
     *
     * @param proveidor El objeto Proveidor que se desea eliminar.
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
