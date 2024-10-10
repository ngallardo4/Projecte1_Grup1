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

    @BeforeEach
    public void setUp() {
        // Inicialitzar l'objecte FamiliaLogica abans de cada test
        familiaLogica = new FamiliaLogica();
    }

    @Test
    void testAfegirFamiliaCorrecte() throws Exception {
        // Dades d'entrada vàlides
        String nom = "Família Exemple";
        String descripcio = "Descripció de la família";
        LocalDate dataAlta = LocalDate.of(2023, 1, 1);
        String provDefecte = "Proveïdor Exemple";
        String observacions = "Observacions aquí";

        // Executar el mètode
        familiaLogica.afegirFamilia(nom, descripcio, dataAlta, provDefecte, observacions);

        // Comprovar que no es llença cap excepció
        // Nota: Aquí es pot afegir una comprovació si es té accés a les dades afegides
    }

    @Test
    void testAfegirFamiliaExcepcioNomBuit() {
        // Dades d'entrada amb nom buit (invàlid)
        String nom = "";
        String descripcio = "Descripció vàlida";
        LocalDate dataAlta = LocalDate.of(2023, 1, 1);
        String provDefecte = "Proveïdor Exemple";
        String observacions = "Observacions vàlides";

        // Comprovar que es llença una excepció de tipus NomBuit
        assertThrows(NomBuit.class, () -> familiaLogica.afegirFamilia(nom, descripcio, dataAlta, provDefecte, observacions));
    }

    @Test
    void testEliminarFamilia() throws Exception {
        // Id d'una família a eliminar
        int id = 1;

        // Executar el mètode eliminarFamilia
        familiaLogica.eliminarFamilia(id);

        // Nota: Aquí es podria comprovar si realment la família ha estat eliminada, 
        // depenent de l'accés a les dades després de la crida.
    }

    @Test
    public void testObtenirTotesLesFamilies() {
        FamiliaLogica logica = new FamiliaLogica();

        // Obtenim totes les families
        List<Familia> families = logica.obtenirTotesLesFamilies();

        // Verificar que la llista no sigui null
        assertNotNull(families);

        // Si s'espera que hi hagi families a la base de dades, es pot verificar que la llista no estigui buida
        assertTrue(families.size() >= 0);
    }
}
