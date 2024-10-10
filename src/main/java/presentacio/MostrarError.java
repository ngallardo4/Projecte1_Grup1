/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import javafx.scene.control.Alert;

/**
 * descripció: Classe creada per poder mostrar els missatges d'errors, tant com
 * al Login com a les Excepcions.
 * @author ngall
 * @version 10/2024.1
 */
public class MostrarError {
    /**
     * Aquest mètode serveix per mostrar un missatge d'error a l'usuari.
     * @param titol, el títol de la finestra d'alerta.
     * @param missatge, el contingut del missatge que es mostrarà.
     */
    public static void mostrarMissatgeError(String titol, String missatge){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titol);
        alert.setHeaderText(null);
        alert.setContentText(missatge);
        alert.showAndWait();
    }
}
