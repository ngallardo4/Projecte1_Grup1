package presentacio;

import aplicacio.App;
import aplicacio.model.Familia;
import aplicacio.model.Usuari;
import excepcions.NomBuit;
import excepcions.cifProveidorBuit;
import excepcions.dataAltaBuit;
import excepcions.descripcioBuit;
import excepcions.observacionsBuit;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private Usuari usuari;  // Variable para guardar el usuario autenticado

    /**
     * Estableix l'usuari autenticat per al sistema i gestiona els permisos
     * segons el rol de l'usuari.
     *
     * @param usuari L'usuari autenticat.
     */
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
        gestionarPermisos();
    }

    /**
     * Inicialitza el controlador de la vista de famílies. Configura el
     * TableView, recupera les famílies de la base de dades i gestiona els
     * permisos de l'usuari.
     *
     * @throws IOException Si hi ha un problema en carregar la vista.
     */
    @FXML
    public void initialize() throws IOException {
        llistaObservableFamilia = FXCollections.observableArrayList();
        familiaLogica = new FamiliaLogica();

        try {
            llistaObservableFamilia.addAll(familiaLogica.obtenirTotesLesFamilies());
        } catch (Exception e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_alta"));
        colProv.setCellValueFactory(new PropertyValueFactory<>("prov_defecte"));
        colObvs.setCellValueFactory(new PropertyValueFactory<>("observacions"));

        TabViewFam.setItems(llistaObservableFamilia);
        TabViewFam.setOnMouseClicked(this::handleOnMouseClicked);

        gestionarPermisos();
    }

    /**
     * Gestiona els permisos de l'usuari actual per habilitar o deshabilitar
     * botons segons el seu rol.
     */
    private void gestionarPermisos() {
        if (usuari != null) {
            boolean esMagatzem = usuari.isRol();
            btnAfegir.setDisable(!esMagatzem);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        } else {
            desactivarBotons();
        }
    }

    /**
     * Desactiva tots els botons d'accions com afegir, eliminar o modificar per
     * evitar operacions no permeses.
     */
    private void desactivarBotons() {
        btnAfegir.setDisable(true);
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
    }

    /**
     * Gestiona l'event de selecció d'una família en el TableView. Activa els
     * botons d'acció segons els permisos de l'usuari.
     *
     * @param ev L'esdeveniment de clic del ratolí.
     */
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

                if (usuari != null && usuari.isRol()) {
                    btnEliminar.setDisable(false);
                    btnModificar.setDisable(false);
                } else {
                    btnEliminar.setDisable(true);
                    btnModificar.setDisable(true);
                }

                btnProducte.setDisable(false);
            } else {
                desactivarBotons();
            }
        } else {
            System.out.println("No s'ha seleccionat cap filera.");
            desactivarBotons();
        }
    }

    /**
     * Canvia a la vista del menú principal quan es prem el botó 'Logo'.
     *
     * @param event L'esdeveniment associat a l'acció del botó.
     * @throws IOException Si hi ha un problema en carregar la nova vista.
     */
    @FXML
    public void btnLogo_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Logo' presionat");
        App.setRoot("menuPrincipal", usuari);
    }

    /**
     * Canvia a la vista d'inici de sessió quan es prem el botó 'Tancar'.
     *
     * @param event L'esdeveniment associat a l'acció del botó.
     * @throws IOException Si hi ha un problema en carregar la nova vista.
     */
    @FXML
    public void btnTancar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Tancar' presionat");
        App.setRoot("iniciSessio");
    }

    /**
     * Torna al menú principal quan es prem el botó 'Sortir'.
     *
     * @param event L'esdeveniment associat a l'acció del botó.
     * @throws IOException Si hi ha un problema en carregar la nova vista.
     */
    @FXML
    public void btnSortir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Sortir' presionat");
        App.setRoot("menuPrincipal", usuari);
    }

    /**
     * Afegeix una nova família buida al TableView i la selecciona per a la seva
     * edició.
     *
     * @param event L'esdeveniment associat a l'acció del botó.
     * @throws IOException Si hi ha un problema en actualitzar la vista.
     */
    @FXML
    public void btnAfegir_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Nova' presionat");
        Familia novaFamilia = new Familia(0, "", "", LocalDate.now(), "", "");

        llistaObservableFamilia.add(novaFamilia);

        TabViewFam.setItems(llistaObservableFamilia);
        TabViewFam.getSelectionModel().select(novaFamilia);
    }

    /**
     * Modifica la família seleccionada amb els valors introduïts en els camps
     * de text. Gestiona les excepcions si algun camp necessari està buit.
     *
     * @param event L'esdeveniment associat a l'acció del botó.
     * @throws IOException Si hi ha un problema en modificar la família.
     * @throws NomBuit Si el nom de la família està buit.
     * @throws descripcioBuit Si la descripció està buida.
     * @throws dataAltaBuit Si la data d'alta està buida o té format incorrecte.
     * @throws cifProveidorBuit Si el CIF del proveïdor està buit.
     * @throws observacionsBuit Si les observacions estan buides.
     */
    @FXML
    public void btnModificar_action(ActionEvent event) throws IOException, NomBuit, descripcioBuit, dataAltaBuit, cifProveidorBuit, observacionsBuit {
        System.out.println("Botó 'Modificar' presionat");
        Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                String nomNou = tf_nomFam.getText();
                if (nomNou.isEmpty()) {
                    throw new NomBuit("El nom de la família no pot estar buit.");
                }
                String descripcioNova = tf_desFam.getText();
                if (descripcioNova.isEmpty()) {
                    throw new descripcioBuit("La descripció de la família no pot estar buida.");
                }
                String dataAltaStr = tf_dataltaFam.getText();
                if (dataAltaStr.isEmpty()) {
                    throw new dataAltaBuit("La data d'alta no pot estar buida.");
                }
                LocalDate dataAltaNova;
                try {
                    dataAltaNova = LocalDate.parse(dataAltaStr);
                } catch (Exception e) {
                    throw new dataAltaBuit("Format de data d'alta no valida.");
                }
                String provDefecteNou = tf_provFam.getText();
                if (provDefecteNou.isEmpty()) {
                    throw new cifProveidorBuit("El CIF del proveïdor no pot estar buit.");
                }
                String observacionsNovas = tf_obvsFam.getText();
                if (observacionsNovas.isEmpty()) {
                    throw new observacionsBuit("Les observacions no poden estar buides.");
                }

                if (familiaSeleccionada.getId() == 0) {
                    familiaLogica.afegirFamilia(nomNou, descripcioNova, dataAltaNova, provDefecteNou, observacionsNovas);
                    System.out.println("Nova família afegida amb ID: " + familiaSeleccionada.getId());
                } else {
                    familiaLogica.modificarFamilia(familiaSeleccionada.getId(), nomNou, descripcioNova, dataAltaNova, provDefecteNou, observacionsNovas);
                    System.out.println("Família modificada correctament.");
                }

                familiaSeleccionada.setNom(nomNou);
                familiaSeleccionada.setDescripcio(descripcioNova);
                familiaSeleccionada.setData_alta(dataAltaNova);
                familiaSeleccionada.setProv_defecte(provDefecteNou);
                familiaSeleccionada.setObservacions(observacionsNovas);

                TabViewFam.refresh();
                TabViewFam.setItems(llistaObservableFamilia);

                System.out.println("Familia modificada correctament.");

            } catch (NomBuit e) {
                System.out.println("Error: " + e.getMessage());
                MostrarError.mostrarMissatgeError("Nom buit", e.getMessage());
            } catch (descripcioBuit e) {
                System.out.println("Error: " + e.getMessage());
                MostrarError.mostrarMissatgeError("Descripció buida", e.getMessage());
            } catch (dataAltaBuit e) {
                System.out.println("Error: " + e.getMessage());
                MostrarError.mostrarMissatgeError("Data alta buida", e.getMessage());
            } catch (cifProveidorBuit e) {
                System.out.println("Error: " + e.getMessage());
                MostrarError.mostrarMissatgeError("CIF Proveïdor buit", e.getMessage());
            } catch (observacionsBuit e) {
                System.out.println("Error: " + e.getMessage());
                MostrarError.mostrarMissatgeError("Observacions buit", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                MostrarError.mostrarMissatgeError("Error en modificar la família", e.getMessage());
            }
        } else {
            System.out.println("No s'ha seleccionat cap família per modificar.");
        }

    }

    /**
     * Elimina la família seleccionada tant de la base de dades com del
     * TableView.
     *
     * @param event L'esdeveniment associat a l'acció del botó.
     * @throws IOException Si hi ha un problema en eliminar la família.
     */
    @FXML
    public void btnEliminar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Eliminar' presionat");
        Familia familiaSeleccionada = (Familia) TabViewFam.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                familiaLogica.eliminarFamilia(familiaSeleccionada.getId());
                System.out.println("Familia eliminada de la base de dades.");

                llistaObservableFamilia.remove(familiaSeleccionada);
                TabViewFam.setItems(llistaObservableFamilia);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No s'ha seleccionat cap família per eliminar.");
        }
    }

    /**
     * Canvia a la vista de gestió de productes quan es prem el botó
     * 'Productes'.
     *
     * @param event L'esdeveniment associat a l'acció del botó.
     * @throws IOException Si hi ha un problema en carregar la nova vista.
     */
    @FXML
    public void btnProducte_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Productes' presionat");
        App.setRoot("menuReferencia", usuari);
    }
}
