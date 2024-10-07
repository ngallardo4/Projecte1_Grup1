/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Héctor Vico
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import aplicacio.model.Referencia;
import dades.DAOreferenciaImpl;
import enums.UnitatMesura;

public class DAOreferenciaImplTest {

    private DAOreferenciaImpl dao;

    @BeforeEach
    public void setUp() {
        dao = new DAOreferenciaImpl();
    }

    @Test
    public void testAfegirReferencia() {
        // Datos de prueba
        Referencia referencia = new Referencia(0, "Referencia DAO Test", UnitatMesura.KG, 1, "CIF123", LocalDate.now(), 50.0f, null, 100, 10.0f);

        // Añadir referencia
        dao.afegir(referencia);

        // Vuelve a obtener las referencias
        List<Referencia> referencies = dao.obtenirEntitats();

        // Verificar que la referencia ha sido añadida
        assertTrue(referencies.stream().anyMatch(r -> r.getNom().equals("Referencia DAO Test")), "La referencia debería haberse añadido.");
    }

    @Test
    public void testEliminarReferencia() {
        // Datos de prueba
        Referencia referencia = new Referencia(0, "Referencia DAO Eliminar", UnitatMesura.KG, 1, "CIF123", LocalDate.now(), 10.0f, null, 100, 50.0f);
        dao.afegir(referencia);

        // Eliminar referencia
        dao.eliminar(referencia);

        // Verificar que la referencia ha sido eliminada
        List<Referencia> referencies = dao.obtenirEntitats();
        assertFalse(referencies.stream().anyMatch(r -> r.getNom().equals("Referencia DAO Eliminar")), "La referencia debería haberse eliminado.");
    }

    @Test
public void testObtenirReferenciesSenseEstoc() {
    // Datos de prueba
    Referencia referencia = new Referencia(0, "Referencia DAO Sense Estoc", UnitatMesura.KG, 1, "CIF123", LocalDate.now(), 50.0f, null, 0, 10.0f); // `quantitat_total` es 0
    dao.afegir(referencia);

    // Verificar que la referencia sin stock aparece en la lista
    List<Referencia> referenciesSenseEstoc = dao.obtenirReferenciesSenseEstoc();
    assertTrue(referenciesSenseEstoc.stream().anyMatch(r -> r.getNom().equals("Referencia DAO Sense Estoc")), "La referencia sin stock debería estar en la lista.");
}

}
