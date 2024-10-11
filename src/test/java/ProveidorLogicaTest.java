/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import aplicacio.model.Proveidor;
import enums.EstatProveidor;
import java.time.LocalDate;
import java.util.List;
import logica.ProveidorLogica;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de test per la lògica dels proveïdors.
 *
 * @author danie
 */
public class ProveidorLogicaTest {

    private ProveidorLogica proveidorLogica;

    @BeforeEach
    public void setUp() {
        proveidorLogica = new ProveidorLogica();
    }

    /**
     * Test per afegir un proveïdor.
     *
     * @throws Exception si hi ha un error en l'afegiment del proveïdor
     */
    @Test
    public void testAfegirProveidor() throws Exception {
        ProveidorLogica logica = new ProveidorLogica();

        logica.afegirProveidor("CIF123", "Proveidor Test", EstatProveidor.ACTIU, null, "123456789", 10.5f, LocalDate.now(), 5);

        List<Proveidor> proveidors = logica.obtenirTotsElsProveidors();

        System.out.println("Proveïdors recuperats després de la inserció:");
        proveidors.forEach(p -> System.out.println(p.getCIF()));
    }

    /**
     * Test per afegir un proveïdor amb dades invàlides.
     */
    @Test
    public void testAfegirProveidorInvalido() {
        LocalDate dataAlta = LocalDate.now();

        Exception excepcio = assertThrows(Exception.class, () -> {
            proveidorLogica.afegirProveidor("", "Proveidor 1", EstatProveidor.ACTIU, null, "123456789", 10.0f, dataAlta, 5);
        });
        assertEquals("El CIF no pot estar buit.", excepcio.getMessage(), "L'error hauria d'indicar que el CIF no pot estar buit.");
    }

    /**
     * Test per modificar un proveïdor.
     *
     * @throws Exception si hi ha un error en la modificació del proveïdor
     */
    public void testModificarProveidor() throws Exception {
        ProveidorLogica proveidorLogica = new ProveidorLogica();

        LocalDate dataAlta = LocalDate.now();
        proveidorLogica.afegirProveidor("CIF456", "Proveidor Modificar", EstatProveidor.ACTIU, null, "123456789", 10.0f, dataAlta, 5);

        List<Proveidor> proveidors = proveidorLogica.obtenirTotsElsProveidors();
        Proveidor proveidor = proveidors.stream().filter(p -> p.getCIF().equals("CIF456")).findFirst().orElse(null);

        assertNotNull(proveidor, "El proveïdor hauria d'haver estat afegit correctament.");

        proveidorLogica.modificarProveidor("CIF456", "Proveidor Modificat", EstatProveidor.ACTIU, null, "987654321", 15.0f, dataAlta, 4);

        List<Proveidor> proveidorsActualitzats = proveidorLogica.obtenirTotsElsProveidors();

        Proveidor proveidorModificat = proveidorsActualitzats.stream().filter(p -> p.getCIF().equals("CIF456")).findFirst().orElse(null);
        assertNotNull(proveidorModificat, "El proveïdor hauria d'existir després de la modificació.");

        assertEquals("Proveidor Modificat", proveidorModificat.getNom(), "El nom del proveïdor hauria d'haver estat actualitzat.");
        assertEquals("987654321", proveidorModificat.getTelefon(), "El telèfon del proveïdor hauria d'haver estat actualitzat.");
        assertEquals(15.0f, proveidorModificat.getDescompte(), "El descompte del proveïdor hauria d'haver estat actualitzat.");
        assertEquals(4, proveidorModificat.getQualificacio(), "La qualificació del proveïdor hauria d'haver estat actualitzada.");
    }

    /**
     * Test per eliminar un proveïdor.
     *
     * @throws Exception si hi ha un error en l'eliminació del proveïdor
     */
    @Test
    public void testEliminarProveidorSimple() throws Exception {
        proveidorLogica.eliminarProveidor("CIF_TEST");

        List<Proveidor> proveidors = proveidorLogica.obtenirTotsElsProveidors();
        assertFalse(proveidors.stream().anyMatch(p -> p.getCIF().equals("CIF_TEST")),
                "El proveïdor hauria d'haver estat eliminat.");
    }

    /**
     * Test per obtenir tots els proveïdors.
     */
    @Test
    public void testObtenirTotsElsProveidors() {
        List<Proveidor> proveidors = proveidorLogica.obtenirTotsElsProveidors();
        assertNotNull(proveidors, "La llista de proveïdors no hauria de ser nul·la.");
    }

    /**
     * Test per la validació de dades en afegir un proveïdor (exemple amb nom
     * buit).
     */
    @Test
    public void testAfegirProveidorNombreVacio() {
        LocalDate dataAlta = LocalDate.now();

        Exception excepcio = assertThrows(Exception.class, () -> {
            proveidorLogica.afegirProveidor("CIF123", "", EstatProveidor.ACTIU, null, "123456789", 10.0f, dataAlta, 5);
        });
        assertEquals("El nom no pot estar buit.", excepcio.getMessage(),
                "L'error hauria d'indicar que el nom no pot estar buit.");
    }

    /**
     * Test per afegir un proveïdor amb estat inactiu i motiu buit.
     */
    @Test
    public void testAfegirProveidorInactiuMotivoVacio() {
        LocalDate dataAlta = LocalDate.now();

        Exception excepcio = assertThrows(Exception.class, () -> {
            proveidorLogica.afegirProveidor("CIF123", "Proveidor Inactiu", EstatProveidor.INACTIU, "", "123456789", 10.0f, dataAlta, 5);
        });
        assertEquals("El motiu d'inactivitat no pot estar buit si el proveïdor és inactiu.", excepcio.getMessage(),
                "L'error hauria d'indicar que el motiu d'inactivitat no pot estar buit.");
    }
}
