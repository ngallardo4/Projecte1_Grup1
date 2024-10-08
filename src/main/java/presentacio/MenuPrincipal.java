/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import aplicacio.model.Usuari;
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
        carregarEscena("menuFamilia.fxml","Familia");
    }
    
    
    
    @FXML
    private void handleReferencia(ActionEvent event){
        carregarEscena("menuReferencia.fxml","Referència");
    }
    
    @FXML
    private void handleProveidor(ActionEvent event){
        carregarEscena("menuProveidor.fxml","Proveïdor");
    }
    
    @FXML
    private void handleTancarSessio(ActionEvent event){
        carregarEscena("iniciSessio.fxml","Iniciar Sessió");
    }
    
    private void carregarEscena(String fxmlArxiu, String title){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlArxiu));
            Parent root = loader.load();
            Stage stage = (Stage) mainMenu.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e){e.printStackTrace();}
    }
}
