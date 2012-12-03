package com.makingiants.model.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	
	// ------------------------------------------------------
	// Constants
	// ------------------------------------------------------
	
	private static Client instance;
	
	// ------------------------------------------------------
	// Attributes
	// ------------------------------------------------------
	
	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	
	// ------------------------------------------------------
	// Constructor
	// ------------------------------------------------------
	
	private Client() throws SocketException {
		socket = new DatagramSocket();
	}
	
	public static Client getInstance() throws SocketException {
		if (instance == null) {
			instance = new Client();
		}
		
		return instance;
	}
	
	// ------------------------------------------------------
	// Send message methods
	// ------------------------------------------------------
	
	public void send(final String message) {
		
		try {
			
			final DatagramPacket p = new DatagramPacket(message.getBytes(), message.length(), address,
			        port);
			socket.send(p);
			
		} catch (final SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// ------------------------------------------------------
	// Accessor methods
	// ------------------------------------------------------
	
	public void setIp(String ip) throws UnknownHostException {
		this.address = InetAddress.getByName(ip);
	}
	
	public void setPort(int port) {
		this.port = port;
	}
}
