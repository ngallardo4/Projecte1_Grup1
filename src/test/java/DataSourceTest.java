/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Héctor Vico
 */
import dades.MyDataSource;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DataSourceTest {

    @Test
    public void testConnection() {
        try (Connection connection = MyDataSource.getConnection()) {
            // Verifica que la conexión no es null
            assertNotNull(connection, "La conexión debería ser válida y no nula.");
            
            // Puedes verificar más cosas si quieres, como si está abierta
            if (connection.isClosed()) {
                fail("La conexión no debería estar cerrada.");
            }

            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            fail("No se pudo establecer conexión con la base de datos: " + e.getMessage());
        }
    }
}

