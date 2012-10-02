package remote.mouse.model;

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
		client = Client.getInstance();
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

	public void sendMouseMessage(int... numbers) {

		if (numbers != null && numbers.length == 2) {
			client.send(String.format("m %d %d", numbers[0], numbers[1]));
		} else {
			throw new InvalidParameterException(
					"The mouse move event must have two numbers in numbers param");
		}

	}

	public void sendKeyboardMessage(String value) {

		if (value.equals(" ")) {
			value = "SPACE";  
		}

		Log.d("key", "k " + value);
		client.send("k " + value);

	}
}
