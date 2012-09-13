package remote.mouse.activities;

import remote.mouse.R;
import remote.mouse.model.UDPClient;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;

public class EventsActivity extends Activity implements OnTouchListener{

	private int _lastMouseX, _lastMouseY;
	private UDPClient _client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_events);

		//Set the touch listener
		((LinearLayout)findViewById(R.id.layoutMain)).setOnTouchListener(this);

		_client = UDPClient.getInstance();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int x = (int)event.getX();
		int y = (int)event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			_lastMouseX = x;
			_lastMouseY = y;

			break;

		case MotionEvent.ACTION_MOVE:

			_client.send(String.format("m %d %d", x - _lastMouseX, y - _lastMouseY));

			_lastMouseX = x;
			_lastMouseY = y;

			break;

		case MotionEvent.ACTION_UP:

			_lastMouseX = 0;
			_lastMouseY = 0;

			break;

		}

		return true;
	}

	@Override
	protected void onDestroy() {
		_client.disconnect();

		super.onDestroy();
	}
}
