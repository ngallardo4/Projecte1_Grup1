package presentacio;

import aplicacio.App;
import aplicacio.model.Familia;
import aplicacio.model.Usuari;
import java.io.IOException;
import java.time.LocalDate;
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
    private Usuari usuari; // Afegeix aquesta variable

    // Mètode per establir l'usuari autenticat
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari; // Assignar l'usuari a la variable de classe
        gestionarPermisos(); // Gestionar permisos després d'assignar l'usuari
    }

    @FXML
    public void initialize() throws IOException {
        llistaObservableFamilia = FXCollections.observableArrayList();
        familiaLogica = new FamiliaLogica(); // O com el creïs

        // Obtenir les famílies de la base de dades
        try {
            llistaObservableFamilia.addAll(familiaLogica.obtenirTotesLesFamilies());
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

        // Assignar event al TableView per seleccionar família
        TabViewFam.setItems(llistaObservableFamilia);
        TabViewFam.setOnMouseClicked(this::handleOnMouseClicked);

        // Desactivar botons al principi
        desactivarBotons();
        // Gestionar permisos al final
        gestionarPermisos();
    }

    private void gestionarPermisos() {
        // Habilitar o deshabilitar botons segons el rol
        if (usuari != null) {
            boolean esMagatzem = usuari.isRol();
            btnAfegir.setDisable(!esMagatzem);
            btnModificar.setDisable(!esMagatzem);
            btnEliminar.setDisable(!esMagatzem);
            btnProducte.setDisable(false); // Sempre habilitat per venedors
        }
    }

    private void desactivarBotons() {
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        btnProducte.setDisable(true);
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

                // Activar botons
                btnEliminar.setDisable(false);
                btnModificar.setDisable(false);
                btnProducte.setDisable(false);
            } else {
                System.out.println("No se seleccionó ninguna fila.");
                desactivarBotons();
            }
        }
    }

    @FXML
    public void btnLogo_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Logo' presionat");
        App.setRoot("menuPrincipal");
    }

    @FXML
    public void btnTancar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Tancar' presionat");
        App.setRoot("iniciSessio");
    }

    @FXML
    public void btnSortir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Sortir' presionat");
        App.setRoot("menuPrincipal");
    }

    @FXML
    public void btnAfegir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Nova' presionat");

        // Crear una nova família amb valors buits (només per al TableView)
        Familia novaFamilia = new Familia(0, "", "", LocalDate.now(), "", "");

        // Afegir la nova família a la llista observable
        llistaObservableFamilia.add(novaFamilia);

        // Actualitzar el TableView
        TabViewFam.setItems(llistaObservableFamilia);
        TabViewFam.getSelectionModel().select(novaFamilia);
    }

    @FXML
    public void btnModificar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Modificar' presionat");
        Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                String nomNou = tf_nomFam.getText();
                String descripcioNova = tf_desFam.getText();
                LocalDate dataAltaNova = LocalDate.parse(tf_dataltaFam.getText());
                String provDefecteNou = tf_provFam.getText();
                String observacionsNovas = tf_obvsFam.getText();

                if (familiaSeleccionada.getId() == 0) {
                    // Si l'ID és 0 és una nova família llavors inserim
                    familiaLogica.afegirFamilia(nomNou, descripcioNova, dataAltaNova, provDefecteNou, observacionsNovas);
                    // Actualitzem l'objecte seleccionat per fer-lo coincidir amb la nova família
                    System.out.println("Nova família afegida amb ID: " + familiaSeleccionada.getId());
                } else {
                    // Si l'ID és diferent de 0 ja existeix i fem una actualització
                    familiaLogica.modificarFamilia(familiaSeleccionada.getId(), nomNou, descripcioNova, dataAltaNova, provDefecteNou, observacionsNovas);
                    System.out.println("Família modificada correctament.");
                }

                // Actualizar els valors de la familia a la llista observable (TableView)
                familiaSeleccionada.setNom(nomNou);
                familiaSeleccionada.setDescripcio(descripcioNova);
                familiaSeleccionada.setData_alta(dataAltaNova);
                familiaSeleccionada.setProv_defecte(provDefecteNou);
                familiaSeleccionada.setObservacions(observacionsNovas);

                // Refrescar el TableView per mostrar els canvis
                TabViewFam.refresh();
                TabViewFam.setItems(llistaObservableFamilia);

                System.out.println("Familia modificada correctament.");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error en modificar la família: " + e.getMessage());
            }
        } else {
            System.out.println("No s'ha seleccionat cap família per modificar.");
        }
    }

    @FXML
    public void btnEliminar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Eliminar' presionat");
        Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                // Eliminar de la base de dades
                familiaLogica.eliminarFamilia(familiaSeleccionada.getId());
                System.out.println("Familia eliminada de la base de dades.");

                // Eliminar de la llista observable
                llistaObservableFamilia.remove(familiaSeleccionada);
                TabViewFam.setItems(llistaObservableFamilia);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No s'ha seleccionat cap família per eliminar.");
        }
    }

    @FXML
    public void btnProducte_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Productes' presionat");
        Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            // Passar la ID de la família a la nova vista
            App.setRoot("menuReferencia"); // Canviem de vista

            // Aquí passes la ID a la nova vista
            //PantallaReferencia controller = (PantallaReferencia) App.getController("menuReferencia");
            //controller.setFamiliaId(familiaSeleccionada.getId());
        } else {
            System.out.println("No s'ha seleccionat cap família.");
        }
    }
}
