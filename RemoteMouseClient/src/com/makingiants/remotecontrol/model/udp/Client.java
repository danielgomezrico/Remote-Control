package com.makingiants.remotecontrol.model.udp;

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
	
	private final DatagramSocket socket;
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
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setBroadcast(boolean broadcast) throws SocketException {
		socket.setBroadcast(broadcast);
	}
	
	// ------------------------------------------------------
	// Accessor methods
	// ------------------------------------------------------
	
	public void setIp(final String ip) throws UnknownHostException {
		this.address = InetAddress.getByName(ip);
	}
	
	public void setPort(final int port) {
		this.port = port;
	}
}
