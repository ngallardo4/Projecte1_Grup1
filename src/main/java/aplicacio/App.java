package aplicacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentacio.Login;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("iniciSessio",true), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml,false));
    }

    private static Parent loadFXML(String fxml, boolean esLogin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cat/copernic/projecte1_equip1/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        
        //Si es l'escena del login inicialitzarà el controlador
        if(esLogin){
            Login loginControlador = fxmlLoader.getController();
            loginControlador.in(); //El truquem per inicialitzar-lo
        }
        return root;
    }

    public static void main(String[] args) {
        launch();
    }

}