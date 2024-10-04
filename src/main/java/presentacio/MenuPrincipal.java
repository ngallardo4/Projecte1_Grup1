/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import java.util.Scanner;
import dades.DAOproveidorImpl;
import dades.DAOfamiliaImpl;
import dades.DAOreferencia;
import aplicacio.model.Proveidor;
import aplicacio.model.Familia;
import aplicacio.model.Referencia;
import enums.EstatProveidor;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author danie
 */
public class MenuPrincipal {
    
    Scanner sc = new Scanner(System.in);
    DAOproveidorImpl proveidorDAO = new DAOproveidorImpl();
    DAOfamiliaImpl familiaDAO = new DAOfamiliaImpl();
    DAOreferencia referenciaDAO = new DAOreferencia();
    
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
            
            switch(opcio){
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
    
    public void llistarProveidors(){
        
        while(true){
            
            System.out.println("=== Menú Principal ===");
            System.out.println("1.- Alta de proveidor.");
            System.out.println("2.- Baixa de proveidor.");
            System.out.println("3.- Modificar proveidor.");
            System.out.println("4.- Consultar proveidor.");
            System.out.println("0.- Sortir.");
            
            System.out.println("Selecciona una opcio: ");
            int opcio = sc.nextInt();
            sc.nextLine();
            
            switch(opcio){
                case 1:
                    altaProveidor();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Sortir.");
                    return;
                default:
                    System.out.println("Opcio invalida.");
            }
        }
    }
    
    private void altaProveidor() {
        
        System.out.print("Introdueix el CIF: ");
        String CIF = sc.nextLine();
        
        System.out.print("Introdueix el nom: ");
        String Nom = sc.nextLine();
                
        EstatProveidor Estat = seleccionarEstat();
        
        System.out.print("Introduce el motiu inactiu: ");
        String MotivoInactivo = sc.nextLine();
        
        System.out.print("Introdueix el telefon: ");
        String Telefon = sc.nextLine();
        
        System.out.print("Introdueix el descompte: ");
        float Descompte = sc.nextFloat();
        
        System.out.print("Introdueix la fecha d'alta (YYYY-MM-DD): ");
        LocalDate Data_Alta = LocalDate.parse(sc.nextLine());
        
        System.out.print("Introdueix la qualificacio: ");
        int Qualificacio = sc.nextInt();

        Proveidor nuevoProveedor = new Proveidor(CIF, Nom, Estat, MotivoInactivo, Telefon, Descompte, Data_Alta, Qualificacio);
        proveidorDAO.afegir(nuevoProveedor);

        System.out.println("Proveedor afegit exitosament.");
    }
    private void baixaProveidor(){
    
        System.out.print("CIF del Proveidor a donar de baixa: ");
        String cif = sc.nextLine();
      
        List<Proveidor> proveidors = proveidorDAO.obtenirEntitats();

        Proveidor proveidor = null;
        for (Proveidor p : proveidors) {
            if (p.getCIF().equals(cif)) {
                proveidor = p;
                break;
            }
        }

        // Confirmar la baja del proveedor
        System.out.print("Estàs segur que vols donar de baixa el proveidor " + proveidor.getNom() + "? (S/N): ");
        String confirmacio = sc.nextLine();

        if (confirmacio.equalsIgnoreCase("S")) {
            proveidorDAO.eliminar(proveidor);
            System.out.println("Proveidor donat de baixa correctament.");
        } else {
            System.out.println("Operació cancel·lada. El proveidor no ha estat donat de baixa.");
        }
    }

    private void modificarProveidor() {

        System.out.print("CIF del Proveidor a modificar: ");
        String cif = sc.nextLine();

        // Obtener la lista completa de proveedores
        List<Proveidor> proveidors = proveidorDAO.obtenirEntitats();

        // Buscar el proveedor por su CIF
        Proveidor proveidor = null;
        for (Proveidor p : proveidors) {
            if (p.getCIF().equals(cif)) {
                proveidor = p;
                break;
            }
        }

        if (proveidor == null) {
            System.out.println("Proveidor no trobat.");
            return;
        }

        // Modificar los datos del proveedor
        System.out.print("Nom actual: " + proveidor.getNom() + ". Nou nom: ");
        String nouNom = sc.nextLine();
        proveidor.setNom(nouNom);

        // Permitimos al usuario modificar el estado
        EstatProveidor Estat = seleccionarEstat();
        proveidor.setEstat(Estat);

        // Llamamos al DAO para actualizarlo
        proveidorDAO.actualitzar(proveidor);

        System.out.println("Proveidor modificat correctament.");
    }   

    
    private EstatProveidor seleccionarEstat(){
        
        while(true){
        
            System.out.println("Estat del proveidor [ACTIU][INACTIU]: ");
            String Estat = sc.nextLine();
        
            if(Estat.equalsIgnoreCase("ACTIU"))
                return EstatProveidor.ACTIU;
            else if(Estat.equalsIgnoreCase("INACTIU"))
                return EstatProveidor.INACTIU;
            else {
                System.out.println("Opción incorrecta. Por favor, selecciona una opció vàlida.");
            }
        }
    } 
}
