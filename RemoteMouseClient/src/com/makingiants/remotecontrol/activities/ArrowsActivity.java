package com.makingiants.remotecontrol.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.makingiants.remotecontrol.R;
import com.makingiants.remotecontrol.model.MessageSender;

public class ArrowsActivity extends Activity {

	//
	// Activity overrides
	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_arrows);
	}

	public void arrowOnClick(View view) {
		if (view.getTag().equals("0")) {
			MessageSender.getInstance().sendKeyboardMessage("UP");
		} else if (view.getTag().equals("1")) {
			MessageSender.getInstance().sendKeyboardMessage("LEFT");
		} else if (view.getTag().equals("2")) {
			MessageSender.getInstance().sendKeyboardMessage("RIGHT");
		} else if (view.getTag().equals("3")) {
			MessageSender.getInstance().sendKeyboardMessage("DOWN");
		}

	}

}
