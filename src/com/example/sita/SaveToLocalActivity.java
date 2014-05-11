package com.example.sita;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SaveToLocalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_to_local);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_to_local, menu);
		return true;
	}

}
