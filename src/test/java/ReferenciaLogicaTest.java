/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Héctor Vico
 */
import aplicacio.model.Referencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import logica.ReferenciaLogica;
import enums.UnitatMesura;
import logica.ProveidorLogica;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReferenciaLogicaTest {

    private ReferenciaLogica referenciaLogica;

    @BeforeEach
    public void setUp() {
        // Inicialitzem la lògica i el DAO
        referenciaLogica = new ReferenciaLogica();
    }

    @Test
    public void testAfegirProveidor() throws Exception {
        // Crear una instancia de ProveidorLogica
        ReferenciaLogica logica = new ReferenciaLogica();

        // Añadir un proveedor
        //logica.afegirReferencia("Producte Test", UnitatMesura.KG.toString(), 1, "CIF123", LocalDate.now(), LocalDate.now().plusDays(30), 10.5f, 0, 20.0f);

        // Recuperar la lista de proveedores
        List<Referencia> proveidors = logica.obtenirTotesLesReferencies();

        // Imprimir los proveedores recuperados
        System.out.println("Producte recuperat despres de la inserción:");
        proveidors.forEach(p -> System.out.println(p.getId()));
    }

    // Test para modificarReferencia()
    @Test
    public void testModificarReferencia() throws Exception {
        ReferenciaLogica referenciaLogica = new ReferenciaLogica();

        // Añadir una referencia para modificar
        LocalDate dataAlta = LocalDate.now();
        LocalDate dataCaducitat = LocalDate.now().plusDays(30);

        referenciaLogica.afegirReferencia("Producte Modificar", UnitatMesura.KG,
                1, "CIF123", dataAlta, dataCaducitat,
                10.0f, 0, 20.0f);

        // Obtener la referencia añadida
        Referencia referencia = referenciaLogica.obtenirTotesLesReferencies().stream()
                .filter(r -> r.getCif_proveidor().equals("CIF123"))
                .findFirst().orElse(null);

        assertNotNull(referencia, "La referencia debería haberse añadido correctamente.");

        // Modificar la referencia
        referenciaLogica.modificarReferencia(
                referencia.getId(), "Producte Modificat", UnitatMesura.KG,
                1, "CIF456", dataAlta, 15.0f, dataCaducitat, 4, 30.0f);

        // Verificar la referencia modificada
        Referencia referenciaModificada = referenciaLogica.obtenirTotesLesReferencies().stream()
                .filter(r -> r.getCif_proveidor().equals("CIF456"))
                .findFirst().orElse(null);

        assertNotNull(referenciaModificada, "La referencia debería existir después de la modificación.");
        assertEquals("Producte Modificat", referenciaModificada.getNom(), "El nombre debería haber sido actualizado.");
        assertEquals("CIF456", referenciaModificada.getCif_proveidor(), "El CIF debería haber sido actualizado.");
        assertEquals(15.0f, referenciaModificada.getPes_total(), "El peso total debería haber sido actualizado.");
        assertEquals(4, referenciaModificada.getQuantitat_total(), "La cantidad total debería haber sido actualizada.");
        assertEquals(30.0f, referenciaModificada.getPreu_total(), "El precio total debería haber sido actualizado.");
    }

}
