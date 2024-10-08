/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Héctor Vico
 */
import aplicacio.model.Referencia;
import logica.ReferenciaLogica;
import enums.UnitatMesura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReferenciaLogicaTest {

    private ReferenciaLogica referenciaLogica;

    @BeforeEach
    public void setUp() {
        referenciaLogica = new ReferenciaLogica(); // Instancia de la lógica de referencias
    }

    // Test para afegirReferencia()
    @Test
    public void testAfegirReferencia() throws Exception {
        LocalDate dataAlta = LocalDate.now();
        LocalDate dataCaducitat = dataAlta.plusDays(30);


        List<Referencia> referencies = referenciaLogica.obtenirTotesLesReferencies();
        assertTrue(referencies.stream().anyMatch(r -> r.getNom().equals("Nou Producte")),
                "La referencia debería haberse añadido correctamente.");
    }

    // Test para afegirReferencia() con datos inválidos
    @Test
    public void testAfegirReferenciaInvalida() {
        LocalDate dataAlta = LocalDate.now();
        LocalDate dataCaducitat = dataAlta.plusDays(30);

        Exception excepcion = assertThrows(Exception.class, () -> {
        });
        assertEquals("El nom no pot estar buit.", excepcion.getMessage(), "El error debería indicar que el nombre no puede estar vacío.");
    }

    // Test para modificarReferencia()
    @Test
    public void testModificarReferencia() throws Exception {
        // Añadir primero una referencia para modificar
        LocalDate dataAlta = LocalDate.now();
        LocalDate dataCaducitat = dataAlta.plusDays(30);


        List<Referencia> referencies = referenciaLogica.obtenirTotesLesReferencies();
        Referencia referencia = referencies.stream().filter(r -> r.getNom().equals("Producte Modificar")).findFirst().orElse(null);
        assertNotNull(referencia, "La referencia debería existir para poder ser modificada.");

        // Modificar la referencia

        List<Referencia> referenciesActualitzades = referenciaLogica.obtenirTotesLesReferencies();
        assertTrue(referenciesActualitzades.stream().anyMatch(r -> r.getNom().equals("Producte Modificat") && r.getQuantitat_total() == 200),
                "La referencia debería haberse modificado correctamente.");
    }

    // Test para eliminarReferencia()
    @Test
    public void testEliminarReferencia() throws Exception {
        // Añadir primero una referencia para eliminar
        LocalDate dataAlta = LocalDate.now();
        LocalDate dataCaducitat = dataAlta.plusDays(30);


        List<Referencia> referencies = referenciaLogica.obtenirTotesLesReferencies();
        Referencia referencia = referencies.stream().filter(r -> r.getNom().equals("Producte Eliminar")).findFirst().orElse(null);
        assertNotNull(referencia, "La referencia debería existir para poder ser eliminada.");

        // Eliminar la referencia
        referenciaLogica.eliminarReferencia(referencia.getId());

        List<Referencia> referenciesDespresEliminar = referenciaLogica.obtenirTotesLesReferencies();
        assertFalse(referenciesDespresEliminar.stream().anyMatch(r -> r.getNom().equals("Producte Eliminar")),
                "La referencia debería haberse eliminado.");
    }

    // Test para obtenirTotesLesReferencies()
    @Test
    public void testObtenirTotesLesReferencies() {
        List<Referencia> referencies = referenciaLogica.obtenirTotesLesReferencies();
        assertNotNull(referencies, "La lista de referencias no debería ser nula.");
        assertFalse(referencies.isEmpty(), "La lista de referencias no debería estar vacía.");
    }

    // Test para obtenirReferenciesSenseEstoc()
    @Test
    public void testObtenirReferenciesSenseEstoc() throws Exception {
        // Añadir una referencia con stock 0
        LocalDate dataAlta = LocalDate.now();
        LocalDate dataCaducitat = dataAlta.plusDays(30);


        List<Referencia> referenciesSenseEstoc = referenciaLogica.obtenirReferenciesSenseEstoc();
        assertTrue(referenciesSenseEstoc.stream().anyMatch(r -> r.getNom().equals("Producte Sense Estoc")),
                "La referencia sin stock debería aparecer en la lista.");
    }

    // Test para la validación de datos en afegirReferencia (ejemplo con fecha de caducidad incorrecta)
    @Test
    public void testAfegirReferenciaFechaInvalida() {
        LocalDate dataAlta = LocalDate.now();
        LocalDate dataCaducitat = dataAlta.minusDays(5); // Caducidad antes de la fecha de alta

        Exception excepcion = assertThrows(Exception.class, () -> {
        });
        assertEquals("La data de caducitat no pot ser anterior a la data d'alta.", excepcion.getMessage(),
                "El error debería indicar que la fecha de caducidad no puede ser anterior a la de alta.");
    }
}



