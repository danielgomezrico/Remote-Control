/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makingiants.model.managers;

import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danielgomezrico
 */
public class MessageManager {
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private MouseManager mouseManager;
	private KeyboardManager keyboardManager;
	
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	
	public MessageManager() {
		mouseManager = new MouseManager();
		try {
			keyboardManager = new KeyboardManager();
		} catch (AWTException ex) {
			Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	// ---------------------------------------------
	// Message managment methods
	// ---------------------------------------------
	
	public void manage(String message) {
		
		if (message.startsWith("mm")) {
			String[] values = message.split(" ");
			
			if (values != null && values.length == 3) {
				int movementX = Integer.parseInt(values[1]);
				int movementY = Integer.parseInt(values[2]);
				
				mouseManager.move(movementX, movementY);
			}
		} else if (message.startsWith("mc")) {
			
			String[] values = message.split(" ");
			
			if (values != null && values.length == 2) {
				mouseManager.click(Integer.valueOf(values[1]));
			}
			
		} else if (message.startsWith("k")) {
			
			String[] values = message.split(" ");
			if (values != null && values.length == 2) {
				keyboardManager.pressKey(values[1]);
			}
			
		}
	}
	
}
