package com.makingiants.remotecontrol.activities;

import java.net.SocketException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.makingiants.remotecontrol.R;
import com.makingiants.remotecontrol.model.udp.Client;


public class ViewConnection extends Activity {
	
	// ------------------------------------------------------
	// Constants
	// ------------------------------------------------------
	
	private static final String SHARED_PREFERENCES_NAME = "s_p_connection";
	private static final String SHARED_PREFERENCES_KEY_IP = "s_p_k_ip";
	private static final String SHARED_PREFERENCES_KEY_PORT = "s_p_k_port";
	
	// ------------------------------------------------------
	// Attributes
	// ------------------------------------------------------
	
	// Views
	private LinearLayout layoutIp;
	private EditText ip1;
	private EditText ip2;
	private EditText ip3;
	private EditText ip4;
	private EditText textPort;
	
	// Connection attributes
	private String serverIP;
	private int port;
	private boolean automaticConnection;// Static ip or broadcast
	
	// ------------------------------------------------------
	// Activity overrides
	// ------------------------------------------------------
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_connection);
		
		// Get IP and Port from Views
		layoutIp = (LinearLayout) findViewById(R.id.view_connection_layout_ip_values);
		ip1 = (EditText) findViewById(R.id.view_connection_text_ip1);
		ip2 = (EditText) findViewById(R.id.view_connection_text_ip2);
		ip3 = (EditText) findViewById(R.id.view_connection_text_ip3);
		ip4 = (EditText) findViewById(R.id.view_connection_text_ip4);
		textPort = (EditText) findViewById(R.id.view_connection_text_port_value);
		
		automaticConnection = true;
		
		loadAttributes();
		
	}
	
	// ------------------------------------------------------
	// Memory methods
	// ------------------------------------------------------
	
	public void loadAttributes() {
		// Get preferences and set it to the views (last ip and port used)
		final SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
		serverIP = settings.getString(SHARED_PREFERENCES_KEY_IP,
		        getString(R.string.view_connection_default_ip));
		port = settings.getInt(SHARED_PREFERENCES_KEY_PORT,
		        getResources().getInteger(R.integer.view_connection_default_port));
		
		final String[] ips = serverIP.split("[.]");
		
		if (ips != null && ips.length == 4) {
			ip1.setText(ips[0]);
			ip2.setText(ips[1]);
			ip3.setText(ips[2]);
			ip4.setText(ips[3]);
		}
		
		final EditText textPort = (EditText) findViewById(R.id.view_connection_text_port_value);
		textPort.setText(String.valueOf(port));
	}
	
	public void saveAttributes() {
		serverIP = String.format("%s.%s.%s.%s", ip1.getText(), ip2.getText(), ip3.getText(),
		        ip4.getText());
		
		port = Integer.valueOf(String.valueOf(textPort.getText()));
		
		// Save the last IP used
		final SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
		settings.edit().putString(SHARED_PREFERENCES_KEY_IP, serverIP);
		settings.edit().putInt(SHARED_PREFERENCES_KEY_PORT, port);
		
	}
	
	// ------------------------------------------------------
	// Events
	// ------------------------------------------------------
	
	public void buttonConnectOnClick(final View view) {
		
		if (ip1.getText().length() != 0 && ip2.getText().length() != 0 && ip3.getText().length() != 0
		        && ip4.getText().length() != 0 && textPort.getText().length() != 0) {
			
			Client client;
			saveAttributes();
			
			try {
				client = Client.getInstance();
				
				if (automaticConnection) {
					client.setIp(getString(R.string.view_connection_ip_broadcast));
					client.setBroadcast(true);
				} else {
					client.setBroadcast(false);
					client.setIp(serverIP);
				}
				client.setPort(port);
				
			} catch (final SocketException e) {
				Log.e(getString(R.string.app_name), "ViewConnection 1", e);
			} catch (final UnknownHostException e) {
				Log.e(getString(R.string.app_name), "ViewConnection 2", e);
			}
			
			//TODO: Manage errors
			final Intent newActivity = new Intent(this, ArrowsActivity.class);
			startActivity(newActivity);
			
		} else {
			Toast.makeText(this, getString(R.string.view_connection_message_empty_input),
			        Toast.LENGTH_LONG).show();
		}
	}
	
	public void onCheckboxClicked(View view) {
		
		// Is the view now checked?
		boolean checked = ((RadioButton) view).isChecked();
		
		// Check which checkbox was clicked
		switch (view.getId()) {
			case R.id.view_connection_check_ip_auto:
				if (checked) {
					automaticConnection = true;
					layoutIp.setVisibility(View.GONE);
				}
				break;
			case R.id.view_connection_check_ip_manual:
				if (checked) {
					automaticConnection = false;
					layoutIp.setVisibility(View.VISIBLE);
				}
				break;
		
		}
	}
}