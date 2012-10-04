/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.server.UDPServer;
import views.FrameConnection;

public class FrameConnectionController implements ActionListener {

	private final FrameConnection frame;
	private final UDPServer server;

	public FrameConnectionController() {

		frame = new FrameConnection(this);

		server = new UDPServer();
		frame.textfieldIP.setText(server.getMyIP());
		frame.textfieldPort.setText(String.valueOf(UDPServer.PORT));

		frame.setVisible(true);

		server.start();
		frame.buttonStart.setText("Stop");
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
		if (!server.isConnected()) {

			server.start();

			frame.buttonStart.setText("Stop");
		} else {
			server.stop();

			frame.buttonStart.setText("Start");
		}
	}
}
