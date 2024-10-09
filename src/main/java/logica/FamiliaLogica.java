/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import aplicacio.model.Familia;
import dades.DAOfamiliaImpl;
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
     * Afegir una nova família
     *
     * @param nom
     * @param descripcio
     * @param data_alta
     * @param prov_defecte
     * @param observacions
     * @throws Exception
     */
    public void afegirFamilia(String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        // Validar les dades d'entrada abans de procedir
        validarFamilia(nom, descripcio, data_alta, prov_defecte, observacions);

        // Crear una nueva família amb les dades proporcionades
        Familia novaFamilia = new Familia(0, nom, descripcio, data_alta, prov_defecte, observacions);

        try {
            // Utilitzem el DAO per guardar la familia
            daoFamilia.afegir(novaFamilia);
            System.out.println("Família afegida correctament.");

        } catch (Exception e) {
            System.out.println("Error afegint família: " + e.getMessage());
        }
    }

    /**
     * Modifica una família existent.
     *
     * @param id
     * @param nom
     * @param descripcio
     * @param data_alta
     * @param prov_defecte
     * @param observacions
     * @throws Exception
     */
    public void modificarFamilia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        // Validar les dades d'entrada
        validarFamilia(nom, descripcio, data_alta, prov_defecte, observacions);

        // Crear la família modificada
        Familia familiaAModificar = new Familia(id, nom, descripcio, data_alta, prov_defecte, observacions);

        // Guardar canvis
        daoFamilia.actualitzar(familiaAModificar);
        System.out.println("Família modificada correctament.");
    }

    /**
     * Elimina una familia existente.
     *
     * @param id el ID de la familia a eliminar.
     * @throws Exception si hi ha un error en eliminar la família.
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
     * Obté totes las familias.
     *
     * @return una llista de {@code Familia} que conté totes les families.
     */
    public List<Familia> obtenirTotesLesFamilies() {
        return daoFamilia.obtenirEntitats();
    }

    /**
     * Métode per validar les dades de la família
     *
     * @param nom
     * @param descripcio
     * @param data_alta
     * @param prov_defecte
     * @param observacions
     * @throws Exception
     */
    private void validarFamilia(String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        if (nom == null || nom.trim().isEmpty()) {
            throw new Exception("El nom no pot estar buit.");
        }
        if (descripcio == null || descripcio.trim().isEmpty()) {
            throw new Exception("La descripció no pot estar buida.");
        }
        if (data_alta == null || data_alta.isAfter(LocalDate.now())) {
            throw new Exception("La data d'alta no és vàlida.");
        }
        if (prov_defecte == null || prov_defecte.trim().isEmpty()) {
            throw new Exception("El proveïdor per defecte no pot estar buit.");
        }
        if (observacions == null || observacions.trim().isEmpty()) {
            throw new Exception("Les observacions no poden estar buides.");
        }
    }
}
