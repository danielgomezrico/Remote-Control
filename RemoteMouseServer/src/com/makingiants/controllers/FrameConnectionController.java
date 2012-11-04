/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makingiants.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.makingiants.model.server.UDPServer;
import com.makingiants.views.FrameConnection;


public class FrameConnectionController implements ActionListener {
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private final FrameConnection frame;
	private final UDPServer server;
	
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	
	public FrameConnectionController() {
		
		frame = new FrameConnection(this);
		frame.setVisible(true);
		
		server = new UDPServer();
		frame.textfieldIP.setText(server.getMyIP());
		frame.textfieldPort.setText(String.valueOf(server.getPort()));
		
		frame.setVisible(true);
		
		server.start();
		frame.buttonStart.setText("Stop");
		frame.textfieldPort.setEditable(false);
	}
	
	// ---------------------------------------------
	// Events
	// ---------------------------------------------
	
	@Override
	public void actionPerformed(final ActionEvent ae) {
		if (!server.isConnected()) {
			
			server.start();
			frame.textfieldPort.setEditable(false);
			frame.buttonStart.setText("Stop");
			
		} else {
			
			server.stop();
			frame.textfieldPort.setEditable(true);
			frame.buttonStart.setText("Start");
			
		}
	}
	
}
