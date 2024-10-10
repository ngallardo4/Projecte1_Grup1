/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import aplicacio.model.Usuari;
import dades.DAOusuariImpl;
import java.util.regex.Pattern;
/**
 * descripció: Aquesta classe utilitza l'expressió regular per validar el email
 * introduït per un usuari.
 * @author ngall
 * @version 10/2024.1
 */
public class UsuariLogica {
    
    private DAOusuariImpl usuariDAO;

    /**
     * Constructor de la classe UsuariLogica, que inicialitza l'objecte amb una 
     * instància de DAOusuariImpl.
     * @param usuariDAO es farà servir per la gestió dels usuaris.
     */
    public UsuariLogica(DAOusuariImpl usuariDAO) {
        this.usuariDAO = usuariDAO;
    }
    
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
        
    /**
     * Verifica si un usuari existeix i si la contrasenya és correcta.
     * @param email El correu electrònic de l'usuari.
     * @param password La contrasenya de l'usuari.
     * @return true si l'usuari existeix i la contrasenya és correcta, false si 
     * no es compleixen les condicions.
     */
    public boolean verificarUsuari(String email, String password){
        if(usuariDAO.usuaris.containsKey(email)){
            Usuari usuari = usuariDAO.usuaris.get(email);
            return usuari.getPassword().equals(password);
        }
        return false;
    }
    
}
