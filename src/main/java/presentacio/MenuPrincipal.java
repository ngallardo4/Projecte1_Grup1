/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import java.util.Scanner;
import logica.ProveidorLogica;
import logica.ReferenciaLogica;
import logica.FamiliaLogica;
import aplicacio.model.Proveidor;
import aplicacio.model.Familia;
import aplicacio.model.Referencia;
import enums.EstatProveidor;
import enums.UnitatMesura;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase que representa el menú principal de la aplicación de gestión de proveedores y familias.
 * Esta clase permite al usuario interactuar con las funcionalidades de agregar, eliminar y consultar 
 * proveedores y familias a través de un menú de texto.
 */

/**
 *
 * @author danie
 */
public class MenuPrincipal {

    Scanner sc = new Scanner(System.in);
    ProveidorLogica proveidor = new ProveidorLogica();
    FamiliaLogica familia = new FamiliaLogica();
    ReferenciaLogica referencia = new ReferenciaLogica();
    
    /**
     * Método que muestra el menú principal y permite al usuario seleccionar una opción.
     * El menú presenta opciones para listar proveedores, familias y referencias.
     * El ciclo se ejecuta indefinidamente hasta que el usuario decide salir.
     */
    
    public void mostrarMenu(){
        
        while(true){
            
            System.out.println("=== Menú Principal ===");
            System.out.println("1.- Llistar Proveidor.");
            System.out.println("2.- Llistar Familia.");
            System.out.println("3.- Llistar Referencia.");
            System.out.println("0.- Sortir");

            System.out.println("Selecciona un opcio: ");
            int opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {
                case 1:
                    llistarProveidors();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Sortir.");
                    return;
                default:
                    System.out.println("Opcio Invalida.");
            }
        }
    }
    
    /**
     * Método que muestra un menú para listar y gestionar proveedores.
     * El usuario puede agregar, eliminar, modificar y consultar proveedores.
     */
    
    public void llistarProveidors() {

        while (true) {

            System.out.println("=== Menú Principal ===");
            System.out.println("1.- Alta de proveidor.");
            System.out.println("2.- Baixa de proveidor.");
            System.out.println("3.- Modificar proveidor.");
            System.out.println("4.- Consultar proveidor.");
            System.out.println("0.- Sortir.");

            System.out.println("Selecciona una opcio: ");
            int opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {
                case 1:
                    altaProveidor();
                    break;
                case 2:
                    baixaProveidor();
                    break;
                case 3:
                    modificarProveidor();
                    break;
                case 4:
                    consultarProveidor();
                    break;
                case 0:
                    System.out.println("Sortir.");
                    return;
                default:
                    System.out.println("Opcio invalida.");
            }
        }
    }

    
    /**
     * Método para agregar un nuevo proveedor.
     * Solicita al usuario información relevante y llama a la lógica correspondiente para añadir el proveedor.
     */
    
    private void altaProveidor() {

        try {
            System.out.print("Introdueix el CIF: ");
            String CIF = sc.nextLine();

            System.out.print("Introdueix el nom: ");
            String Nom = sc.nextLine();

            EstatProveidor Estat = seleccionarEstat();

            System.out.print("Introduce el motiu inactiu: ");
            String MotiuInactiu = sc.nextLine();

            System.out.print("Introdueix el telefon: ");
            String Telefon = sc.nextLine();

            System.out.print("Introdueix el descompte: ");
            float Descompte = sc.nextFloat();
            sc.nextLine();  // Consumir nueva línea

            System.out.print("Introdueix la fecha d'alta (YYYY-MM-DD): ");
            LocalDate Data_Alta = LocalDate.parse(sc.nextLine());

            System.out.print("Introdueix la qualificacio: ");
            int Qualificacio = sc.nextInt();
            sc.nextLine();  // Consumir nueva línea

            // Llamamos al método de la lógica
            proveidor.afegirProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);
            System.out.println("Proveidor afegit exitosament.");
        } catch (Exception e) {
            System.out.println("Error al afegir el proveidor: " + e.getMessage());
        }
    }
    
    /**
     * Método para dar de baja a un proveedor existente.
     * Solicita al usuario el CIF del proveedor y confirma la acción antes de proceder a eliminarlo.
     */

    private void baixaProveidor() {

        try {
            System.out.print("CIF del Proveidor a donar de baixa: ");
            String CIF = sc.nextLine();

            // Confirmar la baja del proveedor
            System.out.print("Estàs segur que vols donar de baixa el proveidor? (S/N): ");
            String confirmacio = sc.nextLine();

            if (confirmacio.equalsIgnoreCase("S")) {
                proveidor.eliminarProveidor(CIF);
                System.out.println("Proveidor donat de baixa correctament.");
            } else {
                System.out.println("Operació cancel·lada. El proveidor no ha estat donat de baixa.");
            }
        } catch (Exception e) {
            System.out.println("Error al donar de baixa el proveidor: " + e.getMessage());
        }
    }

    
    /**
     * Método para modificar un proveedor existente.
     * Solicita al usuario el CIF del proveedor y la nueva información para realizar la modificación.
     */
    
    private void modificarProveidor() {

        try {
            System.out.print("CIF del Proveidor a modificar: ");
            String CIF = sc.nextLine();

            System.out.print("Nou nom del proveidor: ");
            String Nom = sc.nextLine();

            EstatProveidor Estat = seleccionarEstat();

            System.out.print("Introduce el motiu inactiu (o deja vacío si no aplica): ");
            String MotiuInactiu = sc.nextLine();

            System.out.print("Nou telefon del proveidor: ");
            String Telefon = sc.nextLine();

            System.out.print("Nou descompte: ");
            float Descompte = sc.nextFloat();
            sc.nextLine();  // Consumir nueva línea

            System.out.print("Nova data d'alta (YYYY-MM-DD): ");
            LocalDate Data_Alta = LocalDate.parse(sc.nextLine());

            System.out.print("Nova qualificació del proveidor: ");
            int Qualificacio = sc.nextInt();
            sc.nextLine();  

            proveidor.modificarProveidor(CIF, Nom, Estat, MotiuInactiu, Telefon, Descompte, Data_Alta, Qualificacio);
            System.out.println("Proveidor modificat correctament.");
        } catch (Exception e) {
            System.out.println("Error al modificar el proveidor: " + e.getMessage());
        }
    }

    /**
     * Método para consultar un proveedor existente.
     * Solicita al usuario el CIF del proveedor y muestra sus detalles si se encuentra.
     */
    
    private void consultarProveidor(){
        
        System.out.print("CIF del Proveidor a consultar: ");
        String CIF = sc.nextLine();

        List<Proveidor> proveidors = proveidor.obtenirTotsElsProveidors();

        Proveidor proveidor = null;
        for (Proveidor p : proveidors) {
            if (p.getCIF().equals(CIF)) {
                proveidor = p;
                break;
            }
        }

        if (proveidor == null) {
            System.out.println("Proveidor no trobat.");
        } else {
            // Mostrar detalles del proveedor
            System.out.println("=== Detalls del Proveidor ===");
            System.out.println("CIF: " + proveidor.getCIF());
            System.out.println("Nom: " + proveidor.getNom());
            System.out.println("Estat: " + proveidor.getEstat());
            System.out.println("Motiu Inactiu: " + proveidor.getMotiuInactiu());
            System.out.println("Telèfon: " + proveidor.getTelefon());
            System.out.println("Descompte: " + proveidor.getDescompte());
            System.out.println("Data d'alta: " + proveidor.getData_Alt());
            System.out.println("Qualificació: " + proveidor.getQualificacio());
        }
    }
    
    /**
     * Método para seleccionar el estado de un proveedor.
     * Permite al usuario elegir entre los estados disponibles: ACTIU o INACTIU.
     * 
     * @return Estado del proveedor seleccionado.
     */
    
    private EstatProveidor seleccionarEstat() {

        while (true) {

            System.out.println("Estat del proveidor [ACTIU][INACTIU]: ");
            String Estat = sc.nextLine();

            if (Estat.equalsIgnoreCase("ACTIU")) {
                return EstatProveidor.ACTIU;
            } else if (Estat.equalsIgnoreCase("INACTIU")) {
                return EstatProveidor.INACTIU;
            } else {
                System.out.println("Opción incorrecta. Por favor, selecciona una opció vàlida.");
            }
        }
    }
    
    /**
     * Método que muestra un menú para listar y gestionar familias.
     * El usuario puede agregar, eliminar, modificar y consultar familias.
     */
    
    private void llistarFamilia(){
        
        while(true){
            System.out.println("=== Menú Principal ===");
            System.out.println("1.- Alta de familia.");
            System.out.println("2.- Baixa de familia.");
            System.out.println("3.- Modificar familia.");
            System.out.println("4.- Consultar familia.");
            System.out.println("0.- Sortir.");

            System.out.println("Selecciona una opcio: ");
            int opcio = sc.nextInt();
            sc.nextLine();
            
            switch (opcio) {
                case 1:
                    altaFamilia();
                    break;
                case 2:
                    baixaFamilia();
                    break;
                case 3:
                    modificarFamilia();
                    break;
                case 4:
                    consultarFamilia();
                    break;
                case 0:
                    System.out.println("Sortir.");
                    return;
                default:
                    System.out.println("Opcio invalida.");
            }
        }
    }
    
    /**
     * Método para agregar una nueva familia.
     * Solicita al usuario la información relevante y llama a la lógica correspondiente para añadir la familia.
     */
    
    private void altaFamilia() {
        try {
            System.out.println("Introdueix el id de la família: ");
            int id = sc.nextInt(); 
            
            System.out.print("Introdueix el nom de la família: ");
            String nom = sc.nextLine();

            System.out.print("Introdueix una descripció: ");
            String descripcio = sc.nextLine();

            System.out.print("Introdueix la data d'alta (YYYY-MM-DD): ");
            LocalDate data_alta = LocalDate.parse(sc.nextLine());

            System.out.print("Introdueix el proveïdor per defecte: ");
            String prov_defecte = sc.nextLine();

            System.out.print("Introdueix observacions: ");
            String observacions = sc.nextLine();

            familia.afegirFamilia(nom, descripcio, data_alta, prov_defecte, observacions);

            System.out.println("Família afegida correctament.");
        } catch (Exception e) {
            System.out.println("Error al afegir la família: " + e.getMessage());
        }
    }
    
    /**
     * Método para dar de baja a una familia existente.
     * Solicita al usuario el ID de la familia y confirma la acción antes de proceder a eliminarla.
     */
    
    private void baixaFamilia() {
        try {
            System.out.print("Id de Familia a donar de baixa: ");
            int id = sc.nextInt();

            // Confirmar la baja del proveedor
            System.out.print("Estàs segur que vols donar de baixa la familia? (S/N): ");
            String confirmacio = sc.nextLine();

            if (confirmacio.equalsIgnoreCase("S")) {
                familia.eliminarFamilia(id);
                System.out.println("Familia donat de baixa correctament.");
            } else {
                System.out.println("Operació cancel·lada. La familia no ha estat donat de baixa.");
            }
        } catch (Exception e) {
            System.out.println("Error al donar de baixa la familia: " + e.getMessage());
        }
    }

    /**
     * Método para modificar una familia existente.
     * Solicita al usuario el ID de la familia y la nueva información para realizar la modificación.
     */
    
    private void modificarFamilia(){
        try {
            System.out.print("Id de la Familia a modificar: ");
            int id = sc.nextInt();

            System.out.print("Nou nom de la familia: ");
            String Nom = sc.nextLine();

            System.out.print("Nova descripcio de la familia: ");
            String descripcio = sc.nextLine();

            System.out.print("Nova data d'alta (YYYY-MM-DD): ");
            LocalDate Data_Alta = LocalDate.parse(sc.nextLine());

            System.out.print("Nova prova de defecte de la familia: ");
            String prov_defecte = sc.nextLine();

            System.out.print("Nova Observacions de la familia: ");
            String observacions = sc.nextLine();
            
            familia.modificarFamilia(id, Nom, descripcio, Data_Alta, prov_defecte, observacions);
            System.out.println("Proveidor modificat correctament.");
            
        } catch (Exception e) {
            System.out.println("Error al modificar el proveidor: " + e.getMessage());
        }
    }
    
    /**
     * Método para consultar una familia existente.
     * Solicita al usuario el ID de la familia y muestra sus detalles si se encuentra.
     */
    
    private void consultarFamilia(){
        System.out.print("ID de la Família a consultar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea que queda tras nextInt()

        // Obtener todas las familias
        List<Familia> families = familia.obtenirTotesLesFamilies();

        // Buscar la familia con el ID proporcionado
        Familia familia = null;
        for (Familia f : families) {
            if (f.getId() == id) {
                familia = f;
                break;
            }
        }

        if (familia == null) {
            System.out.println("Família no trobada.");
        } else {
            // Mostrar los detalles de la familia
            System.out.println("=== Detalls de la Família ===");
            System.out.println("ID: " + familia.getId());
            System.out.println("Nom: " + familia.getNom());
            System.out.println("Descripció: " + familia.getDescripcio());
            System.out.println("Data d'alta: " + familia.getData_alta());
            System.out.println("Proveïdor per defecte: " + familia.getProv_defecte());
            System.out.println("Observacions: " + familia.getObservacions());
        }
    }
    
    /**
     * Método que muestra un menú para listar y gestionar referencias.
     * El usuario puede agregar, eliminar, modificar y consultar referencias.
     */
    
    private void llistarReferencia(){
        
        while(true){
            System.out.println("=== Menú Principal ===");
            System.out.println("1.- Alta de referencia.");
            System.out.println("2.- Baixa de referencia.");
            System.out.println("3.- Modificar referencia.");
            System.out.println("4.- Consultar referencia.");
            System.out.println("0.- Sortir.");

            System.out.println("Selecciona una opcio: ");
            int opcio = sc.nextInt();
            sc.nextLine();
            
            switch (opcio) {
                case 1:
                    altaReferencia();
                    break;
                case 2:
                    baixaReferencia();
                    break;
                case 3:
                    modificarReferencia();
                    break;
                case 4:
                    consultarFamilia();
                    break;
                case 0:
                    System.out.println("Sortir.");
                    return;
                default:
                    System.out.println("Opcio invalida.");
            }
        }
    }
    
    /**
     * Método para agregar una nueva referencia.
     * Solicita al usuario información relevante y llama a la lógica correspondiente para añadir la referencia.
     */
    
    private void altaReferencia() {
        try {           
            System.out.print("Introdueix el nom de la referencia: ");
            String nom = sc.nextLine();

            UnitatMesura uomStr = seleccionarUOM();
            
            System.out.print("Introdueix l'ID de la família: ");
            int idFamilia = sc.nextInt();

            System.out.print("Introdueix el CIF del proveïdor: ");
            String cifProveidor = sc.nextLine();

            System.out.print("Introdueix la data d'alta (YYYY-MM-DD): ");
            LocalDate dataAlta = LocalDate.parse(sc.nextLine());

            System.out.println("Introdueix el pes total de la referencia: ");
            float pes_total = sc.nextInt();
            
            System.out.print("Introdueix la data de caducitat (YYYY-MM-DD): ");
            LocalDate dataCaducitat = LocalDate.parse(sc.nextLine());

            System.out.print("Introdueix la quantitat total: ");
            int quantitat_total = sc.nextInt();

            System.out.print("Introdueix el preu total: ");
            float preu_total = sc.nextFloat();

            // Llamamos al método de la lógica
            referencia.afegirReferencia(nom, uomStr.toString(), idFamilia, cifProveidor, dataAlta, pes_total,dataCaducitat, quantitat_total, preu_total);
            System.out.println("Proveidor afegit exitosament.");
        } catch (Exception e) {
            System.out.println("Error al afegir el proveidor: " + e.getMessage());
        }
    }
    
    /**
     * Método para dar de baja a una referencia existente.
     * Solicita al usuario el ID de la referencia y confirma la acción antes de proceder a eliminarla.
     */
    
    private void baixaReferencia() {
        try {
            System.out.print("Id de Referencia a donar de baixa: ");
            int id = sc.nextInt();

            // Confirmar la baja del proveedor
            System.out.print("Estàs segur que vols donar de baixa la referencia? (S/N): ");
            String confirmacio = sc.nextLine();

            if (confirmacio.equalsIgnoreCase("S")) {
                referencia.eliminarReferencia(id);
                System.out.println("Referencia donat de baixa correctament.");
            } else {
                System.out.println("Operació cancel·lada. La referencia no ha estat donat de baixa.");
            }
        } catch (Exception e) {
            System.out.println("Error al donar de baixa la referencia: " + e.getMessage());
        }
    }
    
     /**
     * Método para modificar una referencia existente.
     * Solicita al usuario el ID de la referencia y la nueva información para realizar la modificación.
     */
    
    private void modificarReferencia(){
        try {
            System.out.print("ID de la referència a modificar: ");
            int id = sc.nextInt();
            
            System.out.print("Nou nom de la referència: ");
            String nom = sc.nextLine();

            UnitatMesura uomStr = seleccionarUOM();
            
            System.out.print("Introdueix l'ID de la família: ");
            int idFamilia = sc.nextInt();

            System.out.print("Introdueix el CIF del proveïdor: ");
            String cifProveidor = sc.nextLine();

            System.out.print("Introdueix la data d'alta (YYYY-MM-DD): ");
            LocalDate dataAlta = LocalDate.parse(sc.nextLine());

            System.out.println("Introdueix el pes total de la referencia: ");
            float pes_total = sc.nextInt();
            
            System.out.print("Introdueix la data de caducitat (YYYY-MM-DD): ");
            LocalDate dataCaducitat = LocalDate.parse(sc.nextLine());

            System.out.print("Introdueix la quantitat: ");
            int quantitat_total = sc.nextInt();

            System.out.print("Introdueix el preu: ");
            float preu_total = sc.nextFloat();

            referencia.modificarReferencia(id, nom, uomStr.toString(), idFamilia, cifProveidor, dataAlta, pes_total,dataCaducitat, quantitat_total, preu_total);
            System.out.println("Referència modificada correctament.");
        } catch (Exception e) {
            System.out.println("Error al modificar la referència: " + e.getMessage());
        }
    }
    
    /**
     * Método para consultar referencias existentes.
     * Permite al usuario ver todas las referencias o solo aquellas sin stock.
     */
    
    private void consultarReferencies() {
    
        System.out.println("Vols veure (1) Totes les referències o (2) Referències sense estoc?");
        int opcio = sc.nextInt();

        if (opcio == 1) {
            // Obtener todas las referencias
            List<Referencia> referencies = referencia.obtenirTotesLesReferencies();
            mostrarReferencies(referencies, "Totes les Referències");
        } else if (opcio == 2) {
            // Obtener referencias sin stock
            List<Referencia> referenciesSenseEstoc = referencia.obtenirReferenciesSenseEstoc();
            mostrarReferencies(referenciesSenseEstoc, "Referències Sense Estoc");
        } else {
            System.out.println("Opció no vàlida.");
        }
    }
 
    /**
     * Método que muestra las referencias en un formato legible.
     * 
     * @param referencies Lista de referencias a mostrar.
     * @param titol Título que se mostrará antes de las referencias.
     */
    
    private void mostrarReferencies(List<Referencia> referencies, String titol) {
        
        if (referencies.isEmpty()) {
            System.out.println("No hi ha " + titol.toLowerCase() + ".");
        } else {
            System.out.println("=== " + titol + " ===");
            for (Referencia referencia : referencies) {
                System.out.println("ID: " + referencia.getId());
                System.out.println("Nom: " + referencia.getNom());
                System.out.println("Unitat de Mesura: " + referencia.getUom());
                System.out.println("ID Família: " + referencia.getId_familia());
                System.out.println("CIF Proveïdor: " + referencia.getCif_proveidor());
                System.out.println("Data d'Alta: " + referencia.getData_alta());
                System.out.println("Pes total: " + referencia.getPes_total());
                System.out.println("Data de Caducitat: " + referencia.getData_caducitat());
                System.out.println("Quantitat: " + referencia.getQuantitat_total());
                System.out.println("Preu: " + referencia.getPreu_total());
            }
        }
    }
    
    /**
     * Método para seleccionar la unidad de medida.
     * Permite al usuario elegir entre KG, L o PACKS.
     * 
     * @return Unidad de medida seleccionada.
     */
    
    private UnitatMesura seleccionarUOM() {

        while (true) {

            System.out.println("Unitats de mesura [KG][L][PACKS]: ");
            String uomStr = sc.nextLine();

            if (uomStr.equalsIgnoreCase("KG"))
                return UnitatMesura.KG;
            else if (uomStr.equalsIgnoreCase("L"))
                return UnitatMesura.L;
            else if (uomStr.equalsIgnoreCase("PACKS"))
                return UnitatMesura.PACKS;
            else {
                System.out.println("Opción incorrecta. Por favor, selecciona una opció vàlida.");
            }
        }
    }
}
