package com.example.sita;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PlaceActivity extends Activity {
	
	Button back, next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place);
 
		next = (Button) findViewById(R.id.button_next_place);
 		back = (Button) findViewById(R.id.button_back_place);
 		
		Spinner dropdown = (Spinner) findViewById(R.id.spinner_photographed_place);
		ArrayList<String> options = new ArrayList<String>();
		options.add("Toilet");
		options.add("Playground");
		options.add("Classroom");
		options.add("Drinking Water");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,options);
		dropdown.setAdapter(adapter);
		
		next.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (PlaceActivity.this,ReviewActivity.class);
    			startActivity(intent);
    		}
    		
    	});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.place, menu);
		return true;
	}

}
