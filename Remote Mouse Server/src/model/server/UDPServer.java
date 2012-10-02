/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

/**
 *
 * @author danielgomezrico
 */
public class UDPServer {

    public static final int PORT = 5005;
    private NioDatagramAcceptor _acceptor;
    private UDPHandler _handler;
    private boolean _connected = false;

    public void start() {
        _acceptor = new NioDatagramAcceptor();
        _handler = new UDPHandler();
        _acceptor.setHandler(_handler);

        DatagramSessionConfig datagramConfig = _acceptor.getSessionConfig();
        datagramConfig.setReuseAddress(true);

        try {
            //Be ready to listen
            _acceptor.bind(new InetSocketAddress(PORT));

            _connected = true;

        } catch (IOException ex) {
            //TODO: Manage errors
            // Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        
         _handler.close();
         _acceptor.dispose(false);
        _connected = false;
        
    }

    public String getMyIP() {
        try {

            InetAddress addr = InetAddress.getLocalHost();

            String myIP = addr.getHostAddress();

            // Bonus. Get your hostname.
            //String myHost = addr.getHostName();

            return myIP;


        } catch (UnknownHostException e) {
            //TODO: Manage error
        }

        return null;
    }

    public boolean isConnected() {
        return _connected;
    }
}
