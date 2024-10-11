/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 * Classe per gestionar les referències en l'aplicació. Aquesta classe maneja
 * els esdeveniments de la interfície d'usuari per a la gestió de referències,
 * com afegir, modificar i eliminar referències, així com la seva visualització.
 * També gestiona els permisos dels usuaris.
 *
 * @author Héctor Vico
 */
import aplicacio.App;
import aplicacio.model.Referencia;
import aplicacio.model.Usuari;
import enums.UnitatMesura;
import excepcions.IdFamiliaBuit;
import excepcions.NomBuit;
import excepcions.UomBuit;
import excepcions.cifProveidorBuit;
import excepcions.dataAltaBuit;
import excepcions.dataCaducitatBuit;
import excepcions.pesTotalBuit;
import excepcions.preuTotalBuit;
import excepcions.quantitatTotalBuit;
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
import javafx.scene.control.Alert;

public class MenuReferencia {

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

    /**
     * Obté l'ID de la família actualment seleccionada.
     *
     * @return ID de la família.
     */
    public int getFamiliaId() {
        return familiaId;
    }

    /**
     * Estableix l'ID de la família per carregar les referències.
     *
     * @param familiaId ID de la família.
     */
    public void setFamiliaId(int familiaId) {
        this.familiaId = familiaId;
    }

    private Usuari usuari;

    /**
     * Estableix l'usuari que està operant i gestiona els permisos en funció del
     * seu rol.
     *
     * @param usuari Usuari que està utilitzant l'aplicació.
     */
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
        gestionarPermisos();
    }

    /**
     * Inicialitza el controlador de la interfície, carregant les referències de
     * la base de dades i assignant els valors als camps de la taula.
     */
    @FXML
    public void initialize() {
        llistaObservableReferencia = FXCollections.observableArrayList();
        referenciaLogica = new ReferenciaLogica();

        cbUOM.setItems(FXCollections.observableArrayList(UnitatMesura.values()));

        try {
            llistaObservableReferencia.addAll(referenciaLogica.obtenirTotesLesReferencies(familiaId));
        } catch (Exception e) {
            e.printStackTrace();
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

        tabViewRef.setItems(llistaObservableReferencia);
        tabViewRef.setOnMouseClicked(this::handleOnMouseClicked);

        gestionarPermisos();
    }

    /**
     * Gestiona els permisos d'usuari per habilitar o deshabilitar botons
     * d'acord amb el seu rol.
     */
    private void gestionarPermisos() {
        if (usuari != null) {
            boolean esMagatzem = usuari.isRol();
            btnAfegir.setDisable(!esMagatzem);
            btnMod.setDisable(true);
            btnElimi.setDisable(true);
        } else {
            desactivarBotons();
        }
    }

    /**
     * Desactiva tots els botons d'acció de la interfície.
     */
    private void desactivarBotons() {
        btnAfegir.setDisable(true);
        btnElimi.setDisable(true);
        btnMod.setDisable(true);
    }

    /**
     * Gestiona l'esdeveniment de clic en la taula, omplint els camps de la
     * interfície amb les dades de la referència seleccionada i habilitant o
     * deshabilitant botons segons els permisos de l'usuari.
     *
     * @param ev Esdeveniment de clic de ratolí.
     */
    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        if (ev.getButton() == MouseButton.PRIMARY) {
            Referencia referenciaSeleccionada = (Referencia) tabViewRef.getSelectionModel().getSelectedItem();

            if (referenciaSeleccionada != null) {
                System.out.println("Referencia seleccionada: " + referenciaSeleccionada.toString());

                if (estocMode) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información del Proveidor");
                    alert.setHeaderText("Referencia seleccionada");
                    alert.setContentText("El CIF del proveidor es: " + referenciaSeleccionada.getCif_proveidor());
                    alert.showAndWait();  // Mostrar el popup y esperar que el usuario lo cierre
                }

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

                // Verificar els permisos del usuari abants d'habilitar els botones
                if (usuari != null && usuari.isRol()) {
                    btnElimi.setDisable(false);
                    btnMod.setDisable(false);
                } else {
                    btnElimi.setDisable(true);
                    btnMod.setDisable(true);
                }

                btnEstoc.setDisable(false);
            } else {
                desactivarBotons();
            }
        } else {
            System.out.println("No se seleccionó ninguna fila.");
            desactivarBotons();
        }
    }

    /**
     * Acció del botó per filtrar les referències per ID de família.
     * 
     * @param event Esdeveniment d'acció del botó.
     */
    @FXML
    public void btnIdFamilia_action(ActionEvent event) {
        try {
            int idFamilia = Integer.parseInt(tfIdFam.getText());

            List<Referencia> referenciesFiltrades = referenciaLogica.obtenirTotesLesReferencies(idFamilia);

            llistaObservableReferencia.clear();
            llistaObservableReferencia.addAll(referenciesFiltrades);

            tabViewRef.refresh();
            tabViewRef.setItems(llistaObservableReferencia);

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
    public void btnAfegir_action(ActionEvent event) throws IOException, NomBuit, UomBuit {
        System.out.println("Botó 'Afegir' presionat");

        UnitatMesura uomSeleccionada = cbUOM.getValue();

        Referencia novaReferencia = new Referencia(0, "", uomSeleccionada, 0, "", LocalDate.now(), 0.0f, LocalDate.now(), 0, 0.0f);

        llistaObservableReferencia.add(novaReferencia);

        tabViewRef.setItems(llistaObservableReferencia);
        tabViewRef.getSelectionModel().select(novaReferencia);
    }

    @FXML
    public void btnMod_action(ActionEvent event) throws IOException, NomBuit, UomBuit, IdFamiliaBuit, cifProveidorBuit, dataAltaBuit,
            pesTotalBuit, dataCaducitatBuit, quantitatTotalBuit, preuTotalBuit {

        System.out.println("Botó 'Modificar' presionat");
        Referencia referenciaSeleccionada = (Referencia) tabViewRef.getSelectionModel().getSelectedItem();

        if (referenciaSeleccionada != null) {
            try {
                String nomNou = tfNom.getText();
                if (nomNou.isEmpty()) {
                    throw new NomBuit("El nom de la referencia no pot estar buit.");
                }

                UnitatMesura uomNova = cbUOM.getValue();
                if (uomNova == null) {
                    throw new UomBuit("Has de seleccionar una unitat de mesura (UOM).");
                }

                String idFamiliaStr = tfIdFamilia.getText();
                if (idFamiliaStr.isEmpty()) {
                    throw new IdFamiliaBuit("L'ID de la família no pot estar buit.");
                }

                int idFamiliaNou;
                try {
                    idFamiliaNou = Integer.parseInt(idFamiliaStr);
                } catch (NumberFormatException e) {
                    throw new IdFamiliaBuit("L'ID de la família ha de ser un número enter.");
                }

                String cifProveidorNou = tfCifProveidor.getText();
                if (cifProveidorNou.isEmpty()) {
                    throw new cifProveidorBuit("El CIF del proveïdor no pot estar buit.");
                }

                String dataAltaStr = tfDataAlta.getText();
                if (dataAltaStr.isEmpty()) {
                    throw new dataAltaBuit("La data d'alta no pot estar buida.");
                }

                LocalDate dataAltaNova;
                try {
                    dataAltaNova = LocalDate.parse(dataAltaStr);
                } catch (Exception e) {
                    throw new dataAltaBuit("Format de data d'alta no valida.");
                }

                String pesTotalStr = tfPes.getText();
                if (pesTotalStr.isEmpty()) {
                    throw new pesTotalBuit("El pes total no pot estar buit.");
                }

                // Convertir el peso a float
                float pesNou;
                try {
                    pesNou = Float.parseFloat(pesTotalStr);
                } catch (NumberFormatException e) {
                    throw new pesTotalBuit("El format del pes total és invalit.");
                }

                String dataCaducitatStr = tfDataCaducitat.getText();
                if (dataCaducitatStr.isEmpty()) {
                    throw new dataCaducitatBuit("La data de caducitat no pot estar buida.");
                }

                LocalDate dataCaducitatNova;
                try {
                    dataCaducitatNova = LocalDate.parse(dataCaducitatStr);
                } catch (Exception e) {
                    throw new dataCaducitatBuit("Format de data de caducitat no valida.");
                }

                String quantitatTotalStr = tfQuantitat.getText();
                if (quantitatTotalStr.isEmpty()) {
                    throw new quantitatTotalBuit("La quantitat total no pot estar buida.");
                }

                // Convertir la cantidad a int
                int quantitatNova;
                try {
                    quantitatNova = Integer.parseInt(quantitatTotalStr);
                } catch (NumberFormatException e) {
                    throw new quantitatTotalBuit("El format de la quantitat total es invalit.");
                }

                String preuTotalStr = tfPreu.getText();
                if (preuTotalStr.isEmpty()) {
                    throw new preuTotalBuit("El preu total no pot estar buit.");
                }

                float preuNou;
                try {
                    preuNou = Float.parseFloat(preuTotalStr);
                } catch (NumberFormatException e) {
                    throw new preuTotalBuit("El format del preu total es invalit.");
                }

                if (referenciaSeleccionada.getId() == 0) {
                    referenciaLogica.afegirReferencia(nomNou, uomNova, idFamiliaNou, cifProveidorNou, dataAltaNova, pesNou, dataCaducitatNova, quantitatNova, preuNou);
                    System.out.println("Nova família afegida amb ID: " + referenciaSeleccionada.getId());
                } else {
                    referenciaLogica.modificarReferencia(referenciaSeleccionada.getId(), nomNou, uomNova, idFamiliaNou, cifProveidorNou, dataAltaNova, pesNou, dataCaducitatNova, quantitatNova, preuNou);
                    System.out.println("Família modificada correctament.");
                }

                referenciaSeleccionada.setNom(nomNou);
                referenciaSeleccionada.setUom(uomNova);
                referenciaSeleccionada.setId_familia(idFamiliaNou);
                referenciaSeleccionada.setCif_proveidor(cifProveidorNou);
                referenciaSeleccionada.setData_alta(dataAltaNova);
                referenciaSeleccionada.setPes_total(pesNou);
                referenciaSeleccionada.setData_caducitat(dataCaducitatNova);
                referenciaSeleccionada.setQuantitat_total(quantitatNova);
                referenciaSeleccionada.setPreu_total(preuNou);

                tabViewRef.refresh();
                tabViewRef.setItems(llistaObservableReferencia);

                System.out.println("Familia modificada correctament.");

            } catch (NomBuit e) {
                MostrarError.mostrarMissatgeError("Nom Buit", e.getMessage());
            } catch (UomBuit e) {
                MostrarError.mostrarMissatgeError("Uom Buit", e.getMessage());
            } catch (IdFamiliaBuit e) {
                MostrarError.mostrarMissatgeError("IdFamilia Buit", e.getMessage());
            } catch (cifProveidorBuit e) {
                MostrarError.mostrarMissatgeError("cifProveïdor Buit", e.getMessage());
            } catch (dataAltaBuit e) {
                MostrarError.mostrarMissatgeError("dataAlta Buit", e.getMessage());
            } catch (dataCaducitatBuit e) {
                MostrarError.mostrarMissatgeError("dataCaducitat Buit", e.getMessage());
            } catch (pesTotalBuit e) {
                MostrarError.mostrarMissatgeError("pesTotal Buit", e.getMessage());
            } catch (quantitatTotalBuit e) {
                MostrarError.mostrarMissatgeError("quantitatTotal Buit", e.getMessage());
            } catch (preuTotalBuit e) {
                MostrarError.mostrarMissatgeError("preuTotal Buit", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                MostrarError.mostrarMissatgeError("Error", "Error en modificar la referencia: " + e.getMessage());
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
                // Elimina de la base de dades
                referenciaLogica.eliminarReferencia(referenciaSeleccionada.getId());
                System.out.println("Familia eliminada de la base de dades.");

                // Elimina de la llista observable
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
