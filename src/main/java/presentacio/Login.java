/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import aplicacio.App;
import aplicacio.model.Usuari;
import dades.DAOusuariImpl;
import java.io.IOException;
import javafx.event.ActionEvent;
import logica.UsuariLogica;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author ngall
 */
public class Login {
    
    @FXML
    private TextField correuField;
    @FXML
    private PasswordField contrasenyaField;
    @FXML
    private Button btnLogin;
    
    private DAOusuariImpl usuariDAO;
    private Usuari usuariAutenticat; // Variable per emmagatzemar l'usuari autenticat

    //Métode per inicialitzar el controlador
    @FXML
    public void initialize(){
        String rutaArxiu = "uspass.txt";
        usuariDAO = new DAOusuariImpl(rutaArxiu);
        
        //Acció que realitzarà el botó Confirmar
        btnLogin.setOnAction(this::verificarLogin);
    }
    
    private void verificarLogin(ActionEvent event){
        String email = correuField.getText();
        String password = contrasenyaField.getText();
        
        //Verificar si es correcte el format
        if(!UsuariLogica.emailValid(email)){
            mostrarMissatgeError("Correu incorrecte", "El correu no té un format vàlid.");
            return;
        }
        
        //Verificar l'usuari amb DAOusuariImpl
        if(usuariDAO.verificarUsuari(email, password)){
            usuariAutenticat = usuariDAO.getUsuari(email);
            mostrarMissatge("Login correcte. ", "Benvingut!");
            handleLogin(event);
        }else{
            mostrarMissatgeError("Login incorrecte", "Nom d'usuari o contrasenya incorrectes.");
        }
    }
    
    private void mostrarMissatge(String titol, String missatge){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titol);
        alert.setHeaderText(null);
        alert.setContentText(missatge);
        alert.showAndWait();
    }
    
    private void mostrarMissatgeError(String titol, String missatge){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titol);
        alert.setHeaderText(null);
        alert.setContentText(missatge);
        alert.showAndWait();
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
        try{
            App.setRoot("menuPrincipal");
        } catch (IOException e){
            mostrarMissatgeError("Error", "No s'ha pogut obrir el menú principal.");
        }
        
    }
}
