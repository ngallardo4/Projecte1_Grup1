/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dades;

import java.util.List;

/**
 *
 * @author ngall
 */
public interface DAOinterface<T> {
    
    public void afegir(T entitat);
    
    List <T> obtenirEntitats();
    
    public void actualitzar(T entitat);
    
    public void eliminar (T entitat);
}
