/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 * Clase para la lógica de negocio de las referencias. Proporciona métodos para
 * agregar, modificar, eliminar y obtener referencias.
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

    /**
     * Añade una nueva referencia.
     */
    public void afegirReferencia(String nom, String uomStr, int idFamilia, String cifProveidor,
                                 LocalDate dataAlta, float pes_total, LocalDate dataCaducitat,
                                 int quantitat_total, float preu_total) throws Exception {
        try {
            // Validar datos
            validarReferencia(nom, uomStr, idFamilia, cifProveidor, dataAlta, dataCaducitat, pes_total, quantitat_total, preu_total);

            // Convertir el String a UnitatMesura (enum)
            UnitatMesura uom = UnitatMesura.valueOf(uomStr);

            // Crear la referencia
            Referencia novaReferencia = new Referencia(0, nom, uom, idFamilia, cifProveidor,
                                                       dataAlta, pes_total, dataCaducitat, 
                                                       quantitat_total, preu_total);

            // Llamamos al DAO para añadir la referencia
            daoReferencia.afegir(novaReferencia);
        } catch (IllegalArgumentException e) {
            throw new Exception("La unitat de mesura no és vàlida.");
        }
    }

    /**
     * Modifica una referencia existente.
     */
    public void modificarReferencia(int id, String nom, String uomStr, int idFamilia, String cifProveidor,
                                     LocalDate dataAlta, float pes_total, LocalDate dataCaducitat,
                                     int quantitat_total, float preu_total) throws Exception {
        try {
            // Validar datos
            validarReferencia(nom, uomStr, idFamilia, cifProveidor, dataAlta, dataCaducitat, pes_total, quantitat_total, preu_total);

            // Convertir el String a UnitatMesura (enum)
            UnitatMesura uom = UnitatMesura.valueOf(uomStr);

            // Crear la referencia con los nuevos datos
            Referencia referenciaModificada = new Referencia(id, nom, uom, idFamilia, cifProveidor,
                                                             dataAlta, pes_total, dataCaducitat, 
                                                             quantitat_total, preu_total);

            // Llamamos al DAO para modificar la referencia
            daoReferencia.actualitzar(referenciaModificada);
        } catch (IllegalArgumentException e) {
            throw new Exception("La unitat de mesura no és vàlida.");
        }
    }

    /**
     * Elimina una referencia existente.
     * 
     * @param id el ID de la referencia a eliminar.
     * @throws Exception si ocurre un error al eliminar la referencia.
     */
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

    /**
     * Obtiene todas las referencias.
     * 
     * @return una lista de {@code Referencia} que contiene todas las referencias.
     */
    public List<Referencia> obtenirTotesLesReferencies() {
        return daoReferencia.obtenirEntitats();
    }

    /**
     * Obtiene las referencias que no tienen stock.
     *
     * @return una lista de {@code Referencia} que no tienen cantidad
     * disponible.
     */
    public List<Referencia> obtenirReferenciesSenseEstoc() {
        return daoReferencia.obtenirReferenciesSenseEstoc();
    }

    /**
     * Valida los datos de una referencia.
     *
     */
    private void validarReferencia(String nom, String uomStr, int idFamilia, String cifProveidor,
            LocalDate dataAlta, LocalDate dataCaducitat, float pes_total,
            int quantitat_total, float preu_total) throws Exception {
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
        if (quantitat_total < 0) {
            throw new Exception("La quantitat no pot ser negativa.");
        }
        if (dataCaducitat != null && dataCaducitat.isBefore(dataAlta)) {
            throw new Exception("La data de caducitat no pot ser anterior a la data d'alta.");
        }
        if (pes_total < 0) {
            throw new Exception("El pes no pot ser negatiu.");
        }
        if (preu_total < 0) {
            throw new Exception("El preu no pot ser negatiu.");
        }
    }
}
