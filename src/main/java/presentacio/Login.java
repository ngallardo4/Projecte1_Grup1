/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import dades.DAOusuariImpl;
import javafx.stage.Stage;

/**
 *
 * @author ngall
 */
public class Login {
    
    private DAOusuariImpl usuariDAO;
    private Stage escenari;

    public Login(Stage escenari, String rutaArxiu) {
        this.escenari = escenari;
        this.usuariDAO = new DAOusuariImpl(rutaArxiu);       
    }
    
    public void mostrar(){
        
    }
    
    
}
