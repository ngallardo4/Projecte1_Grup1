/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ngallardo
 */
import aplicacio.model.Usuari;
import dades.DAOusuariImpl;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import logica.UsuariLogica;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class UsuariLogicaTest {

    private DAOusuariImpl usuariDAO;
    private UsuariLogica usuariLogica;
    private final String rutaArxiu = "uspass.txt"; // Ruta original

    @BeforeEach
    void setUp() {
        // Inicializa el DAO y la lógica del usuario
        usuariDAO = new DAOusuariImpl(rutaArxiu);
        usuariLogica = new UsuariLogica(usuariDAO);
    }

    @Test
    void testEmailValid() {
        assertTrue(UsuariLogica.emailValid("nacho@gmail.com"), "El correo debería ser válido.");
        assertFalse(UsuariLogica.emailValid("invalid-email"), "El correo debería ser inválido.");
        assertFalse(UsuariLogica.emailValid("@.example.com"), "El correo debería ser inválido.");
    }

    @Test
    void testCarregarUsuaris() {
        // Verifica que los usuarios se hayan cargado correctamente desde el archivo
        Usuari usuario1 = usuariDAO.getUsuari("nacho@gmail.com");
        Usuari usuario2 = usuariDAO.getUsuari("yaiza@gmail.com");

        assertNotNull(usuario1, "El usuario debería existir.");
        assertNotNull(usuario2, "El usuario debería existir.");
        assertEquals("123456", usuario1.getPassword(), "La contraseña debería coincidir.");
        assertEquals("654321", usuario2.getPassword(), "La contraseña debería coincidir.");
    }

    @Test
    void testVerificarUsuari() {
        // Prueba la verificación de usuario
        assertTrue(usuariLogica.verificarUsuari("nacho@gmail.com", "123456"), "El usuario debería ser autenticado correctamente.");
        assertFalse(usuariLogica.verificarUsuari("yaiza@gmail.com", "dsfdsfdsd"), "La contraseña incorrecta no debería permitir el acceso.");
        assertFalse(usuariLogica.verificarUsuari("blabla@gmail.com", "csfdfsf"), "Un usuario inexistente no debería ser autenticado.");
    }

    @Test
    void testFileInputStream() {
        // Verifica que se pueda leer desde el archivo uspass.txt
        try (InputStream inputStream = new FileInputStream(rutaArxiu);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            Map<String, Usuari> usuariosLeidos = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && line.contains(";")) {
                    String[] parts = line.split(";");
                    if (parts.length == 3) {
                        String email = parts[0].trim();
                        String password = parts[1].trim();
                        boolean rol = Boolean.parseBoolean(parts[2].trim());

                        // Crear y almacenar el usuario leído
                        Usuari usuario = new Usuari(0, email, password, "", 5.5f, LocalDate.now(), rol);
                        usuariosLeidos.put(email, usuario);
                    }
                }
            }

            // Verifica que los usuarios leídos coincidan con los usuarios cargados en el DAO
            assertEquals(usuariDAO.usuaris.size(), usuariosLeidos.size(), "El número de usuarios leídos debería coincidir con el número de usuarios cargados en el DAO.");

            for (String email : usuariosLeidos.keySet()) {
                Usuari usuarioLeido = usuariosLeidos.get(email);
                Usuari usuarioDAO = usuariDAO.getUsuari(email);
                assertNotNull(usuarioDAO, "El usuario debería existir en el DAO.");
                assertEquals(usuarioLeido.getPassword(), usuarioDAO.getPassword(), "Las contraseñas deberían coincidir.");
            }
        } catch (IOException e) {
            fail("No se pudo leer el archivo: " + e.getMessage());
        }
    }
}