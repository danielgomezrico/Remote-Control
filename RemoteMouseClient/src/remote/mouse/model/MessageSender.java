package remote.mouse.model;

import java.net.SocketException;
import java.security.InvalidParameterException;

import remote.mouse.model.udp.Client;
import android.util.Log;

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
	
	public static MessageSender getInstance() {
		if (instance == null) {
			instance = new MessageSender();
		}
		
		return instance;
	}
	
	// ---------------------------------------------
	// Message methods
	// ---------------------------------------------
	
	public void sendMouseClickMessage() {
		client.send("mc 1");
	}
	
	public void sendMouseRightClickMessage() {
		client.send("mc 2");
	}
	
	public void sendMouseMovementMessage(final int... numbers) {
		
		if (numbers != null && numbers.length == 2) {
			client.send(String.format("mm %d %d", numbers[0], numbers[1]));
		} else {
			throw new InvalidParameterException(
			        "The mouse move event must have two numbers in numbers param");
		}
		
	}
	
	public void sendKeyboardMessage(String value) {
		
		if (value.equals(" ")) {
			value = "SPACE";
		}
		
		client.send("k " + value);
		
	}
}
