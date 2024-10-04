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
    private Button btnLogo, btnTancar;

    @FXML
    private TextField tf_idFam, tf_nomFam, tf_desFam, tf_dataltaFam, tf_provFam, tf_obvsFam;

    @FXML
    TableView TabViewFam;
    
    @FXML
    TableColumn colId, colNom, colDescrip, colData, colProv, colObvs;

    @FXML
    private Button btnSortirFam, btnNovaFam, btnModFam, btnDelFam, btnProdFam;


    @FXML
    public void initialize() throws IOException {
        System.out.println("Holaa");
    }

    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void btnTancar_action(ActionEvent event) {
        try {
            App.setRoot("iniciSessio");
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
