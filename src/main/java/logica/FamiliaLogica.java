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

    // Constructor que inicializa el DAO
    public FamiliaLogica() {
        this.daoFamilia = new DAOfamiliaImpl();  // Aseguramos que el DAO esté listo
    }

    // Método para añadir una nueva familia
    public void afegirFamilia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        // Validar los datos de entrada antes de proceder
        validarFamilia(id,nom, descripcio, data_alta, prov_defecte, observacions);

        // Crear una nueva familia con los datos proporcionados
        Familia novaFamilia = new Familia(id, nom, descripcio, data_alta, prov_defecte, observacions);

        // Utilizamos el DAO para guardar la familia en la base de datos o la lista
        daoFamilia.afegir(novaFamilia);

        System.out.println("Familia afegida correctament.");
    }

    // Método para modificar una familia existente
    public void modificarFamilia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        // Validar los datos de entrada
        validarFamilia(id,nom, descripcio, data_alta, prov_defecte, observacions);

        // Crear el proveedor modificado
        Familia familiaAModificar = new Familia(id,nom, descripcio, data_alta, prov_defecte, observacions);

        // Guardar los cambios
        daoFamilia.actualitzar(familiaAModificar);
        System.out.println("Familia modificada correctament.");
    }

    // Método para eliminar una familia existente
    public void eliminarFamilia(int id) throws Exception {
        try {
           
            Familia familiaAEliminar = new Familia(id, null, null, null, null, null);

            daoFamilia.eliminar(familiaAEliminar);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el proveidor: " + e.getMessage());
        }
    }


    // Método para obtener todas las familias
    public List<Familia> obtenirTotesLesFamilies() {
        return daoFamilia.obtenirEntitats();
    }

    // Método para validar los datos de la familia
    private void validarFamilia(int id, String nom, String descripcio, LocalDate data_alta, String prov_defecte, String observacions) throws Exception {
        if (id <= 0) {
            throw new Exception("L'ID de família ha de ser major que 0.");
        }
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
