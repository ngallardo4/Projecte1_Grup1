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
import aplicacio.model.Usuari;
import enums.UnitatMesura;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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
import logica.ReferenciaLogica;

public class MenuReferencia {
// COMENTARIO

    @FXML
    private TableView tabViewRef;

    @FXML
    private TableColumn colId, colNom, colUOM, colFamilia, colProveidor, colDataAlta, colPes, colDataCaducitat, colQuantitat, colPreu;

    @FXML
    private Button btnLogo, btnTancarSessio, btnIdFamilia, btnAfegir, btnMod, btnElimi, btnEstoc, btnSortida;

    @FXML
    private TextField tfIdFam, tfId, tfNom, tfIdFamilia, tfCifProveidor, tfDataAlta, tfPes, tfDataCaducitat, tfQuantitat, tfPreu;

    @FXML
    private ComboBox<UnitatMesura> cbUOM;

    private ObservableList<Referencia> llistaObservableReferencia;
    private ReferenciaLogica referenciaLogica;

    private boolean estocMode = false;

    private int familiaId;

    public int getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(int familiaId) {
        this.familiaId = familiaId;
    }

    private Usuari usuari;  // Variable para guardar el usuario autenticado

    // Método para establecer el usuario autenticado
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;  // Guardar el usuario
        gestionarPermisos();    // Gestionar los permisos según el rol
    }

    // Mètodes per gestionar els esdeveniments dels botons
    @FXML
    public void initialize() {
        llistaObservableReferencia = FXCollections.observableArrayList();
        referenciaLogica = new ReferenciaLogica();

        cbUOM.setItems(FXCollections.observableArrayList(UnitatMesura.values()));

        try {
            llistaObservableReferencia.addAll(referenciaLogica.obtenirTotesLesReferencies(familiaId));
        } catch (Exception e) {
            e.printStackTrace(); // Maneig d'errors, si cal
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colUOM.setCellValueFactory(new PropertyValueFactory<>("uom"));
        colFamilia.setCellValueFactory(new PropertyValueFactory<>("id_familia"));
        colProveidor.setCellValueFactory(new PropertyValueFactory<>("cif_proveidor"));
        colDataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta"));
        colPes.setCellValueFactory(new PropertyValueFactory<>("pes_total"));
        colDataCaducitat.setCellValueFactory(new PropertyValueFactory<>("data_caducitat"));
        colQuantitat.setCellValueFactory(new PropertyValueFactory<>("quantitat_total"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu_total"));

        // Assignar event al TableView per seleccionar família
        tabViewRef.setItems(llistaObservableReferencia);
        tabViewRef.setOnMouseClicked(this::handleOnMouseClicked);

        // Desactivar botons al principi
        gestionarPermisos();
    }

    private void gestionarPermisos() {
        if (usuari != null) {
            boolean esMagatzem = usuari.isRol();
            btnAfegir.setDisable(!esMagatzem);
            btnMod.setDisable(true); // Iniciar deshabilitado hasta que se seleccione una familia
            btnElimi.setDisable(true);  // Iniciar deshabilitado hasta que se seleccione una familia
        } else {
            desactivarBotons();
        }
    }

    private void desactivarBotons() {
        btnAfegir.setDisable(true);
        btnElimi.setDisable(true);
        btnMod.setDisable(true);
    }

    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        if (ev.getButton() == MouseButton.PRIMARY) {
            Referencia referenciaSeleccionada = (Referencia) tabViewRef.getSelectionModel().getSelectedItem();

            if (referenciaSeleccionada != null) {
                System.out.println("Referencia seleccionada: " + referenciaSeleccionada.toString());
                tfId.setText(String.valueOf(referenciaSeleccionada.getId()));
                tfNom.setText(referenciaSeleccionada.getNom());
                cbUOM.setValue(referenciaSeleccionada.getUom());
                tfIdFamilia.setText(String.valueOf(referenciaSeleccionada.getId_familia()));
                tfCifProveidor.setText(referenciaSeleccionada.getCif_proveidor());
                tfDataAlta.setText(referenciaSeleccionada.getData_alta().toString());
                tfPes.setText(String.valueOf(referenciaSeleccionada.getPes_total()));
                tfDataCaducitat.setText(referenciaSeleccionada.getData_caducitat().toString());
                tfQuantitat.setText(String.valueOf(referenciaSeleccionada.getQuantitat_total()));
                tfPreu.setText(String.valueOf(referenciaSeleccionada.getPreu_total()));

                // Verificar los permisos del usuario antes de habilitar los botones
                if (usuari != null && usuari.isRol()) {
                    btnElimi.setDisable(false);  // Solo habilitar si tiene permisos
                    btnMod.setDisable(false); // Solo habilitar si tiene permisos
                } else {
                    btnElimi.setDisable(true);   // Deshabilitar si no tiene permisos
                    btnMod.setDisable(true);  // Deshabilitar si no tiene permisos
                }

                btnEstoc.setDisable(false); // El botón de productos siempre habilitado
            } else {
                desactivarBotons();
            }
        } else {
            System.out.println("No se seleccionó ninguna fila.");
            desactivarBotons();
        }
    }

    @FXML
    public void btnIdFamilia_action(ActionEvent event) {
        try {
            // Obtener el ID de familia desde el campo de texto
            int idFamilia = Integer.parseInt(tfIdFam.getText());

            // Obtener las referencias filtradas por familia
            List<Referencia> referenciesFiltrades = referenciaLogica.obtenirTotesLesReferencies(idFamilia);

            // Limpiar la lista observable y agregar las referencias filtradas
            llistaObservableReferencia.clear();
            llistaObservableReferencia.addAll(referenciesFiltrades);

            // Refrescar el TableView
            tabViewRef.refresh();
            
        } catch (NumberFormatException e) {
            System.out.println("ID familia ha de ser un número.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnSortida_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Sortir' presionat");
        App.setRoot("menuPrincipal", usuari);
    }

    @FXML
    public void btnTancar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Tancar' presionat");
        App.setRoot("iniciSessio");
    }

    @FXML
    public void btnAfegir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Afegir' presionat");

        // Obtener el valor seleccionado del ComboBox
        UnitatMesura uomSeleccionada = cbUOM.getValue();

        // Crear una nova referència amb el valor seleccionat
        Referencia novaReferencia = new Referencia(0, "", uomSeleccionada, 0, "", LocalDate.now(), 0.0f, LocalDate.now(), 0, 0.0f);

        // Afegir la nova referència a la llista observable
        llistaObservableReferencia.add(novaReferencia);

        // Actualitzar el TableView
        tabViewRef.setItems(llistaObservableReferencia);
        tabViewRef.getSelectionModel().select(novaReferencia);
    }

    @FXML
    public void btnMod_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Modificar' presionat");
        Referencia referenciaSeleccionada = (Referencia) tabViewRef.getSelectionModel().getSelectedItem();

        if (referenciaSeleccionada != null) {
            try {
                String nomNou = tfNom.getText();
                UnitatMesura uomNova = cbUOM.getValue();  // Obtener el valor del ComboBox para UOM
                int idFamiliaNou = Integer.parseInt(tfIdFamilia.getText());
                String cifProveidorNou = tfCifProveidor.getText();
                LocalDate dataAltaNova = LocalDate.parse(tfDataAlta.getText());
                float pesNou = Float.parseFloat(tfPes.getText());
                LocalDate dataCaducitatNova = LocalDate.parse(tfDataCaducitat.getText());
                int quantitatNova = Integer.parseInt(tfQuantitat.getText());
                float preuNou = Float.parseFloat(tfPreu.getText());

                if (referenciaSeleccionada.getId() == 0) {
                    // Si l'ID és 0 és una nova família llavors inserim
                    referenciaLogica.afegirReferencia(nomNou, uomNova, idFamiliaNou, cifProveidorNou, dataAltaNova, pesNou, dataCaducitatNova, quantitatNova, preuNou);
                    // Actualitzem l'objecte seleccionat per fer-lo coincidir amb la nova família
                    System.out.println("Nova família afegida amb ID: " + referenciaSeleccionada.getId());
                } else {
                    // Si l'ID és diferent de 0 ja existeix i fem una actualització
                    referenciaLogica.modificarReferencia(referenciaSeleccionada.getId(), nomNou, uomNova, idFamiliaNou, cifProveidorNou, dataAltaNova, pesNou, dataCaducitatNova, quantitatNova, preuNou);
                    System.out.println("Família modificada correctament.");
                }

                // Actualizar els valors de la familia a la llista observable (TableView)
                referenciaSeleccionada.setNom(nomNou);
                referenciaSeleccionada.setUom(uomNova);
                referenciaSeleccionada.setId_familia(idFamiliaNou);
                referenciaSeleccionada.setCif_proveidor(cifProveidorNou);
                referenciaSeleccionada.setData_alta(dataAltaNova);
                referenciaSeleccionada.setPes_total(pesNou);
                referenciaSeleccionada.setData_caducitat(dataCaducitatNova);
                referenciaSeleccionada.setQuantitat_total(quantitatNova);
                referenciaSeleccionada.setPreu_total(preuNou);

                // Refrescar el TableView per mostrar els canvis
                tabViewRef.refresh();
                tabViewRef.setItems(llistaObservableReferencia);

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
    public void btnElimi_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Eliminar' presionat");
        Referencia referenciaSeleccionada = (Referencia) tabViewRef.getSelectionModel().getSelectedItem();

        if (referenciaSeleccionada != null) {
            try {
                // Eliminar de la base de dades
                referenciaLogica.eliminarReferencia(referenciaSeleccionada.getId());
                System.out.println("Familia eliminada de la base de dades.");

                // Eliminar de la llista observable
                llistaObservableReferencia.remove(referenciaSeleccionada);
                tabViewRef.setItems(llistaObservableReferencia);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No s'ha seleccionat cap família per eliminar.");
        }
    }

    @FXML
    public void btnEstoc_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Estoc' presionat");

        if (!estocMode) {
            // Mostrar solo productos sin stock
            try {
                List<Referencia> referenciesSenseEstoc = referenciaLogica.obtenirReferenciesSenseEstoc();
                llistaObservableReferencia.clear();
                llistaObservableReferencia.addAll(referenciesSenseEstoc);
                tabViewRef.refresh();
                tabViewRef.setItems(llistaObservableReferencia);
                estocMode = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Restaurar la lista completa
            try {
                List<Referencia> totesLesReferencies = referenciaLogica.obtenirTotesLesReferencies(familiaId);
                llistaObservableReferencia.clear();
                llistaObservableReferencia.addAll(totesLesReferencies);
                tabViewRef.refresh();
                tabViewRef.setItems(llistaObservableReferencia);
                estocMode = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void btnLogo_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Logo' presionat");
        App.setRoot("menuPrincipal", usuari);
    }

}
