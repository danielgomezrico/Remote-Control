/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

/**
 *
 * @author danielgomezrico
 */
public class MessageManager {
   
    MouseManager _mouseManager;
    
    public MessageManager(){
        _mouseManager = new MouseManager();
    }
    
    public void manage(String message){
        if(message.startsWith("m")){
            String[] values = message.split(" ");
            
            int movementX = Integer.parseInt(values[1]);
            int movementY = Integer.parseInt(values[2]);
            
            _mouseManager.moveMouse(movementX, movementY);
        }
    }
    
}
