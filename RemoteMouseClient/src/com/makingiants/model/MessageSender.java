package com.makingiants.model;

import java.net.SocketException;

import android.util.Log;

import com.makingiants.model.udp.Client;

public class MessageSender {
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private static MessageSender instance;
	private Client client;
	
	// ---------------------------------------------
	// Constructors And Singleton get
	// ---------------------------------------------
	
	private MessageSender() {
		try {
			client = Client.getInstance();
		} catch (final SocketException e) {
			Log.e("RemoteControl", "MessageSender 1", e);
		}
	}
	
	/*
	 * Singleton getter
	 */
	public static MessageSender getInstance() {
		if (instance == null) {
			instance = new MessageSender();
		}
		
		return instance;
	}
	
	// ---------------------------------------------
	// Message methods
	// ---------------------------------------------
	
	/**
	 * Send mouse left click event
	 */
	public void sendMouseClickMessage() {
		client.send("mc 1");
	}
	
	/**
	 * Send mouse right click event
	 */
	public void sendMouseRightClickMessage() {
		client.send("mc 2");
	}
	
	/**
	 * Send mouse movement event 
	 * @param numbers must be an array with 2 values, x and y
	 */
	public void sendMouseMovementMessage(final int... numbers) {
		client.send(String.format("mm %d %d", numbers[0], numbers[1]));
	}
	
	/**
	 * Send keyboard message event 
	 * @param value
	 */
	public void sendKeyboardMessage(String value) {
		client.send("k " + value);
	}
}
