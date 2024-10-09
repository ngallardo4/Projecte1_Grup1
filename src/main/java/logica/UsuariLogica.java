/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.regex.Pattern;
/**
 * descripció: Aquesta classe utilitza l'expressió regular per validar el email
 * introduït per un usuari.
 * @author ngall
 * @version 10/2024.1
 */
public class UsuariLogica {
    
    //Verificar el format del correu
    /**
     * Verifica mitjançant una expressió regular si el correu electrònic és vàlid.
     * @param email, El correu electrònic a validar.
     * @return true si el correu és vàlid, false si no ho és.
     */
    public static boolean emailValid(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
