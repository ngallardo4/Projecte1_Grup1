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
// COMENTARIO
/**
 * Implementación de la interfaz DAOreferencia para manejar operaciones CRUD
 * sobre la entidad {@code Referencia}. Proporciona métodos para obtener,
 * agregar, actualizar y eliminar referencias en la base de datos.
 *
 * @autor Héctor Vico
 */
public class DAOreferenciaImpl implements DAOinterface<Referencia>, DAOreferencia<Referencia> {

    /**
     * Implementación de la interfaz DAOreferencia para manejar operaciones CRUD
     * sobre la entidad {@code Referencia}. Proporciona métodos para obtener,
     * agregar, actualizar y eliminar referencias en la base de datos.
     *
     * @autor Héctor Vico
     */
    @Override
    public List<Referencia> obtenirEntitats() {
        List<Referencia> referencies = new ArrayList<>();
        String sql = "SELECT * FROM referencia";
        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UnitatMesura uom = UnitatMesura.valueOf(rs.getString("UOM"));
                Referencia ref = new Referencia(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        uom,
                        rs.getInt("id_familia"),
                        rs.getString("cif_proveidor"),
                        rs.getDate("data_alta").toLocalDate(),
                        rs.getFloat("pes_total"),
                        rs.getDate("data_caducitat") != null ? rs.getDate("data_caducitat").toLocalDate() : null,
                        rs.getInt("quantitat_total"),
                        rs.getFloat("preu_total")
                );
                referencies.add(ref);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return referencies;
    }

    /**
     * Agrega una nueva referencia a la base de datos.
     *
     * @param referencia la {@code Referencia} que se va a agregar.
     */
    @Override
    public void afegir(Referencia referencia) {
        String sql = "INSERT INTO referencia (nom, UOM, id_familia, cif_proveidor, data_alta, pes_total, data_caducitat, quantitat_total, preu_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyDataSource.getConnection(); // Cambiar el PreparedStatement para que devuelva las claves generadas
                 PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la sentencia SQL
            stmt.setString(1, referencia.getNom());
            stmt.setString(2, referencia.getUom().name());
            stmt.setInt(3, referencia.getId_familia());
            stmt.setString(4, referencia.getCif_proveidor());
            stmt.setDate(5, Date.valueOf(referencia.getData_alta()));
            stmt.setFloat(6, referencia.getPes_total());
            stmt.setDate(7, referencia.getData_caducitat() != null ? Date.valueOf(referencia.getData_caducitat()) : null);
            stmt.setInt(8, referencia.getQuantitat_total());
            stmt.setFloat(9, referencia.getPreu_total());

            // Ejecutar la inserción
            int result = stmt.executeUpdate();
            System.out.println("Resultado de la inserción: " + result);

            // Obtener las claves generadas (el ID)
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Asignar el ID generado al objeto referencia
                    int generatedId = generatedKeys.getInt(1);
                    referencia.setId(generatedId);
                    System.out.println("ID generat: " + generatedId);  // Imprime el ID generado
                } else {
                    throw new SQLException("No se ha generado un ID para la referencia.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza una referencia existente en la base de datos.
     *
     * @param referencia la {@code Referencia} que contiene los datos
     * actualizados.
     */
    @Override
    public void actualitzar(Referencia referencia) {
        String sql = "UPDATE referencia SET nom = ?, UOM = ?, id_familia = ?, cif_proveidor = ?, data_alta = ?, pes_total = ?, data_caducitat = ?, quantitat_total = ?, preu_total = ? WHERE id = ?";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, referencia.getNom());
            stmt.setString(2, referencia.getUom().name());
            stmt.setInt(3, referencia.getId_familia());
            stmt.setString(4, referencia.getCif_proveidor());
            stmt.setDate(5, Date.valueOf(referencia.getData_alta()));
            stmt.setFloat(6, referencia.getPes_total());
            stmt.setDate(7, referencia.getData_caducitat() != null ? Date.valueOf(referencia.getData_caducitat()) : null);
            stmt.setInt(8, referencia.getQuantitat_total());
            stmt.setFloat(9, referencia.getPreu_total());
            stmt.setInt(10, referencia.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una referencia de la base de datos.
     *
     * @param referencia la {@code Referencia} que se va a eliminar.
     */
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

    /**
     * Obtiene todas las referencias que no tienen stock.
     *
     * @return una lista de {@code Referencia} que no tienen cantidad
     * disponible.
     */
    @Override
    public List<Referencia> obtenirReferenciesSenseEstoc() {
        List<Referencia> referenciesSenseEstoc = new ArrayList<>();

        String sql = "SELECT r.*, p.nom as nom_proveidor "
                + "FROM referencia r "
                + "JOIN proveidor p ON r.cif_proveidor = p.cif "
                + "WHERE r.quantitat_total = 0"; // Asegúrate de usar `quantitat_total`

        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UnitatMesura uom = UnitatMesura.valueOf(rs.getString("UOM"));

                Proveidor proveidor = new Proveidor(
                        rs.getString("nom_proveidor")
                );

                Referencia ref = new Referencia(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        uom,
                        rs.getInt("id_familia"),
                        rs.getString("cif_proveidor"),
                        rs.getDate("data_alta").toLocalDate(),
                        rs.getFloat("pes_total"),
                        rs.getDate("data_caducitat") != null ? rs.getDate("data_caducitat").toLocalDate() : null,
                        rs.getInt("quantitat_total"),
                        rs.getFloat("preu_total")
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
