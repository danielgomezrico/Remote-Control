package remote.mouse.activities.events;

import remote.mouse.R;
import remote.mouse.model.MessageSender;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;

public class ViewMouse extends Activity implements OnTouchListener {

	private int _lastMouseX, _lastMouseY;
	private MessageSender sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_events_mouse);

		// Set the touch listener
		((LinearLayout) findViewById(R.id.layoutMain)).setOnTouchListener(this);

		sender = MessageSender.getInstance();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			_lastMouseX = x;
			_lastMouseY = y;

			break;

		case MotionEvent.ACTION_MOVE:

			sender.sendMouseMessage(x - _lastMouseX, y - _lastMouseY);

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
}
