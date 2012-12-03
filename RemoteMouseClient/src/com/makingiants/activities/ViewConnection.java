package com.makingiants.activities;

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
import android.widget.Toast;

import com.makingiants.remotecontrol.R;
import com.makingiants.model.udp.Client;

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
	
	private String serverIP;
	private int port;
	
	// ------------------------------------------------------
	// Activity overrides
	// ------------------------------------------------------
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_connection);
		
		// Get preferences and set it to the views (last ip and port used)
		final SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
		serverIP = settings.getString(SHARED_PREFERENCES_KEY_IP,
		        getString(R.string.view_connection_default_ip));
		port = settings.getInt(SHARED_PREFERENCES_KEY_PORT,
		        getResources().getInteger(R.integer.view_connection_default_port));
		
		// Set values to controls
		final EditText ip1 = (EditText) findViewById(R.id.view_connection_text_ip1);
		final EditText ip2 = (EditText) findViewById(R.id.view_connection_text_ip2);
		final EditText ip3 = (EditText) findViewById(R.id.view_connection_text_ip3);
		final EditText ip4 = (EditText) findViewById(R.id.view_connection_text_ip4);
		
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
	
	// ------------------------------------------------------
	// Events
	// ------------------------------------------------------
	
	public void buttonConnectOnClick(final View view) {
		
		// Get IP and Port from Views
		final EditText ip1 = (EditText) findViewById(R.id.view_connection_text_ip1);
		final EditText ip2 = (EditText) findViewById(R.id.view_connection_text_ip2);
		final EditText ip3 = (EditText) findViewById(R.id.view_connection_text_ip3);
		final EditText ip4 = (EditText) findViewById(R.id.view_connection_text_ip4);
		final EditText textPort = (EditText) findViewById(R.id.view_connection_text_port_value);
		
		if (ip1.getText().length() != 0 && ip2.getText().length() != 0 && ip3.getText().length() != 0
		        && ip4.getText().length() != 0 && textPort.getText().length() != 0) {
			serverIP = String.format("%s.%s.%s.%s", ip1.getText(), ip2.getText(), ip3.getText(),
			        ip4.getText());
			
			port = Integer.valueOf(String.valueOf(textPort.getText()));
			
			// Save the last IP used
			final SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
			settings.edit().putString(SHARED_PREFERENCES_KEY_IP, serverIP);
			settings.edit().putInt(SHARED_PREFERENCES_KEY_PORT, port);
			
			Client client;
			
			try {
				client = Client.getInstance();
				
				client.setIp(serverIP);
				client.setPort(port);
				
			} catch (SocketException e) {
				Log.e(getString(R.string.app_name), "ViewConnection 1", e);
			} catch (UnknownHostException e) {
				Log.e(getString(R.string.app_name), "ViewConnection 2", e);
			}
			
			//TODO: Manage errors
			final Intent newActivity = new Intent(this, ViewEvents.class);
			startActivity(newActivity);
			
		} else {
			Toast.makeText(this, getString(R.string.view_connection_message_empty_input),
			        Toast.LENGTH_LONG).show();
		}
	}
	
}