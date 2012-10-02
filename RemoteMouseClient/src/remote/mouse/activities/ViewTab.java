package remote.mouse.activities;

import remote.mouse.R;
import remote.mouse.activities.events.ViewKeyboard;
import remote.mouse.activities.events.ViewMouse;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments. It uses a trick (see the code below) to allow the
 * tabs to switch between fragments instead of simple views.
 */
public class ViewTab extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_tab);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		intent = new Intent().setClass(this, ViewKeyboard.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("Keyboard")
				.setIndicator("Keyboard",
						res.getDrawable(R.drawable.ic_tab_mouse))
				.setContent(intent);
		tabHost.addTab(spec);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ViewMouse.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("Mouse")
				.setIndicator("Mouse", res.getDrawable(R.drawable.ic_tab_mouse))
				.setContent(intent);
		tabHost.addTab(spec);
	}
}