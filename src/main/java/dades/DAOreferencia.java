/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dades;

import java.util.List;

/**
 * Interfície que defineix les operacions per gestionar referències.
 * Proporciona mètodes per obtenir referències sense estoc.
 * 
 * @param <T> el tipus de referència gestionat per la implementació.
 * @author Héctor Vico
 */
public interface DAOreferencia<T> {

    /**
     * Obté una llista de referències que no tenen estoc disponible.
     * 
     * @return una llista de referències sense estoc.
     */
    List<T> obtenirReferenciesSenseEstoc();

}
