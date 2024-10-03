/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import java.util.Scanner;
import dades.DAOproveidorImpl;
import dades.DAOfamilia;
import dades.DAOreferencia;
import aplicacio.model.Proveidor;
import aplicacio.model.Familia;
import aplicacio.model.Referencia;
import enums.EstatProveidor;
import java.time.LocalDate;

/**
 *
 * @author danie
 */
public class MenuPrincipal {
    
    private Scanner sc;
    private DAOproveidorImpl proveidorDAO;
    private DAOfamilia familiaDAO;
    private DAOreferencia referenciaDAO;
    

    public MenuPrincipal() {
        sc = new Scanner(System.in);
        proveidorDAO = new DAOproveidorImpl();
        familiaDAO = new DAOfamilia();
        referenciaDAO = new DAOreferencia();        
    }
    
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
                    afegir();
                    break;
            }
        }
    }
    private void anadirProveedor() {
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
        
        sc.nextLine(); // Consumir el salto de línea
        
        System.out.print("Introdueix la fecha d'alta (YYYY-MM-DD): ");
        LocalDate Data_Alta = LocalDate.parse(sc.nextLine());
        
        System.out.print("Introdueix la qualificacio: ");
        int Qualificacio = sc.nextInt();

        Proveidor nuevoProveedor = new Proveidor(CIF, Nom, Estat, MotivoInactivo, Telefon, Descompte, Data_Alta, Qualificacio);
        proveidorDAO.afegir(nuevoProveedor);

        System.out.println("Proveedor afegit exitosament.");
    }
    
    private EstatProveidor seleccionarEstat(){
        System.out.println("Estat del proveidor [ACTIU][INACTIU]: ");
        String Estat = sc.nextLine();
        
        if(Estat == "ACTIU")
            return EstatProveidor.ACTIU;
        else if(Estat == "INACTIU")
            return EstatProveidor.INACTIU;
        else {
            System.out.println("Opción incorrecta. Por defecto se selecciona ACTIU.");
            return EstatProveidor.ACTIU;
        }
    } 
}
