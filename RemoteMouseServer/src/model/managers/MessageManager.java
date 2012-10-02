/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danielgomezrico
 */
public class MessageManager {
    
    MouseManager mouseManager;
    KeyboardManager keyboardManager;
    
    public MessageManager(){
        mouseManager = new MouseManager();
        try {
            keyboardManager = new KeyboardManager();
        } catch (AWTException ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void manage(String message){
        System.out.println("Message: " + message);
        if(message.startsWith("m")){
            String[] values = message.split(" ");
            
            if(values != null && values.length == 3){
                int movementX = Integer.parseInt(values[1]);
                int movementY = Integer.parseInt(values[2]);
                
                mouseManager.moveMouse(movementX, movementY);
            }
        }
        else if(message.startsWith("k")){
            
            String[] values = message.split(" ");
            if(values != null && values.length == 2){
                keyboardManager.pressKey(values[1]);
            }
            
        }
    }

}
