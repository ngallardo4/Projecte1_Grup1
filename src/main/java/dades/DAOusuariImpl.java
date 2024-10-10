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
 * descripció: Implementació del DAO (Data Access Object) per a la gestió dels 
 * usuaris. Aquesta classe llegeix un fitxer de text per carregar els usuaris i 
 * ofereix funcionalitats per verificar i obtenir usuaris.
 * @author ngall
 * @version 10/2024.1
 */
public class DAOusuariImpl {
    
    public Map<String, Usuari> usuaris;
    private String rutaArxiu = "uspass.txt";
    
    /**
     * Constructor de la classe DAOusuariImpl: Inicialitza el mapa d'usuaris i 
     * carrega els usuaris des del fitxer especificat.
     * @param rutaArxiu La ruta del fitxer d'usuaris a carregar.
     */
    public DAOusuariImpl(String rutaArxiu){
        this.rutaArxiu = rutaArxiu;
        usuaris = new HashMap<>();
        carregarUsuaris();
    }
    
    /**
     * Carrega els usuaris des d'un fitxer de text: El fitxer ha de tenir línies
     * amb el format "email;password;rol", on rol és un valor booleà (true per a
     * Responsable de Magatzem, false per a Venedor). Un cop es verifica que té
     * aquesta estructura, guarda l'usuari al mapa.
     */
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

    /**
     * Retorna l'usuari associat amb un correu electrònic que donem.
     * @param email El correu electrònic de l'usuari.
     * @return L'usuari corresponent si existeix, o null si no existeix cap 
     * usuari amb aquest correu.
     */
    public Usuari getUsuari(String email){
        return usuaris.get(email);
    }
}
