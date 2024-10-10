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

/**
 * descripció: Aquesta classe és el controlador que gestiona la finestra del Menú
 * principal, gestiona les accions en pressionar els botons i manté l'informació
 * de l'usuari autenticat per pasar-ho als següents controladors, MenuFamilia, 
 * MenuProveidor i MenuReferencia.
 * @author ngallardo
 * @version 10/2024.1
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
    /**
     * Estableix l'usuari autenticat per a la sessió actual.
     * @param usuari, establert com a autenticat.
     */
    public void setUsuari(Usuari usuari){
        this.usuari = usuari;
    }
    
    /**
     * Gestiona l'acció del botó Familia, canvia a la vista del menú família.
     * @param event, esdeveniment de clic que desencadena l'acció.
     */
    @FXML
    private void handleFamilia(ActionEvent event){
        try{
            App.setRoot("menuFamilia", usuari);
        }catch (IOException e){e.printStackTrace();}
    } 
    
    /**
     * Gestiona l'acció del botó Referència, canvia a la vista del menú referència.
     * @param event, esdeveniment de clic que desencadena l'acció.
     */
    @FXML
    private void handleReferencia(ActionEvent event) {
        try {
            App.setRoot("menuReferencia", usuari);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gestiona l'acció del botó Proveïdor, canvia a la vista del menú proveïdor.
     * @param event, esdeveniment de clic que desencadena l'acció.
     */
    @FXML
    private void handleProveidor(ActionEvent event) {
        try {
            App.setRoot("menuProveidor", usuari);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gestiona l'acció del botó Tancar sessió, torna a la vista d'inici de sessió.
     * @param event, esdeveniment de clic que desencadena l'acció.
     */
    @FXML
    private void handleTancarSessio(ActionEvent event) {
        try {
            App.setRoot("iniciSessio");
        }catch (IOException e){e.printStackTrace();}
    }    
}
