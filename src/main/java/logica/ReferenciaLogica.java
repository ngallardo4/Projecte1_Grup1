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
import excepcions.IdFamiliaBuit;
import excepcions.NomBuit;
import excepcions.UomBuit;
import excepcions.cifProveidorBuit;
import excepcions.dataAltaBuit;
import excepcions.dataCaducitatBuit;
import excepcions.pesTotalBuit;
import excepcions.preuTotalBuit;
import excepcions.quantitatTotalBuit;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe que gestiona la lògica de negoci de les referències. Proporciona
 * mètodes per afegir, modificar, eliminar i obtenir referències.
 *
 * @autor Héctor Vico
 */
public class ReferenciaLogica {

    private final DAOreferenciaImpl daoReferencia;

    /**
     * Constructor de la classe {@link ReferenciaLogica}. Inicialitza el
     * DAOreferenciaImpl per gestionar les operacions de base de dades.
     */
    public ReferenciaLogica() {
        daoReferencia = new DAOreferenciaImpl();
    }

    /**
     * Afegeix una nova referència a la base de dades.
     *
     * @param nom El nom de la referència.
     * @param uom La unitat de mesura de la referència.
     * @param idFamilia L'identificador de la família a la qual pertany la
     * referència.
     * @param cifProveidor El CIF del proveïdor associat a la referència.
     * @param dataAlta La data d'alta de la referència.
     * @param pesTotal El pes total de la referència.
     * @param dataCaducitat La data de caducitat de la referència.
     * @param quantitatTotal La quantitat total disponible de la referència.
     * @param preuTotal El preu total de la referència.
     * @throws Exception Si es produeix un error en la validació o en la
     * inserció.
     */
    public void afegirReferencia(String nom, UnitatMesura uom, int idFamilia, String cifProveidor,
            LocalDate dataAlta, float pesTotal, LocalDate dataCaducitat,
            int quantitatTotal, float preuTotal) throws Exception {

        validarReferencia(nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        Referencia referencia = new Referencia(0, nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        daoReferencia.afegir(referencia);
    }

    /**
     * Modifica una referència existent a la base de dades.
     *
     * @param id L'identificador de la referència que es vol modificar.
     * @param nom El nou nom de la referència.
     * @param uom La nova unitat de mesura de la referència.
     * @param idFamilia El nou identificador de la família a la qual pertany la
     * referència.
     * @param cifProveidor El nou CIF del proveïdor associat a la referència.
     * @param dataAlta La nova data d'alta de la referència.
     * @param pesTotal El nou pes total de la referència.
     * @param dataCaducitat La nova data de caducitat de la referència.
     * @param quantitatTotal La nova quantitat total disponible de la
     * referència.
     * @param preuTotal El nou preu total de la referència.
     * @throws Exception Si es produeix un error en la validació o en
     * l'actualització.
     */
    public void modificarReferencia(int id, String nom, UnitatMesura uom, int idFamilia, String cifProveidor,
            LocalDate dataAlta, float pesTotal, LocalDate dataCaducitat,
            int quantitatTotal, float preuTotal) throws Exception {

        validarReferencia(nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        Referencia referenciaModificada = new Referencia(id, nom, uom, idFamilia, cifProveidor, dataAlta, pesTotal, dataCaducitat, quantitatTotal, preuTotal);

        daoReferencia.actualitzar(referenciaModificada);
    }

    /**
     * Elimina una referència de la base de dades.
     *
     * @param id L'identificador de la referència que es vol eliminar.
     * @throws Exception Si es produeix un error durant l'eliminació.
     */
    public void eliminarReferencia(int id) throws Exception {
        Referencia referenciaAEliminar = new Referencia(id, null, null, 0, null, null, 0.0f, null, 0, 0.0f);
        daoReferencia.eliminar(referenciaAEliminar);
    }

    /**
     * Obtén totes les referències d'una família específica.
     *
     * @param idFamilia L'identificador de la família per la qual es vol obtenir
     * referències.
     * @return Una llista d'objectes {@link Referencia} de la família
     * especificada.
     */
    public List<Referencia> obtenirTotesLesReferencies(int idFamilia) {
        return daoReferencia.obtenirEntitats(idFamilia);
    }

    /**
     * Obtén una llista de referències que no tenen estoc disponible (quantitat total = 0).
     *
     * @return Una llista d'objectes {@link Referencia} sense estoc.
     */
    public List<Referencia> obtenirReferenciesSenseEstoc() {
        return daoReferencia.obtenirReferenciesSenseEstoc();
    }

     /**
     * Valida els paràmetres d'una referència.
     *
     * @param nom El nom de la referència.
     * @param uom La unitat de mesura de la referència.
     * @param idFamilia L'identificador de la família a la qual pertany la referència.
     * @param cifProveidor El CIF del proveïdor associat a la referència.
     * @param dataAlta La data d'alta de la referència.
     * @param pesTotal El pes total de la referència.
     * @param dataCaducitat La data de caducitat de la referència.
     * @param quantitatTotal La quantitat total disponible de la referència.
     * @param preuTotal El preu total de la referència.
     * @throws Exception Si es produeix un error en la validació.
     * @throws NomBuit Si el nom està buit.
     * @throws UomBuit Si la unitat de mesura està buida.
     * @throws IdFamiliaBuit Si l'identificador de la família és menor o igual a 0.
     * @throws cifProveidorBuit Si el CIF del proveïdor està buit.
     * @throws dataAltaBuit Si la data d'alta no és vàlida.
     * @throws pesTotalBuit Si el pes total és negatiu.
     * @throws dataCaducitatBuit Si la data de caducitat és anterior a la data d'alta.
     * @throws quantitatTotalBuit Si la quantitat total és negativa.
     * @throws preuTotalBuit Si el preu total és negatiu.
     */
    private void validarReferencia(String nom, UnitatMesura uom, int idFamilia, String cifProveidor,
            LocalDate dataAlta, float pesTotal, LocalDate dataCaducitat,
            int quantitatTotal, float preuTotal) throws Exception, NomBuit, UomBuit, IdFamiliaBuit, cifProveidorBuit,
            dataAltaBuit, pesTotalBuit, dataCaducitatBuit, quantitatTotalBuit, preuTotalBuit {

        if (nom == null || nom.trim().isEmpty()) {
            throw new NomBuit("El nom no pot estar buit.");
        }
        if (uom == null) {
            throw new UomBuit("La unitat de mesura no pot estar buida.");
        }
        if (idFamilia <= 0) {
            throw new IdFamiliaBuit("L'ID de família ha de ser major que 0.");
        }
        if (cifProveidor == null || cifProveidor.trim().isEmpty()) {
            throw new cifProveidorBuit("El CIF del proveïdor no pot estar buit.");
        }
        if (dataAlta == null || dataAlta.isAfter(LocalDate.now())) {
            throw new dataAltaBuit("La data d'alta no és vàlida.");
        }
        if (pesTotal < 0) {
            throw new pesTotalBuit("El pes no pot ser negatiu.");
        }
        if (dataCaducitat != null && dataCaducitat.isBefore(dataAlta)) {
            throw new dataCaducitatBuit("La data de caducitat no pot ser anterior a la data d'alta.");
        }
        if (quantitatTotal < 0) {
            throw new quantitatTotalBuit("La quantitat no pot ser negativa.");
        }
        if (preuTotal < 0) {
            throw new preuTotalBuit("El preu no pot ser negatiu.");
        }
    }
}
