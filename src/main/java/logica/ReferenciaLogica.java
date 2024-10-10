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
        daoReferencia = new DAOreferenciaImpl();
    }

    public void afegirReferencia(String nom, UnitatMesura uom, int idFamilia, String cifProveidor,
            LocalDate dataAlta, float pesTotal, LocalDate dataCaducitat,
            int quantitatTotal, float preuTotal) throws Exception {

        validarReferencia(nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        Referencia referencia = new Referencia(0, nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        daoReferencia.afegir(referencia);
    }

    public void modificarReferencia(int id, String nom, UnitatMesura uom, int idFamilia, String cifProveidor,
            LocalDate dataAlta, float pesTotal, LocalDate dataCaducitat,
            int quantitatTotal, float preuTotal) throws Exception {

        validarReferencia(nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        Referencia referenciaModificada = new Referencia(id, nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        daoReferencia.actualitzar(referenciaModificada);
    }

    public void eliminarReferencia(int id) throws Exception {
        Referencia referenciaAEliminar = new Referencia(id, null, null, 0, null, null, 0.0f, null, 0, 0.0f);
        daoReferencia.eliminar(referenciaAEliminar);
    }

    public List<Referencia> obtenirTotesLesReferencies(int idFamilia) {
        return daoReferencia.obtenirEntitats(idFamilia);
    }

    public List<Referencia> obtenirReferenciesSenseEstoc() {
        return daoReferencia.obtenirReferenciesSenseEstoc();
    }

    private void validarReferencia(String nom, UnitatMesura uom, int idFamilia, String cifProveidor,
            LocalDate dataAlta, float pesTotal, LocalDate dataCaducitat,
            int quantitatTotal, float preuTotal) throws Exception {
        if (nom == null || nom.trim().isEmpty()) {
            throw new Exception("El nom no pot estar buit.");
        }
        if (uom == null) { //Posible ERROR
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
        if (pesTotal < 0) {
            throw new Exception("El pes no pot ser negatiu.");
        }
        if (dataCaducitat != null && dataCaducitat.isBefore(dataAlta)) {
            throw new Exception("La data de caducitat no pot ser anterior a la data d'alta.");
        }
        if (quantitatTotal < 0) {
            throw new Exception("La quantitat no pot ser negativa.");
        }
        if (preuTotal < 0) {
            throw new Exception("El preu no pot ser negatiu.");
        }
    }
}
