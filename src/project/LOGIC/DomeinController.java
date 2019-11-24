/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.LOGIC;

/**
 *
 * @author eliseverschelde
 */
public class DomeinController {
    
    public static DomeinController domeinController = new DomeinController();
    
    public DomeinController(){
        
    }
    
    public static DomeinController getInstance(){
        return domeinController;
    }
    
    public String toonVluchtNaam(){
        return "dit is de vluchtnaam";
    }
}
