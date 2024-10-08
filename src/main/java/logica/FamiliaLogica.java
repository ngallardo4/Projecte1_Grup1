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
 *
 * @author danie
 */
public class FamiliaLogica {

    private final DAOfamiliaImpl daoFamilia;

    // Constructor que inicialitza el DAO
    public FamiliaLogica() {
        this.daoFamilia = new DAOfamiliaImpl();
    }

    // Métode per afegir una nova família
    public void afegirFamilia(String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        // Validar les dades d'entrada abans de procedir
        validarFamilia(nom, descripcio, data_alta, prov_defecte, observacions);

        // Crear una nueva família amb les dades proporcionades
        Familia novaFamilia = new Familia(0, nom, descripcio, data_alta, prov_defecte, observacions);

        // Utilitzem el DAO per guardar la família
        daoFamilia.afegir(novaFamilia);

        System.out.println("Família afegida correctament.");
    }

    // Métode per modificar una família existent
    public void modificarFamilia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        // Validar les dades d'entrada
        validarFamilia(nom, descripcio, data_alta, prov_defecte, observacions);

        // Crear la família modificada
        Familia familiaAModificar = new Familia(id, nom, descripcio, data_alta, prov_defecte, observacions);

        // Guardar canvis
        daoFamilia.actualitzar(familiaAModificar);
        System.out.println("Família modificada correctament.");
    }

    // Métode per eliminar una família existent
    public void eliminarFamilia(int id) throws Exception {
        try {

            Familia familiaAEliminar = new Familia(id, null, null, null, null, null);
            daoFamilia.eliminar(familiaAEliminar);

        } catch (Exception e) {
            throw new Exception("Error en eliminar la família: " + e.getMessage());
        }
    }

    // Métode per obtenir totes les famílies
    public List<Familia> obtenirTotesLesFamilies() {
        return daoFamilia.obtenirEntitats();
    }

    // Métode per validar les dades de la família
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
