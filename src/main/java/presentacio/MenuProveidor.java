/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author danie
 */
import aplicacio.App;
import aplicacio.model.Proveidor;
import enums.EstatProveidor;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import logica.ProveidorLogica;

public class MenuProveidor {

    @FXML
    private TableView<Proveidor> tabViewProveidor;

    @FXML
    private TableColumn<Proveidor, Integer> colId;
    @FXML
    private TableColumn<Proveidor, String> colNom, colCif, colMotiuInactiu, colTelefon;
    @FXML
    private TableColumn<Proveidor, EstatProveidor> colEstat;
    @FXML
    private TableColumn<Proveidor, Float> colQualificacio, colDescompte;
    @FXML
    private TableColumn<Proveidor, LocalDate> colDataAlta;

    @FXML
    private Button btnAfegir, btnMod, btnElimi, btnTancarSessio, btnSortida;

    @FXML
    private TextField tfNom, tfCif, tfMotiuInactiu, tfTelefon,tfQualificacio, tfDescompte, tfDataAlta;

    @FXML
    private ComboBox<EstatProveidor> cbEstat;

    private ObservableList<Proveidor> llistaObservableProveidor;
    private ProveidorLogica proveidorLogica;

    @FXML
    public void initialize() {
        llistaObservableProveidor = FXCollections.observableArrayList();
        proveidorLogica = new ProveidorLogica();

        try {
            llistaObservableProveidor.addAll(proveidorLogica.obtenirTotsElsProveidors());
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }

        // Configurar las columnas con el modelo Proveidor
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCif.setCellValueFactory(new PropertyValueFactory<>("cif"));
        colEstat.setCellValueFactory(new PropertyValueFactory<>("estat"));
        colMotiuInactiu.setCellValueFactory(new PropertyValueFactory<>("motiuInactiu"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        colQualificacio.setCellValueFactory(new PropertyValueFactory<>("qualificacio"));
        colDescompte.setCellValueFactory(new PropertyValueFactory<>("descompte"));
        colDataAlta.setCellValueFactory(new PropertyValueFactory<>("dataAlta"));

        // Asignar la lista observable al TableView
        tabViewProveidor.setItems(llistaObservableProveidor);
        tabViewProveidor.setOnMouseClicked(this::handleOnMouseClicked);

        // Rellenar el ComboBox con los valores de EstatProveidor
        cbEstat.setItems(FXCollections.observableArrayList(EstatProveidor.values()));

        // Desactivar botones al inicio
        desactivarBotons();
    }

    private void desactivarBotons() {
        btnElimi.setDisable(true);
        btnMod.setDisable(true);
    }

    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        if (ev.getButton() == MouseButton.PRIMARY) {
            Proveidor proveidorSeleccionat = tabViewProveidor.getSelectionModel().getSelectedItem();

            if (proveidorSeleccionat != null) {
                // Rellenar los campos con la información del proveedor seleccionado
                tfNom.setText(proveidorSeleccionat.getNom());
                tfCif.setText(proveidorSeleccionat.getCIF());
                cbEstat.setValue(proveidorSeleccionat.getEstat());
                tfMotiuInactiu.setText(proveidorSeleccionat.getMotiuInactiu());
                tfQualificacio.setText(String.valueOf(proveidorSeleccionat.getQualificacio()));
                tfDescompte.setText(String.valueOf(proveidorSeleccionat.getDescompte()));
                tfDataAlta.setText(proveidorSeleccionat.getData_Alt().toString());

                // Activar botones
                btnElimi.setDisable(false);
                btnMod.setDisable(false);
            } else {
                desactivarBotons();
            }
        }
    }

    @FXML
    public void btnSortida_action(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal");
    }

    @FXML
    public void btnTancarSessio_action(ActionEvent event) throws IOException {
        App.setRoot("iniciSessio");
    }

    @FXML
    public void btnAfegir_action(ActionEvent event) {
        System.out.println("Botó 'Afegir' presionat");

        // Obtener el valor seleccionado del ComboBox
        EstatProveidor estat = cbEstat.getValue();

        // Crear una nova referència amb el valor seleccionat
        Proveidor nouProveidor = new Proveidor("","", estat, "", "", 0.0f,LocalDate.now(), 0);

        // Afegir la nova referència a la llista observable
        llistaObservableProveidor.add(nouProveidor);

        // Actualitzar el TableView
        tabViewProveidor.setItems(llistaObservableProveidor);
        tabViewProveidor.getSelectionModel().select(nouProveidor);
    }

    @FXML
    public void btnMod_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Modificar' presionat");
        Proveidor proveidorSeleccionat = (Proveidor) tabViewProveidor.getSelectionModel().getSelectedItem();

        if (proveidorSeleccionat != null) {
            try {
                String cifNou = tfCif.getText();
                String nomNou = tfNom.getText();
                EstatProveidor estatNou = cbEstat.getValue();  // Obtener el valor del ComboBox para UOM
                String motiuInactiuNou = tfMotiuInactiu.getText();
                String telefonNou = tfTelefon.getText();
                Float descompteNou = Float.parseFloat(tfDescompte.getText());
                LocalDate dataAltaNova = LocalDate.parse(tfDataAlta.getText());
                int qualificacioNova = Integer.parseInt(tfQualificacio.getText());

                if (proveidorSeleccionat.equals(tfCif)) {
                    // Si l'ID és 0 és una nova família llavors inserim
                    proveidorLogica.afegirProveidor(cifNou,nomNou, estatNou, motiuInactiuNou, telefonNou, descompteNou,dataAltaNova,qualificacioNova);
                    // Actualitzem l'objecte seleccionat per fer-lo coincidir amb la nova família
                    System.out.println("Nova família afegida amb ID: " + proveidorSeleccionat.getCIF());
                } else {
                    // Si l'ID és diferent de 0 ja existeix i fem una actualització
                    proveidorLogica.modificarProveidor(cifNou, nomNou, estatNou, motiuInactiuNou, telefonNou, 0, dataAltaNova, qualificacioNova);
                    System.out.println("Família modificada correctament.");
                }

                // Actualizar els valors de la familia a la llista observable (TableView)
                proveidorSeleccionat.setCIF(cifNou);
                proveidorSeleccionat.setNom(nomNou);
                proveidorSeleccionat.setEstat(estatNou);
                proveidorSeleccionat.setMotiuInactiu(motiuInactiuNou);
                proveidorSeleccionat.setTelefon(telefonNou);
                proveidorSeleccionat.setData_Alt(dataAltaNova);
                proveidorSeleccionat.setDescompte(descompteNou);
                proveidorSeleccionat.setQualificacio(qualificacioNova);

                // Refrescar el TableView per mostrar els canvis
                tabViewProveidor.refresh();
                tabViewProveidor.setItems(llistaObservableProveidor);

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
    public void btnElimi_action(ActionEvent event) throws Exception {
        Proveidor proveidorSeleccionat = tabViewProveidor.getSelectionModel().getSelectedItem();

        if (proveidorSeleccionat != null) {
            // Eliminar el proveedor de la base de datos y de la lista observable
            proveidorLogica.eliminarProveidor(proveidorSeleccionat.getCIF());
            llistaObservableProveidor.remove(proveidorSeleccionat);

            // Refrescar el TableView
            tabViewProveidor.refresh();
        }
    }
}
