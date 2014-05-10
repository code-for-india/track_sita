package com.example.sita;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class ReviewActivity extends Activity {
	
	private Button save, submit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		
		save = (Button) findViewById(R.id.button_save_final);
		submit = (Button) findViewById(R.id.button_submit_final);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);
		return true;
	}

}
