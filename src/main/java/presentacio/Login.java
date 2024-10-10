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
 * descripció: Aquesta classe és el controlador que gestiona l'interfície d'usuari
 * per al procés d'iniciar sessió. Verifica les credencials introduïdes per 
 * l'usuari i si són correctes permet l'accés a l'aplicació.
 * Utilitza una instància de DAOusuariImpl per obtenir les dades d'usuari i 
 * UsuariLogica per validar-les.
 * @author ngall
 * @version 10/2024.1
 */
public class Login {

    @FXML
    private TextField correuField;
    @FXML
    private PasswordField contrasenyaField;
    @FXML
    private Button btnLogin;
    
    private DAOusuariImpl usuariDAO;
    private UsuariLogica usuariLogica; 
    private Usuari usuariAutenticat;
    
    /**
     * Métode per inicialitzar el controlador de la finestra del Login. S'encarrega
     * de carregar el DAO amb el fitxer que porta les credencials correctes i 
     * inicialitza la lògica de l'usuari.
     */
    @FXML
    public void initialize(){
        String rutaArxiu = "uspass.txt";
        usuariDAO = new DAOusuariImpl(rutaArxiu);
        usuariLogica = new UsuariLogica(usuariDAO);
        
        //Acció que realitzarà el botó Confirmar
        btnLogin.setOnAction(this::verificarLogin);
    }
    
    /**
     * Métode per verificar el login de l'usuari, mitjançant els mètodes empleats
     * a DAOusuariImpl y UsuariLogica, quan es prem el botó.
     * @param event, l'esdeveniment de quan premem el botó de confirmar al login.
     */
    private void verificarLogin(ActionEvent event){
        String email = correuField.getText();
        String password = contrasenyaField.getText();
        
        //Verificar si es correcte el format
        if(!UsuariLogica.emailValid(email)){
            MostrarError.mostrarMissatgeError("Correu incorrecte", "El correu no té un format vàlid.");
            return;
        }
        
        //Verificar l'usuari amb UsuariLogica
        if(usuariLogica.verificarUsuari(email, password)){
            usuariAutenticat = usuariDAO.getUsuari(email);
            mostrarMissatge("Login correcte. ", "Benvingut!");
            handleLogin(event);
        }else{
            MostrarError.mostrarMissatgeError("Login incorrecte", "Nom d'usuari o contrasenya incorrectes.");
        }
    }
    
    /**
     * Aquest mètode serveix per mostrar un missatge a l'usuari.
     * @param titol, el títol de la finestra d'alerta.
     * @param missatge, el contingut del missatge que es mostrarà.
     */
    private void mostrarMissatge(String titol, String missatge){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titol);
        alert.setHeaderText(null);
        alert.setContentText(missatge);
        alert.showAndWait();
    }
    
    /**
     * Aquest mètode serveix per poder processar la redirecció a la finestra del
     * menú principal.
     * @param event, l'esdeveniment del login correcte.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        try{
            App.setRoot("menuPrincipal", usuariAutenticat);
        } catch (IOException e){
            MostrarError.mostrarMissatgeError("Error", "No s'ha pogut obrir el menú principal.");
            
        }    
    }
}
