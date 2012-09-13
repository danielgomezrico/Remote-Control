package remote.mouse.activities;

import remote.mouse.R;
import remote.mouse.model.UDPClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ConnectionActivity extends Activity {

	private String serverIP = "192.168.1.101";
	private int PORT = 5005;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// No title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_connection);
	}

	public void buttonConnectOnClick(View view){

		serverIP = getIpFromViews();
		
		//Init the UDP Client
		UDPClient.getInstance().connect(serverIP, PORT);


		//TODO: Manage errors
		Intent newActivity = new Intent(this, EventsActivity.class);
		startActivity(newActivity);
	}

	public String getIpFromViews(){

		TextView ip1 = (TextView) findViewById(R.id.textViewIp1);
		TextView ip2 = (TextView) findViewById(R.id.textViewIp2);
		TextView ip3 = (TextView) findViewById(R.id.textViewIp3);
		TextView ip4 = (TextView) findViewById(R.id.textViewIp4);

		return String.format("%s.%s.%s.%s", ip1.getText(), 
				ip2.getText(), 
				ip3.getText(), 
				ip4.getText());

	}
	
	@Override
	protected void onDestroy() {
		UDPClient.getInstance().disconnect();
		super.onDestroy();
	}


}