/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makingiants.model.managers;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 *
 * @author danielgomezrico
 */
public class MouseManager {
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private Robot robot;
	private int mouseX, mouseY;
	private double offsetX = 1.8, offsetY = 1.8; // Calculated based on screen size
	        
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	
	public MouseManager() {
		try {
			robot = new Robot();
			
			//Get initial position of the mouse
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			mouseX = (int) b.getX();
			mouseY = (int) b.getY();
			
			//Get display size
			// Get the size of the default screen
			/*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

			_screenWidth = dim.getWidth();
			_screenHeight = dim.getHeight();
			    
			_offsetX = _screenWidth/20;
			_offsetY = _screenHeight/20;*/
			
		} catch (AWTException ex) {
			//TODO: Manage errors
		}
	}
	
	// ---------------------------------------------
	// Generate events methods
	// ---------------------------------------------
	
	public void move(int offsetX, int offsetY) {
		
		mouseX += offsetX * this.offsetX;
		mouseY += offsetY * this.offsetY;
		
		//TODO: Check if this do something
		if (mouseX < 0) {
			mouseX = 0;
		}
		
		if (mouseY < 0) {
			mouseY = 0;
		}
		
		robot.mouseMove(mouseX, mouseY);
	}
	
	public void click(int button) {
		if (button == 1) {
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} else if (button == 2) {
			robot.mousePress(InputEvent.BUTTON2_MASK);
			robot.mouseRelease(InputEvent.BUTTON2_MASK);
			
		}
	}
	
}
