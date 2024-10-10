/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcions;

/**
 *
 * @author danie
 */
public class QualificacioInvalid extends Exception{
    
    public QualificacioInvalid(){        
    }
    
    public QualificacioInvalid(String message){
        super(message);
    }
}
