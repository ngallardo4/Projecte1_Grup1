/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import dades.DAOusuari;
import javafx.stage.Stage;

/**
 *
 * @author ngall
 */
public class Login {
    
    private DAOusuari usuariDAO;
    private Stage escenari;

    public Login(Stage escenari, String rutaArxiu) {
        this.escenari = escenari;
        this.usuariDAO = new DAOusuari(rutaArxiu);       
    }
    
    public void mostrar(){
        
    }
    
    
}
