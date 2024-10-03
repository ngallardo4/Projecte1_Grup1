/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ngall
 */
public class DAOusuariImpl {
    private Map<String, String> usuaris;
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
                    if(parts.length == 2){
                        String email = parts[0].trim();
                        String password = parts[1].trim();
                        
                        usuaris.put(email, password);
                    }
                }
            }
        }catch(IOException e){
            System.out.println("Error a la lectura de l'arxiu: " + e.getMessage());
        }
    }
    
    public boolean verificarUsuari(String email, String password){
        return usuaris.containsKey(email) && usuaris.get(email).equals(password);
    }
    
}
