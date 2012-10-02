/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.server.UDPServer;
import views.FrameConnection;

/**
 *
 * @author danielgomezrico
 */
public class FrameConnectionController implements ActionListener {

    private FrameConnection frame;
    private UDPServer server;

    public FrameConnectionController() {

        frame = new FrameConnection(this);

        server = new UDPServer();
        frame.textfieldIP.setText(server.getMyIP());
        frame.textfieldPort.setText(String.valueOf(server.PORT));

        frame.setVisible(true);
        
        server.start();
        frame.buttonStart.setText("Stop");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!server.isConnected()) {

            server.start();

            frame.buttonStart.setText("Stop");
        }
        else {
            server.stop();

            frame.buttonStart.setText("Start");
        }
    }
}
