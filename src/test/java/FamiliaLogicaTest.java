/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import dades.MyDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import logica.FamiliaLogica;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Yaiza
 */
public class FamiliaLogicaTest {

    private FamiliaLogica familiaLogica;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        // Configura la connexió a la base de dades de prova
        connection = MyDataSource.getConnection();

        // Inicia la classe FamiliaLogica que farà servir DAOfamiliaImpl real
        familiaLogica = new FamiliaLogica();

        // Eliminar les referències primer
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM referencia WHERE id_familia = 1");
            statement.executeUpdate("DELETE FROM familia WHERE id = 1");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Eliminar referències primer
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM referencia WHERE id_familia = 1");
            statement.executeUpdate("DELETE FROM familia WHERE id = 1");
        }
        connection.close();
    }

    @Test
    public void testAfegirFamilia() throws Exception {
        // Dades de prova
        String nom = "Prova 1";
        String descripcio = "Productes prova1";
        LocalDate data_alta = LocalDate.now();
        String prov_defecte = "PROVEIDOR prova1";
        String observacions = "Res important1";

        // Executa el mètode a provar (no retorna cap valor)
        familiaLogica.afegirFamilia(nom, descripcio, data_alta, prov_defecte, observacions);

        // Comprova que la família s'ha afegit correctament fent una cerca pel nom
        try (Statement statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("SELECT * FROM familia WHERE nom = '" + nom + "'");
            assertTrue(resultSet.next(), "La família amb nom '" + nom + "' hauria d'haver estat afegida a la base de dades.");

            // Si vols, també pots verificar els altres camps:
            assertEquals(descripcio, resultSet.getString("descripcio"));
            assertEquals(Date.valueOf(data_alta), resultSet.getDate("data_alta"));
            assertEquals(prov_defecte, resultSet.getString("prov_defecte"));
            assertEquals(observacions, resultSet.getString("observacions"));

        } catch (Exception e) {
            fail("No hauria d'haver fallat la verificació de la família a la base de dades.");
        }
    }
}
