package com.example.sita;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PlaceActivity extends Activity {
	
	Button back, next;
	int pos;

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
		
		pos = (int) dropdown.getSelectedItemPosition();
		
		dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				GlobalVars.place_id=pos;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		next.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (PlaceActivity.this,TakePictureActivity.class);
    			startActivity(intent);
    		}
    		
    	});
		
		back.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (PlaceActivity.this,SchoolSelectActivity.class);
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
