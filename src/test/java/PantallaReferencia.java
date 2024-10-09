/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 *
 * @author Héctor Vico
 */
public class PantallaReferencia extends ApplicationTest {

    private Parent rootNode;

    @Override
    public void start(Stage stage) throws Exception {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cat/copernic/projecte1_equip1/menuReferencia.fxml"));
        rootNode = loader.load();

        // Crear la escena y asignarla al stage
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.setTitle("Menu Referencia"); // Establecer título para la ventana
        stage.show();
    }

    @Test
    public void displayMenuFamilia() {
        // Mantener el test en espera mientras la ventana esté abierta
        while (true) {
            if (!Stage.getWindows().isEmpty() && !Stage.getWindows().get(0).isShowing()) {
                break; // Salir del bucle si la ventana se ha cerrado
            }
            try {
                Thread.sleep(100); // Esperar un breve momento antes de verificar de nuevo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
