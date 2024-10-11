/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import aplicacio.App;
import aplicacio.model.Proveidor;
import aplicacio.model.Usuari;
import enums.EstatProveidor;
import excepcions.CifInvalid;
import excepcions.DescompteInvalid;
import excepcions.EstatInvalid;
import excepcions.MotiuInactiuInvalid;
import excepcions.NomBuit;
import excepcions.QualificacioInvalid;
import excepcions.TelefonInvalid;
import excepcions.dataAltaBuit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import logica.ProveidorLogica;

/**
 * Controlador per gestionar el menú de proveïdors.
 *
 * @author danie
 */
public class MenuProveidor {

    @FXML
    private TableView<Proveidor> tabViewProveidor;

    @FXML
    private TableColumn colNom, colCif, colMotiuInactiu, colTelefon, colEstat, colQualificacio, colDescompte, colDataAlta;

    @FXML
    private Button btnLogo, btnAfegir, btnMod, btnElimi, btnTancar, btnSortida, btnExportar, btnImportar;

    @FXML
    private TextField tfCif, tfNom, tfMotiuInactiu, tfTelefon, tfQualificacio, tfDescompte, tfDataAlta;

    @FXML
    private ComboBox<EstatProveidor> cbEstat;

    private ObservableList<Proveidor> llistaObservableProveidor;
    private ProveidorLogica proveidorLogica;
    private Usuari usuari;

    /**
     * Assigna un usuari al menú i gestiona els permisos.
     *
     * @param usuari Usuari que accedeix al menú.
     */
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
        gestionarPermisos();
    }

    /**
     * Inicialitza el menú de proveïdors.
     */
    @FXML
    public void initialize() {
        llistaObservableProveidor = FXCollections.observableArrayList();
        proveidorLogica = new ProveidorLogica();

        cbEstat.setItems(FXCollections.observableArrayList(EstatProveidor.values()));

        try {
            llistaObservableProveidor.addAll(proveidorLogica.obtenirTotsElsProveidors());
        } catch (Exception e) {
            e.printStackTrace();
        }

        colCif.setCellValueFactory(new PropertyValueFactory<>("CIF"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colEstat.setCellValueFactory(new PropertyValueFactory<>("Estat"));
        colMotiuInactiu.setCellValueFactory(new PropertyValueFactory<>("MotiuInactiu"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("Telèfon"));
        colDescompte.setCellValueFactory(new PropertyValueFactory<>("Descompte"));
        colDataAlta.setCellValueFactory(new PropertyValueFactory<>("Data_Alta"));
        colQualificacio.setCellValueFactory(new PropertyValueFactory<>("Qualificació"));

        tabViewProveidor.setItems(llistaObservableProveidor);
        tabViewProveidor.setOnMouseClicked(this::handleOnMouseClicked);

        desactivarBotons();
        btnAfegir.setDisable(false);
    }

    /**
     * Gestiona els permisos dels botons en funció del rol de l'usuari.
     */
    private void gestionarPermisos() {
        if (usuari != null) {
            boolean esMagatzem = usuari.isRol();
            btnAfegir.setDisable(!esMagatzem);
            btnMod.setDisable(!esMagatzem);
            btnElimi.setDisable(!esMagatzem);

        } else {
            desactivarBotons();
        }
    }

    /**
     * Desactiva els botons de modificar i eliminar.
     */
    private void desactivarBotons() {
        btnElimi.setDisable(true);
        btnMod.setDisable(true);
    }

    /**
     * Gestor d'esdeveniments per a fer clics en el TableView.
     *
     * @param ev Esdeveniment del mouse.
     */
    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        if (ev.getButton() == MouseButton.PRIMARY) {
            Proveidor proveidorSeleccionat = tabViewProveidor.getSelectionModel().getSelectedItem();

            if (proveidorSeleccionat != null) {
                tfNom.setText(proveidorSeleccionat.getNom());
                tfCif.setText(proveidorSeleccionat.getCIF());
                cbEstat.setValue(proveidorSeleccionat.getEstat());
                tfMotiuInactiu.setText(proveidorSeleccionat.getMotiuInactiu());
                tfTelefon.setText(proveidorSeleccionat.getTelefon());
                tfDescompte.setText(String.valueOf(proveidorSeleccionat.getDescompte()));
                tfDataAlta.setText(proveidorSeleccionat.getData_Alta().toString());
                tfQualificacio.setText(String.valueOf(proveidorSeleccionat.getQualificacio()));

                if (usuari != null && usuari.isRol()) {
                    btnElimi.setDisable(false);
                    btnMod.setDisable(false);
                } else {
                    btnElimi.setDisable(true);
                    btnMod.setDisable(true);
                }
            } else {
                desactivarBotons();
            }
        }
    }

    /**
     * Acció per al botó 'Logo'.
     *
     * @param event Esdeveniment d'acció.
     * @throws IOException Si hi ha un error d'entrada/sortida.
     */
    @FXML
    public void btnLogo_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Logo' presionat");
        App.setRoot("menuPrincipal", this.usuari);
    }

    /**
     * Acció per al botó 'Sortida'.
     *
     * @param event Esdeveniment d'acció.
     * @throws IOException Si hi ha un error d'entrada/sortida.
     */
    @FXML
    public void btnSortida_action(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal", this.usuari);
    }

    /**
     * Acció per al botó 'Tancar'.
     *
     * @param event Esdeveniment d'acció.
     * @throws IOException Si hi ha un error d'entrada/sortida.
     */
    @FXML
    public void btnTancar_action(ActionEvent event) throws IOException {
        App.setRoot("iniciSessio");
    }

    /**
     * Acció per al botó 'Afegir'.
     *
     * @param event Esdeveniment d'acció.
     */
    @FXML
    public void btnAfegir_action(ActionEvent event) {
        System.out.println("Botó 'Afegir' presionat");

        EstatProveidor estat = cbEstat.getValue();
        Proveidor nouProveidor = new Proveidor("", "", estat, "", "", 0.0f, LocalDate.now(), 0);

        llistaObservableProveidor.add(nouProveidor);

        tabViewProveidor.setItems(llistaObservableProveidor);
        tabViewProveidor.getSelectionModel().select(nouProveidor);
    }

    /**
     * Acció per al botó 'Modificar'.
     *
     * @param event Esdeveniment d'acció.
     * @throws IOException Si hi ha un error d'entrada/sortida.
     * @throws CifInvalid Excepció per un CIF no vàlid.
     * @throws NomBuit Excepció per un nom buit.
     * @throws TelefonInvalid Excepció per un telèfon no vàlid.
     * @throws QualificacioInvalid Excepció per una qualificació no vàlida.
     * @throws DescompteInvalid Excepció per un descompte no vàlid.
     * @throws EstatInvalid Excepció per un estat no vàlid.
     * @throws MotiuInactiuInvalid Excepció per un motiu inactiu no vàlid.
     * @throws dataAltaBuit Excepció per data alta buida.
     */
    @FXML
    public void btnMod_action(ActionEvent event) throws IOException, CifInvalid, NomBuit, EstatInvalid, MotiuInactiuInvalid,
            TelefonInvalid, DescompteInvalid, dataAltaBuit, QualificacioInvalid {

        System.out.println("Botó 'Modificar' presionat");
        Proveidor proveidorSeleccionat = (Proveidor) tabViewProveidor.getSelectionModel().getSelectedItem();

        if (proveidorSeleccionat != null) {
            try {
                String nomNou = tfNom.getText();
                if (nomNou.isEmpty()) {
                    throw new NomBuit("El nom del proveïdor no pot estar buit.");
                }
                String cifNou = tfCif.getText();
                if (cifNou == null || cifNou.trim().isEmpty()) {
                    throw new CifInvalid("El CIF no pot estar buit.");
                }
                EstatProveidor estatNou = cbEstat.getValue();
                if (estatNou == null) {
                    throw new EstatInvalid("Has de seleccionar un estat vàlid pel proveïdor");
                }
                if (estatNou == EstatProveidor.INACTIU && (tfMotiuInactiu.getText().isEmpty())) {
                    throw new MotiuInactiuInvalid("Ha de proporcionar un motiu d'inactivitat si el proveïdor està inactiu.");
                }
                String telefonNou = tfTelefon.getText();
                if (telefonNou.isEmpty() || !telefonNou.matches("\\d{9}")) {
                    throw new TelefonInvalid("El telèfon ha de tenir 9 dígits.");
                }
                String descompteStr = tfDescompte.getText();
                float descompteNou;
                if (descompteStr.isEmpty()) {
                    throw new DescompteInvalid("El descompte no pot estar buit.");
                }
                try {
                    descompteNou = Float.parseFloat(descompteStr);
                } catch (NumberFormatException e) {
                    throw new DescompteInvalid("El descompte ha de ser un número vàlid.");
                }
                String dataAltaStr = tfDataAlta.getText();
                if (dataAltaStr.isEmpty()) {
                    throw new dataAltaBuit("La data d'alta no pot estar buida.");
                }
                LocalDate dataAltaNova;
                try {
                    dataAltaNova = LocalDate.parse(dataAltaStr);
                } catch (Exception e) {
                    throw new dataAltaBuit("El format de la data d'alta no és vàlid (AAAA-MM-DD).");
                }
                String qualificacioStr = tfQualificacio.getText();
                int qualificacioNova;
                if (qualificacioStr.isEmpty()) {
                    throw new QualificacioInvalid("La qualificació no pot estar buida.");
                }
                try {
                    qualificacioNova = Integer.parseInt(qualificacioStr);
                } catch (NumberFormatException e) {
                    throw new QualificacioInvalid("La qualificació ha de ser un número enter vàlid.");
                }
                proveidorLogica.modificarProveidor(cifNou, nomNou, estatNou, tfMotiuInactiu.getText(),
                        telefonNou, descompteNou, dataAltaNova, qualificacioNova);

                proveidorSeleccionat.setCIF(cifNou);
                proveidorSeleccionat.setNom(nomNou);
                proveidorSeleccionat.setEstat(estatNou);
                proveidorSeleccionat.setMotiuInactiu(tfMotiuInactiu.getText());
                proveidorSeleccionat.setTelefon(telefonNou);
                proveidorSeleccionat.setDescompte(descompteNou);
                proveidorSeleccionat.setData_Alta(dataAltaNova);
                proveidorSeleccionat.setQualificacio(qualificacioNova);

                tabViewProveidor.refresh();
                System.out.println("Proveïdor modificat correctamente.");
            } catch (CifInvalid e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (NomBuit e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (EstatInvalid e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (MotiuInactiuInvalid e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (TelefonInvalid e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (DescompteInvalid e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (dataAltaBuit e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (QualificacioInvalid e) {
                MostrarError.mostrarMissatgeError("Error: ", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                MostrarError.mostrarMissatgeError("Error en modificar el proveïdor: ", e.getMessage());
            }
        } else {
            System.out.println("No s'ha seleccionat cap proveidor per a modificar.");
        }
    }

    /**
     * Acció per al botó 'Eliminar'.
     *
     * @param event Esdeveniment d'acció.
     * @throws IOException Si hi ha un error d'entrada/sortida.
     */
    @FXML
    public void btnElimi_action(ActionEvent event) throws Exception {
        Proveidor proveidorSeleccionat = tabViewProveidor.getSelectionModel().getSelectedItem();

        if (proveidorSeleccionat != null) {
            proveidorLogica.eliminarProveidor(proveidorSeleccionat.getCIF());
            llistaObservableProveidor.remove(proveidorSeleccionat);

            tabViewProveidor.refresh();
        }
    }

    /**
     * Acció per al botó 'Importar'.
     *
     * @param event Esdeveniment d'acció.
     * @throws IOException Si hi ha un error d'entrada/sortida.
     */
    @FXML
    public void btnImportar_action(ActionEvent event) {
        System.out.println("El botón 'Importar' ha sido presionado.");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar arxiu a importar");

        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Arxius CSV (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        Window stage = ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                importarArchivo(file);
            } catch (Exception e) {
                MostrarError.mostrarMissatgeError("Error de Importació", "S'ha produït un error durant la importació: " + e.getMessage());
            }
        } else {
            MostrarError.mostrarMissatgeError("Arxiu no seleccionat", "No s'ha seleccionat cap arxiu per importar.");
        }
    }

    /**
     * Acció per al botó 'Exportar'.
     *
     * @param event Esdeveniment d'acció.
     * @throws IOException Si hi ha un error d'entrada/sortida.
     */
    @FXML
    public void btnExportar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Exportar' presionat");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Proveïdors");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                // Escribir encabezados
                bw.write("CIF,Nom,Estat,Motiu Inactiu,Telefon,Descompte,Data Alta,Qualificació");
                bw.newLine();

                for (Proveidor proveidor : llistaObservableProveidor) {
                    String line = String.join(",",
                            proveidor.getCIF(),
                            proveidor.getNom(),
                            proveidor.getEstat().name(),
                            proveidor.getMotiuInactiu(),
                            proveidor.getTelefon(),
                            String.valueOf(proveidor.getDescompte()),
                            proveidor.getData_Alta().toString(),
                            String.valueOf(proveidor.getQualificacio())
                    );
                    bw.write(line);
                    bw.newLine();
                }

                System.out.println("Exportació completada. Proveïdors exportats: " + llistaObservableProveidor.size());
            } catch (IOException e) {
                e.printStackTrace();
                MostrarError.mostrarMissatgeError("Error al escriure l'arxiu: ", e.getMessage());
            }
        }
    }

    /**
     * Importa proveïdors des d'un fitxer CSV especificat.
     *
     * Llegeix les dades del fitxer, processa cada línia per crear objectes
     * {@link Proveidor} i afegeix aquests objectes a la llista observable de
     * proveïdors. Si es troben errors en les dades (com formats incorrectes o
     * valors invàlids), es mostraran missatges d'error.
     *
     * @param filePath El fitxer CSV que conté la informació dels proveïdors a
     * importar. Ha de tenir el format correcte amb les següents columnes: CIF,
     * Nom, Estat, Motiu Inactiu, Telèfon, Descompte, Data Alta, Qualificació.
     *
     * @throws IOException Si hi ha un error d'entrada/sortida durant la lectura
     * del fitxer.
     *
     * @throws Exception Si es produeix un error inesperat durant el procés
     * d'importació.
     */
    public void importarArchivo(File filePath) {

        List<Proveidor> proveidorsImportats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");

                if (datos.length >= 8) {
                    String cif = datos[0].trim();
                    String nom = datos[1].trim();

                    EstatProveidor estat;
                    try {
                        estat = EstatProveidor.valueOf(datos[2].trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        MostrarError.mostrarMissatgeError("Valor d'estat invàlid: ", datos[2]);
                        continue;
                    }

                    String motiuInactiu = datos[3].trim();
                    String telefon = datos[4].trim();
                    float descompte;
                    try {
                        descompte = Float.parseFloat(datos[5].trim());
                    } catch (NumberFormatException e) {
                        MostrarError.mostrarMissatgeError("Descompte invàlid per al CIF", cif + ": " + datos[5]);
                        continue;
                    }

                    LocalDate dataAlta;
                    try {
                        dataAlta = LocalDate.parse(datos[6].trim());
                    } catch (Exception e) {
                        MostrarError.mostrarMissatgeError("Data d'alta invàlida per al CIF ", cif + ": " + datos[6]);
                        continue;
                    }

                    int qualificacio;
                    try {
                        qualificacio = Integer.parseInt(datos[7].trim());
                    } catch (NumberFormatException e) {
                        MostrarError.mostrarMissatgeError("Qualificació invàlida per al CIF ", cif + ": " + datos[7]);
                        continue;
                    }

                    Proveidor nouProveidor = new Proveidor(cif, nom, estat, motiuInactiu, telefon, descompte, dataAlta, qualificacio);
                    proveidorsImportats.add(nouProveidor);
                } else {
                    System.out.println("Fila amb dades insuficients: " + line);
                }
            }

            for (Proveidor proveidor : proveidorsImportats) {
                try {
                    proveidorLogica.afegirProveidor(proveidor.getCIF(), proveidor.getNom(),
                            proveidor.getEstat(), proveidor.getMotiuInactiu(),
                            proveidor.getTelefon(), proveidor.getDescompte(),
                            proveidor.getData_Alta(), proveidor.getQualificacio());
                } catch (Exception e) {
                    MostrarError.mostrarMissatgeError("Error a l'afegir proveïdor", "No s'ha pogut afegir el proveïdor amb CIF " + proveidor.getCIF() + ": " + e.getMessage());
                }
            }

            llistaObservableProveidor.addAll(proveidorsImportats);
            tabViewProveidor.setItems(llistaObservableProveidor);

            System.out.println("Importació completada. Proveïdors afegits:" + proveidorsImportats.size());

        } catch (IOException e) {
            e.printStackTrace();
            MostrarError.mostrarMissatgeError("Error al llegir l'arxiu: ", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            MostrarError.mostrarMissatgeError("Error inesperat: ", e.getMessage());
        }
    }

}
