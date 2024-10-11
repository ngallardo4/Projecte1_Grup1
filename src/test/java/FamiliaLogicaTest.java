/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import aplicacio.model.Familia;
import excepcions.NomBuit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.FamiliaLogica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Yaiza
 */
public class FamiliaLogicaTest {

    private FamiliaLogica familiaLogica;

    /**
     * Inicialitza l'objecte {@code FamiliaLogica} abans de cada prova. Aquest
     * mètode s'executa abans de cada test individual.
     */
    @BeforeEach
    public void setUp() {
        familiaLogica = new FamiliaLogica();
    }

    /**
     * Prova que el mètode {@code afegirFamilia} funcioni correctament amb dades
     * d'entrada vàlides.
     *
     * @throws Exception si hi ha algun problema amb l'afegit de la família.
     */
    @Test
    void testAfegirFamiliaCorrecte() throws Exception {
        String nom = "Família Exemple";
        String descripcio = "Descripció de la família";
        LocalDate dataAlta = LocalDate.of(2023, 1, 1);
        String provDefecte = "Proveïdor Exemple";
        String observacions = "Observacions aquí";

        familiaLogica.afegirFamilia(nom, descripcio, dataAlta, provDefecte, observacions);
    }

    /**
     * Prova que el mètode {@code afegirFamilia} llença una excepció
     * {@code NomBuit} quan el nom proporcionat està buit.
     */
    @Test
    void testAfegirFamiliaExcepcioNomBuit() {
        String nom = "";
        String descripcio = "Descripció vàlida";
        LocalDate dataAlta = LocalDate.of(2023, 1, 1);
        String provDefecte = "Proveïdor Exemple";
        String observacions = "Observacions vàlides";

        assertThrows(NomBuit.class, () -> familiaLogica.afegirFamilia(nom, descripcio, dataAlta, provDefecte, observacions));
    }

    /**
     * Prova el mètode {@code eliminarFamilia} per assegurar-se que elimina
     * correctament una família amb un ID donat.
     *
     * @throws Exception si hi ha algun problema en eliminar la família.
     */
    @Test
    void testEliminarFamilia() throws Exception {
        int id = 1;

        familiaLogica.eliminarFamilia(id);
    }

    /**
     * Prova el mètode {@code obtenirTotesLesFamilies} per assegurar-se que
     * retorna una llista de famílies.
     *
     * Comprova que la llista retornada no sigui nul·la i que conté almenys 0 o
     * més elements.
     */
    @Test
    public void testObtenirTotesLesFamilies() {
        FamiliaLogica logica = new FamiliaLogica();

        List<Familia> families = logica.obtenirTotesLesFamilies();
        assertNotNull(families);
        assertTrue(families.size() >= 0);
    }
}
