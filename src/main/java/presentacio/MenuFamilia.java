package presentacio;

import aplicacio.App;
import aplicacio.model.Familia;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.FamiliaLogica;

public class MenuFamilia {

    @FXML
    private TableView TabViewFam;

    @FXML
    private TableColumn colId, colNom, colDescrip, colData, colProv, colObvs;

    @FXML
    private TextField tf_idFam, tf_nomFam, tf_desFam, tf_dataltaFam, tf_provFam, tf_obvsFam;

    @FXML
    private Button btnLogo;

    @FXML
    private Button btnTancar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnAfegir;

    @FXML
    private Button btnSortir;

    @FXML
    private Button btnProducte;

    private ObservableList<Familia> llistaObservableFamilia;
    private FamiliaLogica familiaLogica;

    @FXML
    public void initialize() throws IOException {
        llistaObservableFamilia = FXCollections.observableArrayList();

        // Assegura't d'inicialitzar l'objecte familiaLogica
        familiaLogica = new FamiliaLogica(); // O com el creïs

        // Obtenir les famílies de la base de dades
        try {
            llistaObservableFamilia.addAll(familiaLogica.obtenirTotesLesFamilies()); // Assumeix que tens aquest mètode a FamiliaLogica
        } catch (Exception e) {
            e.printStackTrace(); // Maneig d'errors, si cal
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_alta"));
        colProv.setCellValueFactory(new PropertyValueFactory<>("prov_defecte"));
        colObvs.setCellValueFactory(new PropertyValueFactory<>("observacions"));

        // Fem editables les columnes Nom, Descripció i Observacions amb la classe TextFieldTableCell
        colNom.setCellFactory(TextFieldTableCell.forTableColumn());
        colDescrip.setCellFactory(TextFieldTableCell.forTableColumn());
        colObvs.setCellFactory(TextFieldTableCell.forTableColumn());

        // COLUMNA NOM
        colNom.setOnEditCommit(event -> {
            // recuperem l'objecte CellEditEvent que ens dona informació de l'esdeveniment
            TableColumn.CellEditEvent<Familia, String> e = (TableColumn.CellEditEvent<Familia, String>) event;

            // recuperem l'objecte Familia de la fila afectada
            Familia familia = e.getRowValue();
            // li assignem el nou valor
            familia.setNom(e.getNewValue());
        });

        // COLUMNA DESCRIPCIO
        colDescrip.setOnEditCommit(event -> {
            // recuperem l'objecte CellEditEvent que ens dona informació de l'esdeveniment
            TableColumn.CellEditEvent<Familia, String> e = (TableColumn.CellEditEvent<Familia, String>) event;

            // recuperem l'objecte Familia de la fila afectada
            Familia familia = e.getRowValue();
            // li assignem el nou valor
            familia.setDescripcio(e.getNewValue());
        });

        // COLUMNA OBSERVACIONS
        colObvs.setOnEditCommit(event -> {
            // recuperem l'objecte CellEditEvent que ens dona informació de l'esdeveniment
            TableColumn.CellEditEvent<Familia, String> e = (TableColumn.CellEditEvent<Familia, String>) event;

            // recuperem l'objecte Familia de la fila afectada
            Familia familia = e.getRowValue();
            // li assignem el nou valor
            familia.setObservacions(e.getNewValue());
        });

        // finalment afegim els elements a la llista
        TabViewFam.setItems(llistaObservableFamilia);
        TabViewFam.setOnMouseClicked(this::handleOnMouseClicked);

    }

    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        if (ev.getButton() == MouseButton.PRIMARY) {
            Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

            if (familiaSeleccionada != null) {
                System.out.println("Familia seleccionada: " + familiaSeleccionada.toString());
                tf_idFam.setText(String.valueOf(familiaSeleccionada.getId()));
                tf_nomFam.setText(familiaSeleccionada.getNom());
                tf_desFam.setText(familiaSeleccionada.getDescripcio());
                tf_dataltaFam.setText(familiaSeleccionada.getData_alta().toString());
                tf_provFam.setText(familiaSeleccionada.getProv_defecte());
                tf_obvsFam.setText(familiaSeleccionada.getObservacions());
            } else {
                System.out.println("No se seleccionó ninguna fila.");
            }
        }

    }

    @FXML
    public void btnLogo_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Logo' presionat");
        App.setRoot("secondary");
    }

    @FXML
    public void btnTancar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Tancar' presionat");
        Stage stage = (Stage) btnTancar.getScene().getWindow(); // Obtener la ventana actual
        stage.close(); // Cerrar la ventana
        Platform.exit(); // Finalizar la aplicación
        //App.setRoot("iniciSessio");
    }

    @FXML
    public void btnSortir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Sortir' presionat");
        App.setRoot("secondary");
    }

    @FXML
    public void btnAfegir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Nova' presionat");

        // Obtenir les dades dels TextFields
        String nom = tf_nomFam.getText();
        String descripcio = tf_desFam.getText();
        LocalDate dataAlta = LocalDate.parse(tf_dataltaFam.getText());
        String provDefecte = tf_provFam.getText();
        String observacions = tf_obvsFam.getText();

        // Afegir la nova família a la base de dades
        try {
            familiaLogica.afegirFamilia(nom, descripcio, dataAlta, provDefecte, observacions);
            System.out.println("Familia afegida a la base de dades.");

            // Crear un nou objecte Família i afegir-lo a la llista observable
            Familia novaFamilia = new Familia(0, nom, descripcio, dataAlta, provDefecte, observacions);
            llistaObservableFamilia.add(novaFamilia); // Aquí ja és accessible
            TabViewFam.setItems(llistaObservableFamilia); // Actualitza el TableView

            // Netejar els camps de text després de l'addició
            tf_nomFam.clear();
            tf_desFam.clear();
            tf_dataltaFam.clear();
            tf_provFam.clear();
            tf_obvsFam.clear();

        } catch (Exception e) {
            e.printStackTrace(); // Maneig d'errors
        }
    }

    @FXML
    public void btnModificar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Modificar' presionat");
        Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                // Obtener los nuevos valores de los campos de texto
                String nomNou = tf_nomFam.getText();
                String descripcioNova = tf_desFam.getText();
                LocalDate dataAltaNova = LocalDate.parse(tf_dataltaFam.getText());
                String provDefecteNou = tf_provFam.getText();
                String observacionsNovas = tf_obvsFam.getText();

                // Llamar al método de lógica para actualizar en la base de datos
                familiaLogica.modificarFamilia(familiaSeleccionada.getId(), nomNou, descripcioNova, dataAltaNova, provDefecteNou, observacionsNovas);

                // Actualizar los valores de la familia en la lista observable (TableView)
                familiaSeleccionada.setNom(nomNou);
                familiaSeleccionada.setDescripcio(descripcioNova);
                familiaSeleccionada.setData_alta(dataAltaNova);
                familiaSeleccionada.setProv_defecte(provDefecteNou);
                familiaSeleccionada.setObservacions(observacionsNovas);

                // Refrescar el TableView para mostrar los cambios
                TabViewFam.refresh(); // Esto asegura que el TableView se actualice con los nuevos valores
                TabViewFam.setItems(llistaObservableFamilia);

                System.out.println("Familia modificada correctament.");

            } catch (Exception e) {
                e.printStackTrace(); // Manejo de errores
                System.out.println("Error al modificar la familia: " + e.getMessage());
            }
        } else {
            System.out.println("No s'ha seleccionat cap família per modificar.");
        }
    }

    @FXML
    public void btnEliminar_action(ActionEvent event) throws IOException {
        // Obtenemos la familia seleccionada
        Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                // Eliminamos la familia de la base de datos
                familiaLogica.eliminarFamilia(familiaSeleccionada.getId());
                System.out.println("Familia eliminada de la base de dades.");

                // Eliminamos la familia de la lista observable
                llistaObservableFamilia.remove(familiaSeleccionada);
                TabViewFam.setItems(llistaObservableFamilia); // Actualizamos el TableView

            } catch (Exception e) {
                e.printStackTrace(); // Manejo de errores
            }
        } else {
            System.out.println("No se ha seleccionat cap família per eliminar.");
        }
    }

    @FXML
    public void btnProducte_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Productes' presionat");
        App.setRoot("menuReferencia");
    }
}
