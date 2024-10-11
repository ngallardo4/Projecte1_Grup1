/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import aplicacio.model.Familia;
import dades.DAOfamiliaImpl;
import excepcions.NomBuit;
import excepcions.cifProveidorBuit;
import excepcions.dataAltaBuit;
import excepcions.descripcioBuit;
import excepcions.observacionsBuit;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe per la lógica de negoci de les families. Proporciona métodes per
 * afegir, modificar, eliminar i obtenir families.
 *
 * @author yaiza
 */
public class FamiliaLogica {

    private final DAOfamiliaImpl daoFamilia;

    /**
     * Constructor de la classe {@code FamiliaLogica}. Inicialitza el DAO per
     * realitzar operacions sobre families.
     */
    public FamiliaLogica() {
        this.daoFamilia = new DAOfamiliaImpl();
    }

    /**
     * Afegeix una nova família.
     *
     * @param nom El nom de la família.
     * @param descripcio La descripció de la família.
     * @param data_alta La data d'alta de la família.
     * @param prov_defecte El proveïdor per defecte.
     * @param observacions Observacions addicionals de la família.
     * @throws Exception Si es produeix un error en validar o afegir la família.
     */
    public void afegirFamilia(String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        validarFamilia(nom, descripcio, data_alta, prov_defecte, observacions);
        Familia novaFamilia = new Familia(0, nom, descripcio, data_alta, prov_defecte, observacions);

        try {
            daoFamilia.afegir(novaFamilia);
            System.out.println("Família afegida correctament.");

        } catch (Exception e) {
            System.out.println("Error afegint família: " + e.getMessage());
        }
    }

    /**
     * Modifica una família existent.
     *
     * @param id L'identificador de la família.
     * @param nom El nom actualitzat de la família.
     * @param descripcio La nova descripció de la família.
     * @param data_alta La nova data d'alta.
     * @param prov_defecte El nou proveïdor per defecte.
     * @param observacions Les noves observacions de la família.
     * @throws Exception Si es produeix un error en validar o modificar la
     * família.
     */
    public void modificarFamilia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        validarFamilia(nom, descripcio, data_alta, prov_defecte, observacions);
        Familia familiaAModificar = new Familia(id, nom, descripcio, data_alta, prov_defecte, observacions);

        daoFamilia.actualitzar(familiaAModificar);
        System.out.println("Família modificada correctament.");
    }

    /**
     * Elimina una família existent.
     *
     * @param id L'identificador de la família a eliminar.
     * @throws Exception Si hi ha un error en eliminar la família.
     */
    public void eliminarFamilia(int id) throws Exception {
        try {
            Familia familiaAEliminar = new Familia(id, null, null, null, null, null);
            daoFamilia.eliminar(familiaAEliminar);

        } catch (Exception e) {
            throw new Exception("Error en eliminar la família: " + e.getMessage());
        }
    }

    /**
     * Obté totes les famílies.
     *
     * @return Una llista de {@code Familia} que conté totes les famílies.
     */
    public List<Familia> obtenirTotesLesFamilies() {
        return daoFamilia.obtenirEntitats();
    }

    /**
     * Valida les dades d'una família abans d'afegir-la o modificar-la.
     *
     * @param nom El nom de la família.
     * @param descripcio La descripció de la família.
     * @param data_alta La data d'alta de la família.
     * @param prov_defecte El proveïdor per defecte.
     * @param observacions Observacions addicionals de la família.
     * @throws NomBuit Si el nom de la família està buit.
     * @throws descripcioBuit Si la descripció de la família està buida.
     * @throws dataAltaBuit Si la data d'alta és invàlida o està buida.
     * @throws cifProveidorBuit Si el proveïdor per defecte està buit.
     * @throws observacionsBuit Si les observacions estan buides.
     */
    private void validarFamilia(String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws NomBuit, descripcioBuit, dataAltaBuit, cifProveidorBuit, observacionsBuit {
        if (nom == null || nom.trim().isEmpty()) {
            throw new NomBuit("El nom no pot estar buit.");
        }
        if (descripcio == null || descripcio.trim().isEmpty()) {
            throw new descripcioBuit("La descripció no pot estar buida.");
        }
        if (data_alta == null || data_alta.isAfter(LocalDate.now())) {
            throw new dataAltaBuit("La data d'alta no és vàlida.");
        }
        if (prov_defecte == null || prov_defecte.trim().isEmpty()) {
            throw new cifProveidorBuit("El proveïdor per defecte no pot estar buit.");
        }
        if (observacions == null || observacions.trim().isEmpty()) {
            throw new observacionsBuit("Les observacions no poden estar buides.");
        }
    }
}
