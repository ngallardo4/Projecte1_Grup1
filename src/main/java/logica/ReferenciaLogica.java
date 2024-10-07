/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Héctor Vico
 */
import aplicacio.model.Referencia;
import dades.DAOreferenciaImpl;
import enums.UnitatMesura;

import java.time.LocalDate;
import java.util.List;

public class ReferenciaLogica {

    private final DAOreferenciaImpl daoReferencia;

    public ReferenciaLogica() {
        // Inicializamos el DAO
        daoReferencia = new DAOreferenciaImpl();
    }

    // Añadir una nueva referencia
    public void afegirReferencia(String nom, String uomStr, int idFamilia, String cifProveidor, LocalDate dataAlta, LocalDate dataCaducitat, int quantitat_total, float preu_total) throws Exception {
        try {
            // Validar datos
            validarReferencia(nom, uomStr, idFamilia, cifProveidor, dataAlta, dataCaducitat, quantitat_total, preu_total);

            // Convertir el String a UnitatMesura (enum)
            UnitatMesura uom = UnitatMesura.valueOf(uomStr);

            // Crear la referencia
            Referencia novaReferencia = new Referencia(0, nom, uom, idFamilia, cifProveidor, dataAlta, 0.0f, dataCaducitat, quantitat_total, preu_total);

            // Llamamos al DAO para añadir la referencia
            daoReferencia.afegir(novaReferencia);
        } catch (IllegalArgumentException e) {
            throw new Exception("La unitat de mesura no és vàlida.");
        }
    }

    // Modificar una referencia existente
    public void modificarReferencia(int id, String nom, String uomStr, int idFamilia, String cifProveidor, LocalDate dataAlta, LocalDate dataCaducitat, int quantitat_total, float preu_total) throws Exception {
        try {
            // Validar datos
            validarReferencia(nom, uomStr, idFamilia, cifProveidor, dataAlta, dataCaducitat, quantitat_total, preu_total);

            // Convertir el String a UnitatMesura (enum)
            UnitatMesura uom = UnitatMesura.valueOf(uomStr);

            // Crear la referencia con los nuevos datos
            Referencia referenciaModificada = new Referencia(id, nom, uom, idFamilia, cifProveidor, dataAlta, 0.0f, dataCaducitat, quantitat_total, preu_total);

            // Llamamos al DAO para modificar la referencia
            daoReferencia.actualitzar(referenciaModificada);
        } catch (IllegalArgumentException e) {
            throw new Exception("La unitat de mesura no és vàlida.");
        }
    }

    // Eliminar una referencia existente
    public void eliminarReferencia(int id) throws Exception {
        try {
            // Creamos una referencia solo con el ID
            Referencia referenciaAEliminar = new Referencia(id, null, null, 0, null, null, 0.0f, null, 0, 0.0f);

            // Llamamos al DAO para eliminarla
            daoReferencia.eliminar(referenciaAEliminar);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la referència: " + e.getMessage());
        }
    }

    // Obtener todas las referencias
    public List<Referencia> obtenirTotesLesReferencies() {
        return daoReferencia.obtenirEntitats();
    }

    // Obtener referencias sin stock
    public List<Referencia> obtenirReferenciesSenseEstoc() {
        return daoReferencia.obtenirReferenciesSenseEstoc();
    }

    // Validar datos de referencia
    private void validarReferencia(String nom, String uomStr, int idFamilia, String cifProveidor, LocalDate dataAlta, LocalDate dataCaducitat, int quantitat_total, float preu_total) throws Exception {
        if (nom == null || nom.trim().isEmpty()) {
            throw new Exception("El nom no pot estar buit.");
        }
        if (uomStr == null || uomStr.trim().isEmpty()) {
            throw new Exception("La unitat de mesura no pot estar buida.");
        }
        if (idFamilia <= 0) {
            throw new Exception("L'ID de família ha de ser major que 0.");
        }
        if (cifProveidor == null || cifProveidor.trim().isEmpty()) {
            throw new Exception("El CIF del proveïdor no pot estar buit.");
        }
        if (dataAlta == null || dataAlta.isAfter(LocalDate.now())) {
            throw new Exception("La data d'alta no és vàlida.");
        }
        if (dataCaducitat != null && dataCaducitat.isBefore(dataAlta)) {
            throw new Exception("La data de caducitat no pot ser anterior a la data d'alta.");
        }
        if (quantitat_total < 0) {
            throw new Exception("La quantitat no pot ser negativa.");
        }
        if (preu_total < 0) {
            throw new Exception("El preu no pot ser negatiu.");
        }
    }
}
