/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dades;

import java.util.List;

/**
 * descripció: Interface creada per als métodes bàsics comuns entre les classes 
 * DAOfamiliaImpl, DAOproveidorImpl i DAOreferenciaImpl. Defineix mètodes per 
 * afegir, obtenir, actualitzar i eliminar entitats.
 * 
 * @author ngall
 * @version 10/2024.1
 * @param <T> El tipus d'entitat amb la qual treballarà l'implementació del DAO.
 */
public interface DAOinterface<T> {
    
    /**
     * Afegeix una nova entitat al sistema connectat a la base de dades.
     * @param entitat L'entitat a afegir.
     */
    public void afegir(T entitat);
    
    
    /**
     * Actualitza l'informació d'una entitat existent en el sistema connectat a la base de dades. 
     * @param entitat L'entitat amb les dades actualitzades.
     */
    public void actualitzar(T entitat);
    
    /**
     * Elimina una entitat del sistema connectat a la base de dades.
     * @param entitat L'entitat a eliminar.
     */
    public void eliminar (T entitat);
}
