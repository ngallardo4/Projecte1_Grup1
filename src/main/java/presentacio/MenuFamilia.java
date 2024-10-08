package presentacio;

import aplicacio.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MenuFamilia {

 @FXML
    private Button btnLogo;

    @FXML
    private TableColumn<?, ?> colProv;

    @FXML
    private Button btnTancar;

    @FXML
    private TextField tf_obvsFam;

    @FXML
    private TextField tf_provFam;

    @FXML
    private TableColumn<?, ?> colData;

    @FXML
    private Button btnDelFam;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TextField tf_dataltaFam;

    @FXML
    private Button btnModFam;

    @FXML
    private Button btnNovaFam;

    @FXML
    private Button btnSortirFam;

    @FXML
    private Button btnProdFam;

    @FXML
    private TableColumn<?, ?> colObvs;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TextField tf_idFam;

    @FXML
    private TableView<?> TabViewFam;

    @FXML
    private TextField tf_nomFam;

    @FXML
    private TableColumn<?, ?> colDescrip;

    @FXML
    private TextField tf_desFam;


    @FXML
    public void initialize() throws IOException {
        
    }

    @FXML
    public void btnLogo_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Logo' presionat");
        App.setRoot("secondary");
    }

    @FXML
    public void btnTancar_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Tancar' presionat");
        App.setRoot("iniciSessio");
    }

    @FXML
    public void btnSortirFam_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Sortir' presionat");
        App.setRoot("secondary");
    }

    @FXML
    public void btnNovaFam_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Nova' presionat");
    }

    @FXML
    public void btnModFam_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Modificar' presionat");

    }

    @FXML
    public void btnDelFam_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Eliminar' presionat");

    }

    @FXML
    public void btnProdFam_action(ActionEvent event) throws IOException {
        System.out.println("Botó 'Productes' presionat");
        App.setRoot("menuReferencia");
    }
}
