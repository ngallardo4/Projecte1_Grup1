/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import aplicacio.model.Proveidor;
import dades.DAOproveidorImpl;
import enums.EstatProveidor;
import java.time.LocalDate;
import java.util.List;

/**
 * La clase {@code ProveidorLogica} maneja la lógica de negocio relacionada con
 * los proveedores. Proporciona métodos para añadir, modificar, eliminar y
 * obtener proveedores, así como para validar los datos del proveedor.
 *
 * @author danie
 */
public class ProveidorLogica {

    private final DAOproveidorImpl daoProveidor;

    /**
     * Crea una nueva instancia de {@code ProveidorLogica} e inicializa el DAO
     * de proveedores.
     */
    public ProveidorLogica() {
        // Inicializamos el DAO
        daoProveidor = new DAOproveidorImpl();
    }

    /**
     * Añade un nuevo proveedor al sistema.
     *
     * @throws Exception Si hay un error en la validación o al añadir el
     * proveedor.
     */
    public void afegirProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu, String Telefon, float Descompte, LocalDate Data_Alt, int Qualificacio) throws Exception {

        validarProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alt, Qualificacio);

        Proveidor proveidor = new Proveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alt, Qualificacio);

        daoProveidor.afegir(proveidor);
    }

    /**
     * Modifica un proveedor existente en el sistema.
     *
     * @throws Exception Si hay un error en la validación o al modificar el
     * proveedor.
     */
    public void modificarProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu, String Telefon, float Descompte, LocalDate Data_Alt, int Qualificacio) throws Exception {

        validarProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alt, Qualificacio);

        Proveidor proveidorModificat = new Proveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alt, Qualificacio);

        // Llamamos al DAO para modificar el proveedor
        daoProveidor.actualitzar(proveidorModificat);
    }

    /**
     * Elimina un proveedor existente del sistema.
     *
     * @param CIF El CIF del proveedor a eliminar.
     * @throws Exception Si hay un error al eliminar el proveedor.
     */
    public void eliminarProveidor(String CIF) throws Exception {
        Proveidor proveidorAEliminar = new Proveidor(CIF, null, null, null, null, 0.0f, null, 0);
        // Llamamos al DAO para eliminarlo
        daoProveidor.eliminar(proveidorAEliminar);
    }

    /**
     * Obtiene todos los proveedores registrados en el sistema.
     *
     * @return Una lista de proveedores.
     */
    public List<Proveidor> obtenirTotsElsProveidors() {
        return daoProveidor.obtenirEntitats();
    }

    /**
     * Valida los datos del proveedor.
     *
     * @throws Exception Si hay un error en la validación de los datos.
     */
    private void validarProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu,
            String Telefon, float Descompte, LocalDate Data_Alta, int Qualificacio) throws Exception {
        if (CIF == null || CIF.trim().isEmpty()) {
            throw new Exception("El CIF no pot estar buit.");
        }
        if (Nom == null || Nom.trim().isEmpty()) {
            throw new Exception("El nom no pot estar buit.");
        }
        if (Estat == null) {
            throw new Exception("L'estat no pot estar buit.");
        }
        if (Telefon == null || Telefon.trim().isEmpty()) {
            throw new Exception("El telefon no pot estar buit.");
        }
        if (Data_Alta == null || Data_Alta.isAfter(LocalDate.now())) {
            throw new Exception("La data d'alta no és vàlida.");
        }
        if (Qualificacio < 0) {
            throw new Exception("La qualificació no pot ser negativa.");
        }
        // Si el proveedor es inactivo, puede que necesites validar el motivo de inactividad
        if (Estat == EstatProveidor.INACTIU && (MotiuInactiu == null || MotiuInactiu.trim().isEmpty())) {
            throw new Exception("El motiu d'inactivitat no pot estar buit si el proveidor és inactiu.");
        }
    }
}
