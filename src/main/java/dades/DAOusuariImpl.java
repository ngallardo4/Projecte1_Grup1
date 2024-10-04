/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Usuari;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ngall
 */
public class DAOusuariImpl {
    private Map<String, Usuari> usuaris;
    private String rutaArxiu;
    
    public DAOusuariImpl(String rutaArxiu){
        this.rutaArxiu = rutaArxiu;
        usuaris = new HashMap<>();
        carregarUsuaris();
    }
    
    private void carregarUsuaris(){
        try(BufferedReader lec = new BufferedReader(new FileReader(rutaArxiu))){
            String linea;
            while ((linea = lec.readLine()) != null){
                if(!linea.isEmpty() && linea.contains(";")){
                    String[] parts = linea.split(";");
                    if(parts.length == 3){
                        String email = parts[0].trim();
                        String password = parts[1].trim();
                        boolean rol = Boolean.parseBoolean(parts[2].trim());
                        
                        
                        //Crear l'usuari
                        Usuari usuari = new Usuari(0, email, password, "",5.5f, LocalDate.now(), rol);
                        //Guardar l'usuari al mapa
                        usuaris.put(email, usuari); 
                    }
                }
            }
        }catch(IOException e){
            System.out.println("Error a la lectura de l'arxiu: " + e.getMessage());
        }
    }
    
    public boolean verificarUsuari(String email, String password){
        if(usuaris.containsKey(email)){
            Usuari usuari = usuaris.get(email);
            return usuari.getPassword().equals(password);
        }
        return false;
    }
    
    public Usuari getUsuari(String email){
        return usuaris.get(email);
    }
}
