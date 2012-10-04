/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.awt.*;

/**
 *
 * @author danielgomezrico
 */
public class MouseManager {

    private Robot _robot;
    private int _mouseX, _mouseY;
    //private double _screenWidth, _screenHeight;
    private double _offsetX = 1.8, _offsetY = 1.8; // Calculated based on screen size
    
    public MouseManager() {
        try {
            _robot = new Robot();

            //Get initial position of the mouse
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            _mouseX = (int) b.getX();
            _mouseY = (int) b.getY();

            //Get display size
            // Get the size of the default screen
            /*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            _screenWidth = dim.getWidth();
            _screenHeight = dim.getHeight();
                
            _offsetX = _screenWidth/20;
            _offsetY = _screenHeight/20;*/

        } catch (AWTException ex) {
            //Logger.getLogger(MouseManager.class.getName()).log(Level.SEVERE, null, ex);
            //TODO: Manage errors
        }
    }

    public void moveMouse(int offsetX, int offsetY) {

        _mouseX += offsetX*_offsetX;
        _mouseY += offsetY*_offsetY;

        //TODO: Check if this do something
        if (_mouseX < 0) {
            _mouseX = 0;
        }

        if (_mouseY < 0) {
            _mouseY = 0;
        }

        _robot.mouseMove(_mouseX, _mouseY);
    }
}
