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
 *
 * @author danie
 */
public class ProveidorLogicaTest {
    private ProveidorLogica proveidorLogica;

    @BeforeEach
    public void setUp() {
        proveidorLogica = new ProveidorLogica(); // Instancia de la lógica de proveedores
    }

    // Test para afegirProveidor()
    @Test
    public void testAfegirProveidor() throws Exception {
        // Crear una instancia de ProveidorLogica
        ProveidorLogica logica = new ProveidorLogica();

        // Añadir un proveedor
        logica.afegirProveidor("CIF123", "Proveedor Test", EstatProveidor.ACTIU, null, "123456789", 10.5f, LocalDate.now(), 5);

        // Recuperar la lista de proveedores
        List<Proveidor> proveidors = logica.obtenirTotsElsProveidors();

        // Imprimir los proveedores recuperados
        System.out.println("Proveedores recuperados después de la inserción:");
        proveidors.forEach(p -> System.out.println(p.getCIF()));
    }


    // Test para afegirProveidor() con datos inválidos
    @Test
    public void testAfegirProveidorInvalido() {
        LocalDate dataAlta = LocalDate.now();

        Exception excepcion = assertThrows(Exception.class, () -> {
            proveidorLogica.afegirProveidor("", "Proveidor 1", EstatProveidor.ACTIU, null, "123456789", 10.0f, dataAlta, 5);
        });
        assertEquals("El CIF no pot estar buit.", excepcion.getMessage(), "El error debería indicar que el CIF no puede estar vacío.");
    }

    // Test para modificarProveidor()
    public void testModificarProveidor() throws Exception {
        // Crear una instancia de ProveidorLogica
        ProveidorLogica proveidorLogica = new ProveidorLogica();

        // Añadir primero un proveedor para modificar
        LocalDate dataAlta = LocalDate.now();
        proveidorLogica.afegirProveidor("CIF456", "Proveidor Modificar", EstatProveidor.ACTIU, null, "123456789", 10.0f, dataAlta, 5);

        // Verificar que el proveedor ha sido añadido correctamente
        List<Proveidor> proveidors = proveidorLogica.obtenirTotsElsProveidors();
        Proveidor proveidor = proveidors.stream().filter(p -> p.getCIF().equals("CIF456")).findFirst().orElse(null);

        // Aquí aseguramos que el proveedor ha sido añadido antes de intentar modificarlo
        assertNotNull(proveidor, "El proveedor debería haberse añadido correctamente.");

        // Modificar el proveedor
        proveidorLogica.modificarProveidor("CIF456", "Proveidor Modificat", EstatProveidor.ACTIU, null, "987654321", 15.0f, dataAlta, 4);

        // Recuperar la lista de proveedores actualizados
        List<Proveidor> proveidorsActualitzats = proveidorLogica.obtenirTotsElsProveidors();

        // Verificar que el proveedor ha sido modificado correctamente
        Proveidor proveidorModificat = proveidorsActualitzats.stream().filter(p -> p.getCIF().equals("CIF456")).findFirst().orElse(null);
        assertNotNull(proveidorModificat, "El proveedor debería existir después de la modificación.");

        // Verificar que los campos se han actualizado
        assertEquals("Proveidor Modificat", proveidorModificat.getNom(), "El nombre del proveedor debería haber sido actualizado.");
        assertEquals("987654321", proveidorModificat.getTelefon(), "El teléfono del proveedor debería haber sido actualizado.");
        assertEquals(15.0f, proveidorModificat.getDescompte(), "El descuento del proveedor debería haber sido actualizado.");
        assertEquals(4, proveidorModificat.getQualificacio(), "La cualificación del proveedor debería haber sido actualizada.");
    }



    // Test para eliminarProveidor()
    @Test
    public void testEliminarProveidorSimple() throws Exception {
        // Asegúrate de que existe un proveedor en la base de datos con CIF_TEST antes de ejecutar el test
        proveidorLogica.eliminarProveidor("CIF_TEST");

        // Verificar que el proveedor ha sido eliminado
        List<Proveidor> proveidors = proveidorLogica.obtenirTotsElsProveidors();
        assertFalse(proveidors.stream().anyMatch(p -> p.getCIF().equals("CIF_TEST")),
                "El proveedor debería haberse eliminado.");
    }

    // Test para obtener todos los proveedores
    @Test
    public void testObtenirTotsElsProveidors() {
        List<Proveidor> proveidors = proveidorLogica.obtenirTotsElsProveidors();
        assertNotNull(proveidors, "La lista de proveedores no debería ser nula.");
    }

    // Test para la validación de datos en afegirProveidor (ejemplo con nombre vacío)
    @Test
    public void testAfegirProveidorNombreVacio() {
        LocalDate dataAlta = LocalDate.now();

        Exception excepcion = assertThrows(Exception.class, () -> {
            proveidorLogica.afegirProveidor("CIF123", "", EstatProveidor.ACTIU, null, "123456789", 10.0f, dataAlta, 5);
        });
        assertEquals("El nom no pot estar buit.", excepcion.getMessage(),
                "El error debería indicar que el nombre no puede estar vacío.");
    }

    // Test para afegirProveidor con estado inactivo y motivo vacío
    @Test
    public void testAfegirProveidorInactiuMotivoVacio() {
        LocalDate dataAlta = LocalDate.now();

        Exception excepcion = assertThrows(Exception.class, () -> {
            proveidorLogica.afegirProveidor("CIF123", "Proveidor Inactiu", EstatProveidor.INACTIU, "", "123456789", 10.0f, dataAlta, 5);
        });
        assertEquals("El motiu d'inactivitat no pot estar buit si el proveidor és inactiu.", excepcion.getMessage(),
                "El error debería indicar que el motivo de inactividad no puede estar vacío.");
    }
}
