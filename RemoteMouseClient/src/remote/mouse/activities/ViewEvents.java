package remote.mouse.activities;

import remote.mouse.R;
import remote.mouse.model.KeyboardManager;
import remote.mouse.model.MessageSender;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
	static final int MAX_DURATION = 400;
	
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
		
		editText = (EditText) findViewById(R.id.view_events_keyboard_text);
		editText.addTextChangedListener(this);
		editText.setOnKeyListener(this);
		
		// Set the touch listener
		((RelativeLayout) findViewById(R.id.layoutMain)).setOnTouchListener(this);
		
		sender = MessageSender.getInstance();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		InputMethodManager imm = (InputMethodManager) this
		        .getSystemService(Context.INPUT_METHOD_SERVICE);
		
		if (imm != null) {
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		}
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
	}
	
	// ------------------------------------------------------
	// TextWatcher overrides
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
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				
				lastMouseX = x;
				lastMouseY = y;
				
				startTime = System.currentTimeMillis();
				clickCount++;
				
				break;
			
			case MotionEvent.ACTION_MOVE:
				
				sender.sendMouseMovementMessage(x - lastMouseX, y - lastMouseY);
				
				lastMouseX = x;
				lastMouseY = y;
				
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
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
	}
	
	public void radioKeyboardTypeTextOnTouch(View view) {
		editText.setInputType(InputType.TYPE_CLASS_TEXT);
	}
	
}
