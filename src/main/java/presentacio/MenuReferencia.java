/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author Héctor Vico
 */
import aplicacio.App;
import aplicacio.model.Referencia;
import enums.UnitatMesura;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Platform;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.ReferenciaLogica;

public class MenuReferencia {

    @FXML
    private TableView<Referencia> TabViewRef;

    @FXML
    private TableColumn colId, colNom, colUOM, colIdFamilia, colCifProveidor, colDataAlta, colPes, colDataCaducitat, colQuantitat, colPreu;

    @FXML
    private TextField tfId, tfNom, tfUOM, tfIdFamilia, tfCifProveidor, tfDataAlta, tfPes, tfDataCaducitat, tfQuantitat, tfPreu;

    @FXML
    private Button btnLogo;

    @FXML
    private Button btnTancar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnAfegir;

    @FXML
    private Button btnSortir;

    @FXML
    private Button btnProd;

    @FXML
    private ComboBox<UnitatMesura> cbUOM;

    private ObservableList<Referencia> llistaObservableReferencia;
    private ReferenciaLogica referenciaLogica;

    @FXML
    public void initialize() throws IOException {
        llistaObservableReferencia = FXCollections.observableArrayList();

        // Inicializa la lógica de Referencia
        referenciaLogica = new ReferenciaLogica();
        cbUOM.getItems().addAll(UnitatMesura.values());

        // Obtener las referencias de la base de datos
        try {
            llistaObservableReferencia.addAll(referenciaLogica.obtenirTotesLesReferencies());
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }

        // Configurar las columnas del TableView
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colUOM.setCellValueFactory(new PropertyValueFactory<>("uom"));
        colIdFamilia.setCellValueFactory(new PropertyValueFactory<>("id_familia"));
        colCifProveidor.setCellValueFactory(new PropertyValueFactory<>("cif_proveidor"));
        colDataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta"));
        colPes.setCellValueFactory(new PropertyValueFactory<>("pes_total"));
        colDataCaducitat.setCellValueFactory(new PropertyValueFactory<>("data_caducitat"));
        colQuantitat.setCellValueFactory(new PropertyValueFactory<>("quantitat_total"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu_total"));

        // Hacer editables algunas columnas si es necesario
        colNom.setCellFactory(TextFieldTableCell.forTableColumn());
        colNom.setOnEditCommit(event -> {
            TableColumn.CellEditEvent<Referencia, String> e = (TableColumn.CellEditEvent<Referencia, String>) event;
            Referencia referencia = e.getRowValue();
            referencia.setNom(e.getNewValue());
        });

        // Configurar el TableView
        TabViewRef.setItems(llistaObservableReferencia);
        TabViewRef.setOnMouseClicked(this::handleOnMouseClicked);
    }

    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        if (ev.getButton() == MouseButton.PRIMARY) {
            Referencia referencia = TabViewRef.getSelectionModel().getSelectedItem();

            if (referencia != null) {
                System.out.println("Referencia seleccionada: " + referencia.toString());
                tfId.setText(String.valueOf(referencia.getId()));
                tfNom.setText(referencia.getNom());
                tfUOM.setText(referencia.getUom().toString());
                tfIdFamilia.setText(String.valueOf(referencia.getId_familia()));
                tfCifProveidor.setText(referencia.getCif_proveidor());
                tfDataAlta.setText(referencia.getData_alta().toString());
                tfPes.setText(String.valueOf(referencia.getPes_total()));
                tfDataCaducitat.setText(referencia.getData_caducitat().toString());
                tfQuantitat.setText(String.valueOf(referencia.getQuantitat_total()));
                tfPreu.setText(String.valueOf(referencia.getPreu_total()));
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
        Stage stage = (Stage) btnTancar.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @FXML
    public void btnSortir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Sortir' presionat");
        App.setRoot("secondary");
    }

    @FXML
    public void btnNova_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Nova' presionat");

        String nom = tfNom.getText();
        UnitatMesura uom = cbUOM.getValue();
        int idFamilia = Integer.parseInt(tfIdFamilia.getText());
        String cifProveidor = tfCifProveidor.getText();
        LocalDate dataAlta = LocalDate.parse(tfDataAlta.getText());
        float pes_total = Float.parseFloat(tfPes.getText());
        LocalDate dataCaducitat = LocalDate.parse(tfDataCaducitat.getText());
        int quantitat_total = Integer.parseInt(tfQuantitat.getText());
        float preu_total = Float.parseFloat(tfPreu.getText());

        try {
            referenciaLogica.afegirReferencia(nom, uom, idFamilia, cifProveidor, dataAlta, dataCaducitat, pes_total, quantitat_total, preu_total);
            Referencia novaReferencia = new Referencia(0, nom, uom, idFamilia, cifProveidor, dataAlta, pes_total, dataCaducitat, quantitat_total, preu_total);
            llistaObservableReferencia.add(novaReferencia);
            TabViewRef.setItems(llistaObservableReferencia);

            // Limpiar los campos después de agregar
            tfNom.clear();
            cbUOM.getSelectionModel().clearSelection();
            tfIdFamilia.clear();
            tfCifProveidor.clear();
            tfDataAlta.clear();
            tfPes.clear();
            tfDataCaducitat.clear();
            tfQuantitat.clear();
            tfPreu.clear();

        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    @FXML
    public void btnDel_action(ActionEvent event) throws IOException {
        Referencia referencia = TabViewRef.getSelectionModel().getSelectedItem();

        if (referencia != null) {
            try {
                referenciaLogica.eliminarReferencia(referencia.getId());
                llistaObservableReferencia.remove(referencia);
                TabViewRef.setItems(llistaObservableReferencia);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se ha seleccionat cap referència per eliminar.");
        }
    }

    @FXML
    public void btnProd_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Productes' presionat");
        App.setRoot("menuProducte");
    }
}
