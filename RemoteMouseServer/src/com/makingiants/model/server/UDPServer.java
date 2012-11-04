/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makingiants.model.server;

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
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private int port = 5005;
	private UDPHandler _handler;
	private boolean _connected = false;
	private NioDatagramAcceptor _acceptor;
	
	// ---------------------------------------------
	// Server methods
	// ---------------------------------------------
	
	public void start() {
		_acceptor = new NioDatagramAcceptor();
		_handler = new UDPHandler();
		_acceptor.setHandler(_handler);
		
		DatagramSessionConfig datagramConfig = _acceptor.getSessionConfig();
		datagramConfig.setReuseAddress(true);
		
		try {
			//Be ready to listen
			_acceptor.bind(new InetSocketAddress(port));
			
			_connected = true;
			
		} catch (IOException ex) {
			//TODO: Manage errors
			// Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void stop() {
		
		try {
			_handler.close();
			_acceptor.dispose(false);
			_connected = false;
		} catch (NullPointerException e) {
			
		}
		
	}
	
	// ---------------------------------------------
	// Accessor Methods
	// ---------------------------------------------
	
	public final int getPort() {
		return port;
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
