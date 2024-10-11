/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import enums.UnitatMesura;
import excepcions.IdFamiliaBuit;
import excepcions.NomBuit;
import excepcions.UomBuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import logica.ReferenciaLogica;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Héctor Vico
 */
public class ReferenciaLogicaTest {

    private ReferenciaLogica referenciaLogica;

    @BeforeEach
    void setUp() {
        referenciaLogica = new ReferenciaLogica();
    }

    @Test
    void testAfegirReferencia_ValidInput() {
        try {
            referenciaLogica.afegirReferencia(
                "Referencia 1",
                UnitatMesura.KG,
                1,
                "CIF1234",
                LocalDate.now(),
                10.0f,
                LocalDate.now().plusDays(10),
                100,
                15.0f
            );
        } catch (Exception e) {
            fail("No debería lanzar ninguna excepción: " + e.getMessage());
        }
    }

    @Test
    void testAfegirReferencia_NomBuit() {
        Exception exception = assertThrows(NomBuit.class, () -> {
            referenciaLogica.afegirReferencia("", UnitatMesura.KG, 1, "CIF1234", LocalDate.now(), 10.0f, LocalDate.now().plusDays(10), 100, 15.0f);
        });
        assertEquals("El nom no pot estar buit.", exception.getMessage());
    }

    @Test
    void testAfegirReferencia_UomBuit() {
        Exception exception = assertThrows(UomBuit.class, () -> {
            referenciaLogica.afegirReferencia("Referencia 1", null, 1, "CIF1234", LocalDate.now(), 10.0f, LocalDate.now().plusDays(10), 100, 15.0f);
        });
        assertEquals("La unitat de mesura no pot estar buida.", exception.getMessage());
    }

    @Test
    void testAfegirReferencia_IdFamiliaBuit() {
        Exception exception = assertThrows(IdFamiliaBuit.class, () -> {
            referenciaLogica.afegirReferencia("Referencia 1", UnitatMesura.KG, 0, "CIF1234", LocalDate.now(), 10.0f, LocalDate.now().plusDays(10), 100, 15.0f);
        });
        assertEquals("L'ID de família ha de ser major que 0.", exception.getMessage());
    }

    @Test
    void testModificarReferencia_ValidInput() {
        try {
            referenciaLogica.afegirReferencia(
                "Referencia 1",
                UnitatMesura.KG,
                1,
                "CIF1234",
                LocalDate.now(),
                10.0f,
                LocalDate.now().plusDays(10),
                100,
                15.0f
            );

            referenciaLogica.modificarReferencia(
                1,
                "Referencia 1 Modificada",
                UnitatMesura.L,
                1,
                "CIF5678",
                LocalDate.now(),
                20.0f,
                LocalDate.now().plusDays(20),
                200,
                30.0f
            );
        } catch (Exception e) {
            fail("No debería lanzar ninguna excepción: " + e.getMessage());
        }
    }

    @Test
    void testModificarReferencia_NomBuit() {
        Exception exception = assertThrows(NomBuit.class, () -> {
            referenciaLogica.modificarReferencia(1, "", UnitatMesura.KG, 1, "CIF1234", LocalDate.now(), 10.0f, LocalDate.now().plusDays(10), 100, 15.0f);
        });
        assertEquals("El nom no pot estar buit.", exception.getMessage());
    }

    @Test
    void testEliminarReferencia_ValidInput() {
        try {
            referenciaLogica.afegirReferencia(
                "Referencia 1",
                UnitatMesura.KG,
                1,
                "CIF1234",
                LocalDate.now(),
                10.0f,
                LocalDate.now().plusDays(10),
                100,
                15.0f
            );

            referenciaLogica.eliminarReferencia(1);
        } catch (Exception e) {
            fail("No debería lanzar ninguna excepción: " + e.getMessage());
        }
    }

    @Test
    void testObtenirTotesLesReferencies_ValidIdFamilia() {
        try {
            referenciaLogica.afegirReferencia(
                "Referencia 1",
                UnitatMesura.KG,
                1,
                "CIF1234",
                LocalDate.now(),
                10.0f,
                LocalDate.now().plusDays(10),
                100,
                15.0f
            );

            var referencias = referenciaLogica.obtenirTotesLesReferencies(1);
            assertNotNull(referencias);
            assertFalse(referencias.isEmpty());
        } catch (Exception e) {
            fail("No debería lanzar ninguna excepción: " + e.getMessage());
        }
    }

    @Test
    void testObtenirReferenciesSenseEstoc() {
        var referenciasSinEstoc = referenciaLogica.obtenirReferenciesSenseEstoc();
        assertNotNull(referenciasSinEstoc);
        // Comprobar que se devuelven las referencias correctas
    }
}


