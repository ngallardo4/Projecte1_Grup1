/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import aplicacio.App;
import aplicacio.model.Proveidor;
import aplicacio.model.Usuari;
import enums.EstatProveidor;
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
    private TextField tfNom, tfCif, tfMotiuInactiu, tfTelefon, tfQualificacio, tfDescompte, tfDataAlta;

    @FXML
    private ComboBox<EstatProveidor> cbEstat;

    private ObservableList<Proveidor> llistaObservableProveidor;
    private ProveidorLogica proveidorLogica;
    private Usuari usuari;  // Variable para guardar el usuario autenticado

    // Método para establecer el usuario autenticado
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;  // Guardar el usuario
        gestionarPermisos();    // Gestionar los permisos según el rol
    }

    @FXML
    public void initialize() {
        llistaObservableProveidor = FXCollections.observableArrayList();
        proveidorLogica = new ProveidorLogica();

        // Rellenar el ComboBox con los valores de EstatProveidor
        cbEstat.setItems(FXCollections.observableArrayList(EstatProveidor.values()));

        try {
            llistaObservableProveidor.addAll(proveidorLogica.obtenirTotsElsProveidors());
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }

        // Configurar las columnas con el modelo Proveidor
        colCif.setCellValueFactory(new PropertyValueFactory<>("CIF"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colEstat.setCellValueFactory(new PropertyValueFactory<>("Estat"));
        colMotiuInactiu.setCellValueFactory(new PropertyValueFactory<>("MotiuInactiu"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
        colDescompte.setCellValueFactory(new PropertyValueFactory<>("Descompte"));
        colDataAlta.setCellValueFactory(new PropertyValueFactory<>("Data_Alta"));
        colQualificacio.setCellValueFactory(new PropertyValueFactory<>("Qualificacio"));

        // Asignar la lista observable al TableView
        tabViewProveidor.setItems(llistaObservableProveidor);
        tabViewProveidor.setOnMouseClicked(this::handleOnMouseClicked);

        // Desactivar botones al inicio
        desactivarBotons();
    }

    private void gestionarPermisos() {
        if (usuari != null) {
            boolean esMagatzem = usuari.isRol();
            btnAfegir.setDisable(!esMagatzem);
            btnMod.setDisable(true); // Iniciar deshabilitado hasta que se seleccione una familia
            btnElimi.setDisable(true);  // Iniciar deshabilitado hasta que se seleccione una familia
            btnImportar.setDisable(!esMagatzem);
        } else {
            desactivarBotons();
        }
    }

    private void desactivarBotons() {
        btnElimi.setDisable(true);
        btnMod.setDisable(true);
        btnAfegir.setDisable(true);
        btnImportar.setDisable(true);
        btnExportar.setDisable(true);
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
                tfDescompte.setText(String.valueOf(proveidorSeleccionat.getDescompte()));
                tfDataAlta.setText(proveidorSeleccionat.getData_Alta().toString());
                tfQualificacio.setText(String.valueOf(proveidorSeleccionat.getQualificacio()));

                // Activar botones
                btnElimi.setDisable(false);
                btnMod.setDisable(false);
            } else {
                desactivarBotons();
            }
        }
    }

    @FXML
    public void btnLogo_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Logo' presionat");
        App.setRoot("menuPrincipal", usuari);
    }

    @FXML
    public void btnSortida_action(ActionEvent event) throws IOException {
        App.setRoot("menuPrincipal", usuari);
    }

    @FXML
    public void btnTancar_action(ActionEvent event) throws IOException {
        App.setRoot("iniciSessio");
    }

    @FXML
    public void btnAfegir_action(ActionEvent event) {
        System.out.println("Botó 'Afegir' presionat");

        EstatProveidor estat = cbEstat.getValue(); // Valor del ComboBox

        // Crear una nueva referencia con todos los valores
        Proveidor nouProveidor = new Proveidor("", "", estat, "", "", 0.0f, LocalDate.now(), 0);

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
                float descompteNou = Float.parseFloat(tfDescompte.getText());
                LocalDate dataAltaNova = LocalDate.parse(tfDataAlta.getText());
                int qualificacioNova = Integer.parseInt(tfQualificacio.getText());

                if (proveidorSeleccionat.getCIF().equals(cifNou)) {
                    // Modificar el proveedor existente
                    proveidorLogica.modificarProveidor(cifNou, nomNou, estatNou, motiuInactiuNou, telefonNou, descompteNou, dataAltaNova, qualificacioNova);
                    System.out.println("Proveedor modificado correctamente.");
                } else {
                    // Si es un proveedor nuevo, agregarlo
                    proveidorLogica.afegirProveidor(cifNou, nomNou, estatNou, motiuInactiuNou, telefonNou, descompteNou, dataAltaNova, qualificacioNova);
                    System.out.println("Nuevo proveedor agregado con CIF: " + cifNou);
                }

                // Actualizar els valors de la familia a la llista observable (TableView)
                proveidorSeleccionat.setCIF(cifNou);
                proveidorSeleccionat.setNom(nomNou);
                proveidorSeleccionat.setEstat(estatNou);
                proveidorSeleccionat.setMotiuInactiu(motiuInactiuNou);
                proveidorSeleccionat.setTelefon(telefonNou);
                proveidorSeleccionat.setDescompte(descompteNou);
                proveidorSeleccionat.setData_Alta(dataAltaNova);
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

    @FXML
    public void btnImportar_action(ActionEvent event) {
        // Acción simple para probar el botón
        System.out.println("El botón 'Importar' ha sido presionado.");

        // Crear un objeto FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo a importar");

        // Establecer extensiones de archivo aceptadas (opcional)
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Archivos CSV (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Abrir el diálogo para seleccionar un archivo
        Window stage = ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        // Verificar si se ha seleccionado un archivo
        if (file != null) {
            // Aquí puedes implementar la lógica para importar el archivo
            System.out.println("Archivo seleccionado: " + file.getAbsolutePath());

            // Puedes llamar a otro método para manejar la importación
            importarArchivo(file);
        } else {
            System.out.println("No se ha seleccionado ningún archivo.");
        }
    }

    @FXML
    public void btnExportar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Exportar' presionat");
        // Crear un archivo CSV y mostrar un diálogo para elegir la ubicación
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Proveedores");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                // Escribir encabezados
                bw.write("CIF,Nom,Estat,Motiu Inactiu,Telefon,Descompte,Data Alta,Qualificació");
                bw.newLine();

                // Escribir los datos de cada proveedor
                for (Proveidor proveidor : llistaObservableProveidor) {
                    String line = String.join(",",
                            proveidor.getCIF(),
                            proveidor.getNom(),
                            proveidor.getEstat().name(), // Guardar el enum como un String
                            proveidor.getMotiuInactiu(),
                            proveidor.getTelefon(),
                            String.valueOf(proveidor.getDescompte()),
                            proveidor.getData_Alta().toString(),
                            String.valueOf(proveidor.getQualificacio())
                    );
                    bw.write(line);
                    bw.newLine();
                }

                System.out.println("Exportación completada. Proveedores exportados: " + llistaObservableProveidor.size());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al escribir el archivo: " + e.getMessage());
            }
        }
    }

    public void importarArchivo(File filePath) {

        List<Proveidor> proveidorsImportats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Leer la primera línea si contiene encabezados
            br.readLine(); // Si tienes encabezados en el CSV, puedes descomentar esto

            while ((line = br.readLine()) != null) {
                // Suponemos que los campos están separados por comas
                String[] datos = line.split(",");

                // Asegúrate de que hay suficientes datos en la fila
                if (datos.length >= 8) { // Ajusta esto según la cantidad de campos
                    String cif = datos[0].trim();
                    String nom = datos[1].trim();

                    EstatProveidor estat;
                    try {
                        estat = EstatProveidor.valueOf(datos[2].trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Valor de estat inválido: " + datos[2]);
                        continue; // Salta esta fila
                    }

                    String motiuInactiu = datos[3].trim();
                    String telefon = datos[4].trim();
                    float descompte;
                    try {
                        descompte = Float.parseFloat(datos[5].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Descuento inválido para el CIF " + cif + ": " + datos[5]);
                        continue; // Salta esta fila
                    }

                    LocalDate dataAlta;
                    try {
                        dataAlta = LocalDate.parse(datos[6].trim());
                    } catch (Exception e) {
                        System.out.println("Fecha de alta inválida para el CIF " + cif + ": " + datos[6]);
                        continue; // Salta esta fila
                    }

                    int qualificacio;
                    try {
                        qualificacio = Integer.parseInt(datos[7].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Calificación inválida para el CIF " + cif + ": " + datos[7]);
                        continue; // Salta esta fila
                    }

                    // Crear un nuevo proveedor y añadirlo a la lista
                    Proveidor nouProveidor = new Proveidor(cif, nom, estat, motiuInactiu, telefon, descompte, dataAlta, qualificacio);
                    proveidorsImportats.add(nouProveidor);
                } else {
                    System.out.println("Fila con datos insuficientes: " + line);
                }
            }

            // Aquí puedes añadir cada proveedor a la base de datos
            for (Proveidor proveidor : proveidorsImportats) {
                try {
                    proveidorLogica.afegirProveidor(proveidor.getCIF(), proveidor.getNom(),
                            proveidor.getEstat(), proveidor.getMotiuInactiu(),
                            proveidor.getTelefon(), proveidor.getDescompte(),
                            proveidor.getData_Alta(), proveidor.getQualificacio());
                } catch (Exception e) {
                    System.out.println("Error al añadir el proveedor: " + proveidor.getCIF() + " - " + e.getMessage());
                }
            }

            // Actualizar la lista observable y la tabla
            llistaObservableProveidor.addAll(proveidorsImportats);
            tabViewProveidor.setItems(llistaObservableProveidor);

            System.out.println("Importación completada. Proveedores añadidos: " + proveidorsImportats.size());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

}
