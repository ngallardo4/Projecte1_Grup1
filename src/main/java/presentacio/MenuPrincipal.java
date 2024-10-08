/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import aplicacio.App;
import aplicacio.model.Usuari;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ngallardo
 */
public class MenuPrincipal {
    
    @FXML
    private VBox mainMenu;
    
    @FXML
    private Button btnFamilia;
    
    @FXML
    private Button btnReferencia;
    
    @FXML
    private Button btnProveidor;
    
    @FXML
    private Button btnTancarSessio;
    
    private Usuari usuari;
    
    //Métode per establir l'usuari autenticat
    public void setUsuari(Usuari usuari){
        this.usuari = usuari;
    }
    
    @FXML
    private void handleFamilia(ActionEvent event){
        try{
            App.setRoot("menuFamilia", usuari);
        }catch (IOException e){e.printStackTrace();}
    }
    
    
    
    @FXML
    private void handleReferencia(ActionEvent event){
        try{
            App.setRoot("menuReferencia", usuari);
        }catch (IOException e){e.printStackTrace();}
    }
    
    @FXML
    private void handleProveidor(ActionEvent event){
        try{
            App.setRoot("menuProveidor", usuari);
        }catch (IOException e){e.printStackTrace();}
    }
    
    @FXML
    private void handleTancarSessio(ActionEvent event){
        try{
            App.setRoot("iniciSessio");
        }catch (IOException e){e.printStackTrace();}
    }
    
}
