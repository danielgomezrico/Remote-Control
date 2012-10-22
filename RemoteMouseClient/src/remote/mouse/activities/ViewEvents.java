package remote.mouse.activities;

import remote.mouse.R;
import remote.mouse.model.KeyboardManager;
import remote.mouse.model.MessageSender;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * 
 * @author danielgomez22
 *
 * TODO: add right click check to send the message 
 */
public class ViewEvents extends Activity implements TextWatcher, OnKeyListener, OnTouchListener {
	
	// ------------------------------------------------------
	// Constants
	// ------------------------------------------------------
	
	/* 
	 * Constant for defining the time duration between the click that 
	 * can be considered as double-tap, used to check click and double click
	 */
	static final int MAX_DURATION = 250;
	
	// ------------------------------------------------------
	// Attributes
	// ------------------------------------------------------
	
	private EditText editText;
	
	private int clickCount = 0;//variable for counting two successive up-down events
	private long startTime;//variable for storing the time of first click
	private long duration;//variable for calculating the total time
	
	//Mouse
	private int lastMouseX, lastMouseY;
	private MessageSender sender;
	
	// ------------------------------------------------------
	// Activity overrides
	// ------------------------------------------------------
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_events);
		
		sender = MessageSender.getInstance();
		
		// Set the text listeners for keyboard messages
		editText = (EditText) findViewById(R.id.view_events_keyboard_text);
		editText.addTextChangedListener(this);
		editText.setOnKeyListener(this);
		
		// Set the touch listener for mouse messages
		((RelativeLayout) findViewById(R.id.view_events_layout_main)).setOnTouchListener(this);
		
	}
	
	// ------------------------------------------------------
	// OnKeyListener overrides
	// ------------------------------------------------------
	
	@Override
	public void afterTextChanged(final Editable s) {
		editText.removeTextChangedListener(this);
		editText.setText("");
		editText.addTextChangedListener(this);
	}
	
	@Override
	public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
		
	}
	
	@Override
	public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
		
		if (count != 0) {
			Log.d("Remote 1", "'" + s + "'");
			String value = KeyboardManager.getValueToSend(s.charAt(0));
			MessageSender.getInstance().sendKeyboardMessage(value);
		}
		
	}
	
	// ------------------------------------------------------
	// TextWatcher overrides
	// ------------------------------------------------------
	
	@Override
	public boolean onKey(View arg0, int keyCode, KeyEvent event) {
		
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			
			String value = KeyboardManager.getValueToSend(keyCode);
			
			Log.d("Remote 2", "'" + value + "'" + " " + keyCode);
			
			if (value != null) {
				MessageSender.getInstance().sendKeyboardMessage(value);
			}
			
		}
		
		return false;
	}
	
	//------------------------------------------------------
	// OnTouchListener overrides
	// ------------------------------------------------------
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		int x = (int) event.getX();
		int y = (int) event.getY();
		boolean validMovement = false;
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				validMovement = false;
				lastMouseX = x;
				lastMouseY = y;
				
				startTime = System.currentTimeMillis();
				clickCount++;
				
				break;
			
			case MotionEvent.ACTION_MOVE:
				
				if (!validMovement) {
					int offX = x - lastMouseX;
					int offY = y - lastMouseY;
					if (offX > 5 || offY > 5) {
						validMovement = true;
					}
				}
				
				if (validMovement) {
					sender.sendMouseMovementMessage(x - lastMouseX, y - lastMouseY);
					
					lastMouseX = x;
					lastMouseY = y;
					
				}
				
				break;
			
			case MotionEvent.ACTION_UP:
				
				lastMouseX = 0;
				lastMouseY = 0;
				
				long time = System.currentTimeMillis() - startTime;
				duration = duration + time;
				
				if (clickCount == 1) {
					if (time <= MAX_DURATION) {
						sender.sendMouseClickMessage();
					}
					clickCount = 0;
					duration = 0;
					break;
				}
				
				break;
		
		}
		
		return true;
	}
	
	// ------------------------------------------------------
	// View events
	// ------------------------------------------------------
	
	public void radioKeyboardTypeNumberOnTouch(View view) {
		editText.setInputType(InputType.TYPE_CLASS_PHONE);
	}
	
	public void radioKeyboardTypeTextOnTouch(View view) {
		editText.setInputType(InputType.TYPE_CLASS_TEXT);
	}
	
	public void leftClickOnTouch(View view) {
		
	}
}
