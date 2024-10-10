/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dades;

import java.util.List;

/**
 *
 * @author Héctor Vico
 * @param <T>
 */
public interface DAOinterfaceLlista<T> {
    /**
     * Obté una llista de totes les entitats del sistema connectat a la base de dades.
     * @return Una llista amb totes les entitats disponibles.
     */
    public List <T> obtenirEntitats();
}
