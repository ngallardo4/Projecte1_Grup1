
/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Héctor Vico
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import aplicacio.model.Referencia;
import dades.DAOreferenciaImpl;
import dades.MyDataSource;
import enums.UnitatMesura;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOreferenciaImplTest {

    private DAOreferenciaImpl dao;
    private Referencia referencia;

    @BeforeEach
    public void setUp() {
        dao = new DAOreferenciaImpl();
        // Insertar registros necesarios para las pruebas
        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement()) {
            // Insertar un proveedor activo
            stmt.executeUpdate("INSERT INTO proveidor (cif, nom, estat, motiuInactiu, telefon, descompte, qualificacio) "
                    + "VALUES ('C123456', 'Proveedor de Prueba', 'ACTIU', NULL, '957849552', 5.0, 5);");

            // Insertar una familia de prueba
            stmt.executeUpdate("INSERT INTO familia (nom, descripcio, prov_defecte, observacions) "
                    + "VALUES ('Familia Test', 'Descripción de prueba', 'C123456', 'Observaciones de prueba');");

            // Insertar una referencia de prueba
            stmt.executeUpdate("INSERT INTO referencia (nom, UOM, id_familia, CIF_proveidor, pes_total, data_caducitat, quantitat_total, preu_total) "
                    + "VALUES ('Referencia Test', 'KG', LAST_INSERT_ID(), 'C123456', 25.0, NULL, 0, 300.00);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Limpiar la base de datos después de cada prueba
        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM referencia WHERE nom = 'Referencia Test';");
            stmt.executeUpdate("DELETE FROM familia WHERE nom = 'Familia Test';");
            stmt.executeUpdate("DELETE FROM proveidor WHERE cif = 'C123456';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObtenirEntitats() {
        // Inserim una referència de prova directament a la base de dades (a part del que ja s'ha fet al `setUp`)
        try (Connection conn = MyDataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO referencia (nom, UOM, id_familia, CIF_proveidor, pes_total, data_caducitat, quantitat_total, preu_total) "
                    + "VALUES ('Referencia Test Obtenir', 'KG', 1, 'C123456', 50.0, NULL, 0, 200.00);");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Error en inserir la referència de prova: " + e.getMessage());
        }

        // Ara cridem el mètode que estem provant
        List<Referencia> referencies = dao.obtenirEntitats();

        // Verifiquem que la referència esperada està present a la llista retornada
        Referencia referencia = referencies.stream().filter(r -> r.getNom().equals("Referencia Test Obtenir")).findFirst().orElse(null);

        assertNotNull(referencia, "La referència hauria d'existir a la base de dades.");
        assertEquals("Referencia Test Obtenir", referencia.getNom(), "El nom de la referència no coincideix.");
        assertEquals("KG", referencia.getUom().name(), "La unitat de mesura no coincideix.");
        assertEquals(50.0f, referencia.getPes_total(), 0.01, "El pes total no coincideix.");
        assertEquals(200.00f, referencia.getPreu_total(), 0.01, "El preu total no coincideix.");
    }

    @Test
    public void testInsercioSimple() {
        String sql = "INSERT INTO referencia (nom, UOM, id_familia, cif_proveidor, data_alta, pes_total, data_caducitat, quantitat_total, preu_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la sentencia SQL
            stmt.setString(1, "Referencia Simple Test");
            stmt.setString(2, "KG");
            stmt.setInt(3, 1); // Asume que la familia con ID 1 existe
            stmt.setString(4, "C123456");
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setFloat(6, 50.0f);
            stmt.setDate(7, null);
            stmt.setInt(8, 0);
            stmt.setFloat(9, 10.0f);

            // Ejecutar la inserción
            int result = stmt.executeUpdate();
            System.out.println("Resultado de la inserción: " + result);

            // Obtener las claves generadas (el ID)
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Imprimir el ID generado
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("ID generado: " + generatedId);
                    assertNotEquals(0, generatedId, "El ID debería haber sido generado.");
                } else {
                    fail("No se ha generado un ID.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Error en la inserción: " + e.getMessage());
        }
    }

    @Test
    public void testObtenirReferenciesSenseEstoc() {
        // Verificar que la referencia sin stock aparece en la lista
        List<Referencia> referenciesSenseEstoc = dao.obtenirReferenciesSenseEstoc();
        assertTrue(referenciesSenseEstoc.stream().anyMatch(r -> r.getNom().equals("Referencia Simple Test")), "La referencia sin stock debería estar en la lista.");
    }

    @Test
    public void testEliminarReferencia() {
        // Obtenir la referència per eliminar
        List<Referencia> referencies = dao.obtenirEntitats();
        referencia = referencies.stream().filter(r -> r.getNom().equals("Referencia Simple Test")).findFirst().orElse(null);

        assertNotNull(referencia, "La referencia hauria d'existir abans d'eliminar.");

        // Eliminar la referència
        dao.eliminar(referencia);

        // Comprovar que la referència ha estat eliminada
        Referencia referenciaEliminada = dao.obtenirEntitats().stream()
                .filter(r -> r.getId() == referencia.getId())
                .findFirst().orElse(null);

        assertNull(referenciaEliminada, "La referencia hauria d'haver estat eliminada.");
    }
}
