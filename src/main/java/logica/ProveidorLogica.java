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
 *
 * @author danie
 */
public class ProveidorLogica {
    
    private final DAOproveidorImpl daoProveidor;

    public ProveidorLogica() {
        // Inicializamos el DAO
        daoProveidor = new DAOproveidorImpl();
    }

    // Añadir un nuevo proveedor
    public void afegirProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu,
                                 String Telefon, float Descompte, LocalDate Data_Alta, int Qualificacio) throws Exception {
        // Validar datos
        validarProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);

        // Crear el proveedor
        Proveidor nouProveidor = new Proveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);

        // Llamamos al DAO para añadir el proveedor
        daoProveidor.afegir(nouProveidor);
    }

    // Modificar un proveedor existente
    public void modificarProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu,
                                 String Telefon, float Descompte, LocalDate Data_Alta, int Qualificacio) throws Exception {
        // Validar datos
        validarProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);

        // Crear el proveedor modificado
        Proveidor proveidorModificat = new Proveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);

        // Llamamos al DAO para modificar el proveedor
        daoProveidor.actualitzar(proveidorModificat);
    }

    // Eliminar un proveedor existente
    public void eliminarProveidor(String CIF) throws Exception {
        try {
            // Creamos un proveedor solo con el CIF
            Proveidor proveidorAEliminar = new Proveidor(CIF, null, null, null, null, 0.0f, null, 0);

            // Llamamos al DAO para eliminarlo
            daoProveidor.eliminar(proveidorAEliminar);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el proveidor: " + e.getMessage());
        }
    }

    // Obtener todos los proveedores
    public List<Proveidor> obtenirTotsElsProveidors() {
        return daoProveidor.obtenirEntitats();
    }

    // Validar datos del proveedor
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

