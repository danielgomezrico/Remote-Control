package remote.mouse.activities.events;

import remote.mouse.R;
import remote.mouse.model.KeyboardManager;
import remote.mouse.model.MessageSender;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ViewKeyboard extends Activity implements TextWatcher,
		OnKeyListener {

	private EditText editText;

	// ------------------------------------------------------
	// Activity overrides
	// ------------------------------------------------------

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.view_events_keyboard);

		editText = (EditText) findViewById(R.id.view_events_keyboard_text);
		editText.addTextChangedListener(this);
		editText.setOnKeyListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();

		InputMethodManager imm = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		if (imm != null) {
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		}

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

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
	public void beforeTextChanged(final CharSequence s, final int start,
			final int count, final int after) {

	}

	@Override
	public void onTextChanged(final CharSequence s, final int start,
			final int before, final int count) {

		if (count != 0) {
			Log.d("Key1 ", s + "");
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
			Log.d("Key2 + ", keyCode + "");
			String value = KeyboardManager.getValueToSend(keyCode);

			if (value != null) {
				MessageSender.getInstance().sendKeyboardMessage(value);
			}

		}

		return false;
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
