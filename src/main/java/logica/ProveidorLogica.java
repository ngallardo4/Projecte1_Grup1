/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;import aplicacio.model.Proveidor;
import dades.DAOproveidorImpl;
import enums.EstatProveidor;
import excepcions.CifInvalid;
import excepcions.DescompteInvalid;
import excepcions.EstatInvalid;
import excepcions.MotiuInactiuInvalid;
import excepcions.NomBuit;
import excepcions.QualificacioInvalid;
import excepcions.TelefonInvalid;
import excepcions.dataAltaBuit;
import java.time.LocalDate;
import java.util.List;

/**
 * La classe {@code ProveidorLogica} gestiona la lògica de negoci relacionada amb
 * els proveïdors. Proporciona mètodes per afegir, modificar, eliminar i obtenir
 * proveïdors, així com per validar les dades del proveïdor.
 *
 * @author danie
 * @version 10/2024.1
 */
public class ProveidorLogica {

    private final DAOproveidorImpl daoProveidor;

    /**
     * Crea una nova instància de {@code ProveidorLogica} i inicialitza el DAO
     * de proveïdors.
     */
    public ProveidorLogica() {
        // Inicialitzem el DAO
        daoProveidor = new DAOproveidorImpl();
    }

    /**
     * Afegeix un nou proveïdor al sistema.
     *
     * @param CIF identificador únic per cada proveïdor.
     * @param Nom el nom del proveïdor.
     * @param Estat l'estat en què es troba el proveïdor (Actiu o Inactiu).
     * @param MotiuInactiu motiu d'inactivitat si el proveïdor està inactiu.
     * @param Telefon telèfon de contacte del proveïdor.
     * @param Descompte descompte aplicat al proveïdor.
     * @param Data_Alta data d'alta del proveïdor al sistema.
     * @param Qualificacio qualificació del proveïdor.
     * @throws Exception Si hi ha un error en la validació o en l'addició del proveïdor.
     */
    public void afegirProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu,
                                 String Telefon, float Descompte, LocalDate Data_Alta, int Qualificacio) throws Exception {

        validarProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, LocalDate.now(), Qualificacio);

        Proveidor proveidor = new Proveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);

        daoProveidor.afegir(proveidor);
        System.out.println("S'ha afegit correctament el proveïdor: " + Nom + " amb CIF: " + CIF);
    }

    /**
     * Modifica un proveïdor existent en el sistema.
     *
     * @param CIF identificador únic del proveïdor que es vol modificar.
     * @param Nom el nou nom del proveïdor.
     * @param Estat el nou estat del proveïdor (Actiu o Inactiu).
     * @param MotiuInactiu nou motiu d'inactivitat si el proveïdor està inactiu.
     * @param Telefon el nou telèfon de contacte del proveïdor.
     * @param Descompte el nou descompte aplicat al proveïdor.
     * @param Data_Alta la nova data d'alta del proveïdor al sistema.
     * @param Qualificacio la nova qualificació del proveïdor.
     * @throws Exception Si hi ha un error en la validació o en la modificació del proveïdor.
     */
    public void modificarProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu,
                                    String Telefon, float Descompte, LocalDate Data_Alta, int Qualificacio) throws Exception {

        validarProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);

        Proveidor proveidorModificat = new Proveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);

        // Cridem al DAO per modificar el proveïdor
        daoProveidor.actualitzar(proveidorModificat);
        System.out.println("S'ha modificat correctament el proveïdor: " + Nom + " amb CIF: " + CIF);
    }

    /**
     * Elimina un proveïdor existent del sistema.
     *
     * @param CIF El CIF del proveïdor a eliminar.
     * @throws Exception Si hi ha un error en l'eliminació del proveïdor.
     */
    public void eliminarProveidor(String CIF) throws Exception {
        Proveidor proveidorAEliminar = new Proveidor(CIF, null, null, null, null, 0.0f, null, 0);
        // Cridem al DAO per eliminar-lo
        daoProveidor.eliminar(proveidorAEliminar);
        System.out.println("S'ha eliminat correctament el proveïdor amb CIF: " + CIF);
    }

    /**
     * Obtén tots els proveïdors registrats en el sistema.
     *
     * @return Una llista de proveïdors.
     */
    public List<Proveidor> obtenirTotsElsProveidors() {
        List<Proveidor> proveidors = daoProveidor.obtenirEntitats();
        System.out.println("S'han obtingut " + proveidors.size() + " proveïdors del sistema.");
        return proveidors;
    }

    /**
     * Valida les dades del proveïdor.
     *
     * @param CIF identificador únic per cada proveïdor.
     * @param Nom el nom del proveïdor.
     * @param Estat l'estat en què es troba el proveïdor.
     * @param MotiuInactiu motiu d'inactivitat si el proveïdor està inactiu.
     * @param Telefon telèfon de contacte del proveïdor.
     * @param Descompte descompte aplicat al proveïdor.
     * @param Data_Alta data d'alta del proveïdor al sistema.
     * @param Qualificacio qualificació del proveïdor.
     * @throws CifInvalid si el CIF és nul o buit.
     * @throws NomBuit si el nom és nul o buit.
     * @throws EstatInvalid si l'estat és nul.
     * @throws MotiuInactiuInvalid si el motiu d'inactivitat és nul o buit quan l'estat és inactiu.
     * @throws TelefonInvalid si el telèfon és nul o buit.
     * @throws DescompteInvalid si el descompte és negatiu.
     * @throws dataAltaBuit si la data d'alta és nul·la o no és vàlida.
     * @throws QualificacioInvalid si la qualificació és negativa.
     * @throws Exception si hi ha un error en la validació de les dades.
     */
    private void validarProveidor(String CIF, String Nom, EstatProveidor Estat, String MotiuInactiu,
                                   String Telefon, float Descompte, LocalDate Data_Alta, int Qualificacio) throws CifInvalid,
            NomBuit, EstatInvalid, MotiuInactiuInvalid, TelefonInvalid, DescompteInvalid, dataAltaBuit,
            QualificacioInvalid, Exception {

        if (CIF == null || CIF.trim().isEmpty()) {
            throw new CifInvalid("El CIF no pot estar buit.");
        }
        if (Nom == null || Nom.trim().isEmpty()) {
            throw new NomBuit("El nom no pot estar buit.");
        }
        if (Estat == null) {
            throw new EstatInvalid("L'estat no pot estar buit.");
        }
        if (Telefon == null || Telefon.trim().isEmpty()) {
            throw new TelefonInvalid("El telèfon no pot estar buit.");
        }
        if (Descompte < 0) {
            throw new DescompteInvalid("El descompte no pot ser negatiu.");
        }
        if (Data_Alta == null || Data_Alta.isAfter(LocalDate.now())) {
            throw new dataAltaBuit("La data d'alta no és vàlida.");
        }
        if (Qualificacio < 0) {
            throw new QualificacioInvalid("La qualificació no pot ser negativa.");
        }
        if (Estat == EstatProveidor.INACTIU && (MotiuInactiu == null || MotiuInactiu.trim().isEmpty())) {
            throw new MotiuInactiuInvalid("El motiu d'inactivitat no pot estar buit si el proveïdor és inactiu.");
        }
    }
}
