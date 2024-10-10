package aplicacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import presentacio.MenuPrincipal;
import aplicacio.model.Usuari;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("iniciSessio"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Testosterona SL");
        stage.show();
    }

    public static void setRoot(String fxml, Usuari usuari) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cat/copernic/projecte1_equip1/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        //Si carreguem la vista de menuPrincipal, passem l'usuari autenticat
        if (fxml.equals("menuPrincipal")) {
            MenuPrincipal controller = fxmlLoader.getController();
            controller.setUsuari(usuari);  // Aqui pasem l'objete Usuari
        }
        scene.setRoot(root);
    }

    //setRoot per usar sense passar l'usuari per tancar la sessió
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cat/copernic/projecte1_equip1/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
