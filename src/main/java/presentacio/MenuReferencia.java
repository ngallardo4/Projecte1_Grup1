/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author Héctor Vico
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MenuReferencia {

    // Identificadors dels botons i altres elements de la interfície
    @FXML
    private Button btnLogo, btnTancarSessio, btnAfegir, btnMod, btnElimi, btnEstoc, btnSortida;

    @FXML
    private TextField tfId, tfNom, tfUOM, tfIdFamilia, tfCifProveidor, tfDataAlta, tfPes, tfQuantitat, tfPreu;

    @FXML
    private TableView<?> tabViewRef; // Defineix el tipus adequat segons les dades de la taula

    // Mètodes per gestionar els esdeveniments dels botons
    @FXML
    public void initialize() {
        // Defineix l'acció de cada botó
        btnAfegir.setOnAction(event -> afegirNovaReferencia());
        btnMod.setOnAction(event -> modificarReferencia());
        btnElimi.setOnAction(event -> eliminarReferencia());
        btnEstoc.setOnAction(event -> mostrarReferenciesSenseEstoc());
        btnSortida.setOnAction(event -> sortir());
        btnTancarSessio.setOnAction(event -> tancarSessio());
        btnLogo.setOnAction(event -> mostrarInici());
    }

    private void afegirNovaReferencia() {
        // Acció quan es fa clic en "Afegir"
        System.out.println("Afegir nova referència");
        // Aquí implementes la lògica per afegir una nova referència
    }

    private void modificarReferencia() {
        // Acció quan es fa clic en "Modificar"
        if (tabViewRef.getSelectionModel().getSelectedItem() != null) {
            // Si una fila està seleccionada, la podem modificar
            System.out.println("Modificar referència seleccionada");
            // Aquí implementes la lògica per modificar la referència seleccionada
        } else {
            // Si no hi ha cap fila seleccionada, pots mostrar un missatge d'error
            System.out.println("Cap fila seleccionada");
        }
    }

    private void eliminarReferencia() {
        // Acció quan es fa clic en "Eliminar"
        if (tabViewRef.getSelectionModel().getSelectedItem() != null) {
            // Si una fila està seleccionada, la podem eliminar
            System.out.println("Eliminar referència seleccionada");
            // Aquí implementes la lògica per eliminar la referència seleccionada
        } else {
            // Si no hi ha cap fila seleccionada, pots mostrar un missatge d'error
            System.out.println("Cap fila seleccionada");
        }
    }

    private void mostrarReferenciesSenseEstoc() {
        // Acció quan es fa clic en "Ref. sense estoc"
        System.out.println("Mostrar referències sense estoc");
        // Aquí implementes la lògica per mostrar les referències sense estoc
    }

    private void sortir() {
        // Acció quan es fa clic en "<< Sortir"
        System.out.println("Sortir");
        // Aquí implementes la lògica per sortir de la pantalla actual
    }

    private void tancarSessio() {
        // Acció quan es fa clic en "Tancar Sessió"
        System.out.println("Tancar sessió");
        // Aquí implementes la lògica per tancar la sessió de l'usuari
    }

    private void mostrarInici() {
        // Acció quan es fa clic en el botó de "Logo"
        System.out.println("Tornar a la pantalla d'inici");
        // Aquí implementes la lògica per tornar a la pantalla d'inici
    }
}

