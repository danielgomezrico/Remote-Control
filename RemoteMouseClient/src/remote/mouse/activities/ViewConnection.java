package remote.mouse.activities;

import remote.mouse.R;
import remote.mouse.model.udp.Client;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ViewConnection extends Activity {

	private static final String SHARED_PREFERENCES_NAME = "s_p_connection";
	private static final String SHARED_PREFERENCES_KEY_IP = "s_p_k_ip";
	private static final String SHARED_PREFERENCES_KEY_PORT = "s_p_k_port";

	private String serverIP = "192.168.1.106";
	private int port = 5005;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// No title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_connection);

	}

	@Override
	protected void onResume() {
		super.onResume();

		// Get preferences and set it to the views (last ip and port used)
		final SharedPreferences settings = getSharedPreferences(
				SHARED_PREFERENCES_NAME, 0);
		serverIP = settings.getString(SHARED_PREFERENCES_KEY_IP,
				"192.168.1.106");
		port = settings.getInt(SHARED_PREFERENCES_KEY_PORT, 5005);

		final EditText ip1 = (EditText) findViewById(R.id.view_connection_text_ip1);
		final EditText ip2 = (EditText) findViewById(R.id.view_connection_text_ip2);
		final EditText ip3 = (EditText) findViewById(R.id.view_connection_text_ip3);
		final EditText ip4 = (EditText) findViewById(R.id.view_connection_text_ip4);
		//TODO:add EditText with port to xml and manage it
		//final EditText textPort = (EditText) findViewById(R.id.view_connection_text_port);

		final String[] ips = serverIP.split("[.]");

		if (ips != null && ips.length == 4) {
			ip1.setText(ips[0]);
			ip2.setText(ips[1]);
			ip3.setText(ips[2]);
			ip4.setText(ips[3]);
		}

		//textPort.setText(port);
	}

	public void buttonConnectOnClick(final View view) {
		// Get IP and Port from Views
		final EditText ip1 = (EditText) findViewById(R.id.view_connection_text_ip1);
		final EditText ip2 = (EditText) findViewById(R.id.view_connection_text_ip2);
		final EditText ip3 = (EditText) findViewById(R.id.view_connection_text_ip3);
		final EditText ip4 = (EditText) findViewById(R.id.view_connection_text_ip4);
		//final EditText textPort = (EditText) findViewById(R.id.view_connection_text_port_value);

		if (ip1.getText().length() != 0 && ip2.getText().length() != 0
				&& ip3.getText().length() != 0 && ip4.getText().length() != 0
		/*&& textPort.getText().length() != 0*/) {
			serverIP = String.format("%s.%s.%s.%s", ip1.getText(),
					ip2.getText(), ip3.getText(), ip4.getText());

			//port = Integer.valueOf(String.valueOf(textPort.getText()));

			//Init the UDP Client
			Client.getInstance().connect(serverIP, port);

			//TODO: Manage errors
			final Intent newActivity = new Intent(this, ViewTab.class);
			startActivity(newActivity);

			// Save the last IP used
			final SharedPreferences settings = getSharedPreferences(
					SHARED_PREFERENCES_NAME, 0);
			settings.edit().putString(SHARED_PREFERENCES_KEY_IP, serverIP);
			settings.edit().putInt(SHARED_PREFERENCES_KEY_PORT, port);
		} else {
			Toast.makeText(this, "Ningun campo puede estar vacio",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onDestroy() {
		Client.getInstance().disconnect();
		super.onDestroy();
	}

}