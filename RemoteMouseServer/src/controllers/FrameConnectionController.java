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

    FrameConnection _frame;
    UDPServer _server;

    public FrameConnectionController() {

        _frame = new FrameConnection(this);

        _server = new UDPServer();
        _frame.textfieldIP.setText(_server.getMyIP());

        _frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!_server.isConnected()) {

            _server.start();

            _frame.buttonStart.setText("Stop");
        }
        else {
            _server.stop();

            _frame.buttonStart.setText("Start");
        }
    }
}
